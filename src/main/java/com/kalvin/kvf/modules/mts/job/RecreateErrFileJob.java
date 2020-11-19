package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.entity.MTSCtrlErrFile;
import com.kalvin.kvf.modules.mts.entity.ReqControlType;
import com.kalvin.kvf.modules.mts.service.MTSCtrlErrFileService;
import com.kalvin.kvf.modules.mts.service.impl.GenMtsDataService;
import com.kalvin.kvf.modules.mts.util.*;
import com.kalvin.kvf.modules.schedule.constant.JobConstant;
import com.kalvin.kvf.modules.schedule.entity.JobLog;
import com.kalvin.kvf.modules.schedule.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 10:59
 * @Modified by:
 */
@Slf4j
@Component
public class RecreateErrFileJob extends QuartzJobBean {

    private MTSCtrlErrFileService mlService = SpringContextKit.getBean(MTSCtrlErrFileService.class);
    private GenMtsDataService gdservice = SpringContextKit.getBean(GenMtsDataService.class);
    private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);
    private RedisTemplate<String, Object> template = SpringContextKit.getBean("redisTemplate");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobLog jobLog=new JobLog();
        jobLog.setBean("RecreateErrFileJob");
        //获取配置参数
        Object params = jobExecutionContext.getMergedJobDataMap().get(JobConstant.JOB_MAP_KEY);
        ValueOperations<String, Object> redis = template.opsForValue();
        Map<String, String> paramMap = AdpUtil.getStringToMap(params.toString());
        try {
            String uploadDate = DateUtil.getTaskJobDate(paramMap.get("upload_date"));
            String locDir = String.format("%s%s%s%s", CommonUtil.getKeyValue( CommonConstant.BACK_ERR_FILE_PATH_KEY), File.separator, uploadDate , File.separator);
            File errAcceptFile = new File(locDir);
            if(!errAcceptFile.exists()){
                errAcceptFile.mkdirs();
            }
            Map<String, List<String>> impErrCrdFiles = (Map<String, List<String>>) redis.get("imp_err_crd_files");
            Map<String, List<String>> impErrCrxFiles = (Map<String, List<String>>)redis.get("imp_err_crx_files");
            Entry<String, List<String>> entry = null;
            String dirName = null;
            List<String> errFiles = null;
            List<String> crdfeedBackDirList = new ArrayList<String>();
            List<String> crxfeedBackDirList = new ArrayList<String>();
            if(null != impErrCrdFiles){
                Iterator<Entry<String, List<String>>> it = impErrCrdFiles.entrySet().iterator();
                while(it.hasNext()){
                    entry = it.next();
                    dirName = entry.getKey();
                    errFiles = entry.getValue();
                    dealCRDErrFile(dirName, uploadDate, errFiles, locDir);
                    crdfeedBackDirList.add(dirName);
                }
            }

            if(null != impErrCrxFiles){
                Iterator<Entry<String, List<String>>> it = impErrCrxFiles.entrySet().iterator();
                while(it.hasNext()){
                    entry = it.next();
                    dirName = entry.getKey();
                    errFiles = entry.getValue();
                    dealCRXErrFile(dirName, uploadDate, errFiles, locDir);
                    crxfeedBackDirList.add(dirName);
                }
            }
            if(crdfeedBackDirList.size() > 0){
                for(String errDirName : crdfeedBackDirList){
                    String errDir = String.format("%s%s%s", locDir, File.separator, errDirName);
                    uploadCrdErrFileFeedBack(uploadDate, errDir, errDirName);
                }
            }
            if(crxfeedBackDirList.size() > 0){
                for(String errDirName : crxfeedBackDirList){
                    String errDir = String.format("%s%s%s", locDir, File.separator, errDirName);
                    uploadCrxErrFileFeedBack(uploadDate, errDir, errDirName);
                }
            }
            jobLog.setStatus(0);
            jobLog.setReason("获取反馈错误文件成功");
        }catch (Exception e){
            log.error(" get err back file fail " , e);
            jobLog.setStatus(1);
            jobLog.setReason(e.getMessage());
            throw new JobExecutionException("获取反馈错误文件失败!",e);
        }finally {
            jobService.save(jobLog);
        }

    }

    /**
     * @throws JobExecutionException
     *
     */
    private void uploadCrdErrFileFeedBack(String uploadDate, String locErrDir, String errDirName) throws JobExecutionException {

        SFTPUtil sftp = null;
        String tokenFileStr = null;
        boolean upTokenFlag = false;

        try{
            sftp = new SFTPUtil();
            tokenFileStr = String.format("%s%s%s",
                    CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                    File.separator,
                    CommonConstant.TOKEN_FILE_NAME);//Token.lock默认放在本地主目录下
            int tryTimes = 1;
            String maxTime = CommonUtil.getKeyValue(CommonConstant.TOKEN_RETRY_TIME_KEY) ;
            int maxRetryTimes = Integer.parseInt(maxTime == null ? "21" : maxTime);
            while(true){
                if(!CommonServiceUtil.checkHasToken( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY )){//检查是否存在Token.lock文件,若没有跳出循环
                    break;
                }

                Thread.sleep(6000);
                if(tryTimes > maxRetryTimes){//若大于了两分钟跳出循环,可能需要人为查找原因
                    throw new JobExecutionException("等待Token.lock锁释放时间大于两分钟,请检查Token.lock文件!");
                }
                tryTimes++;
            }

            File token = new File(tokenFileStr);
            if(!token.exists()){
                gdservice.createTokenFile(tokenFileStr);
            }

            sftp.putFile(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY), token);//上传token.lock文件
            upTokenFlag = true;
            sftp.mkdir(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY), errDirName);

            File file = new File(locErrDir);
            if(file.exists() && file.isDirectory()){
                File[] subFiles = file.listFiles();
                int size = subFiles.length;
                for(int i = 0 ; i < size ; i ++){
                    String fileName = subFiles[i].getName();
                    if(fileName.startsWith(CommonConstant.CRD_APP_TYPE)){
                        sftp.putFile(String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY), errDirName), subFiles[i]);
                    }
                }
            }

            sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY), CommonConstant.TOKEN_FILE_NAME);

        }catch(JobExecutionException job){
            if(upTokenFlag){
                deleteuploadCrdEx(uploadDate, sftp);
            }
            throw job;
        }catch(Exception e){

            if(upTokenFlag){
                deleteuploadCrdEx(uploadDate, sftp);
            }
            throw new JobExecutionException("采集行内数据失败\\文件上传失败",e);
        }finally{
            try{
                if(null != null){
                    sftp.close();
                    sftp = null;
                }
            }catch(Exception e){}
        }

    }

    private void uploadCrxErrFileFeedBack(String uploadDate, String locErrDir, String errDirName) throws JobExecutionException {

        SFTPUtil sftp = null;
        String tokenFileStr = null;
        boolean upTokenFlag = false;

        try{
            sftp = new SFTPUtil();
            tokenFileStr = String.format("%s%s%s",
                    CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                    File.separator,
                    CommonConstant.TOKEN_FILE_NAME);//Token.lock默认放在本地主目录下
            int tryTimes = 1;
            String maxTime = CommonUtil.getKeyValue(CommonConstant.TOKEN_RETRY_TIME_KEY) ;
            int maxRetryTimes = Integer.parseInt(maxTime == null ? "21" : maxTime);
            while(true){
                if(!CommonServiceUtil.checkHasToken( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY )){//检查是否存在Token.lock文件,若没有跳出循环
                    break;
                }

                Thread.sleep(6000);
                if(tryTimes > maxRetryTimes){//若大于了两分钟跳出循环,可能需要人为查找原因
                    throw new JobExecutionException("等待Token.lock锁释放时间大于两分钟,请检查Token.lock文件!");
                }
                tryTimes++;
            }

            File token = new File(tokenFileStr);
            if(!token.exists()){
                gdservice.createTokenFile(tokenFileStr);
            }

            sftp.putFile(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), token);//上传token.lock文件
            upTokenFlag = true;

            sftp.mkdir(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), errDirName);

            File file = new File(locErrDir);
            if(file.exists() && file.isDirectory()){
                File[] subFiles = file.listFiles();
                int size = subFiles.length;
                for(int i = 0 ; i < size ; i ++){
                    String fileName = subFiles[i].getName();
                    if(fileName.startsWith(CommonConstant.CRX_APP_TYPE)){
                        sftp.putFile(String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), errDirName), subFiles[i]);
                    }
                }
            }

            sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), CommonConstant.TOKEN_FILE_NAME);

        }catch(JobExecutionException job){
            if(upTokenFlag){
                deleteuploadCrxEx(uploadDate, sftp);
            }
            throw job;
        }catch(Exception e){

            if(upTokenFlag){
                deleteuploadCrxEx(uploadDate, sftp);
            }
            throw new JobExecutionException("采集行内数据失败\\文件上传失败",e);
        }finally{
            try{
                if(null != null){
                    sftp.close();
                    sftp = null;
                }
            }catch(Exception e){}
        }

    }


    /**
     * @throws JobExecutionException
     * @throws
     *
     */
    private void dealCRDErrFile(String batchNo, String inputDate, List<String> errFiles, String locErrFileDir) throws Exception {
        if( null == errFiles){
            return;
        }
        MTSCtrlErrFile errFile = new MTSCtrlErrFile();
        errFile.setBatchNo(batchNo);
        errFile.setInputDate(inputDate);
        mlService.save(errFile);
        int size = errFiles.size();
        String locErrPath = String.format("%s%s%s%", locErrFileDir, File.separator, batchNo, File.separator);
        File locErrPathDirFile = new File(locErrPath);
        if(!locErrPathDirFile.exists()){
            locErrPathDirFile.mkdirs();
        }
        ReqControlType controlType = new ReqControlType();
        ReqControlType.Files typeFiles = new ReqControlType.Files();
        controlType.setFiles(typeFiles);
        for(int i = 0 ; i < size; i++){
            String fileStr = errFiles.get(i);
            cpFile(fileStr, locErrPath);
            typeFiles.getFileNameList().add(new File(fileStr).getName());
        }

        controlType.setCurrentFile(CommonConstant.CRD_CONTROL_FILE_TYPE);
        controlType.setAppType(CommonConstant.CRD_APP_TYPE);
        controlType.setInOut(CommonConstant.BANK_INPUT_TYPE);
        controlType.setTotalFiles(controlType.getFiles().getFileNameList().size() + "");
        gdservice.createFile(controlType, CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, batchNo, locErrPath);

    }

    private void dealCRXErrFile(String batchNo, String inputDate, List<String> errFiles, String locErrFileDir) throws Exception {
        if( null == errFiles){
            return;
        }
        MTSCtrlErrFile errFile = new MTSCtrlErrFile();
        errFile.setBatchNo(batchNo);
        errFile.setInputDate(inputDate);
        mlService.save(errFile);
        int size = errFiles.size();
        String locErrPath = String.format("%s%s%s%", locErrFileDir, File.separator, batchNo, File.separator);
        File locErrPathDirFile = new File(locErrPath);
        if(!locErrPathDirFile.exists()){
            locErrPathDirFile.mkdirs();
        }
        ReqControlType controlType = new ReqControlType();
        ReqControlType.Files typeFiles = new ReqControlType.Files();
        controlType.setFiles(typeFiles);
        for(int i = 0 ; i < size; i++){
            String fileStr = errFiles.get(i);
            cpFile(fileStr, locErrPath);
            typeFiles.getFileNameList().add(new File(fileStr).getName());
        }

        controlType.setCurrentFile(CommonConstant.CRX_CONTROL_FILE_TYPE);
        controlType.setAppType(CommonConstant.CRX_APP_TYPE);
        controlType.setInOut(CommonConstant.BANK_INPUT_TYPE);
        controlType.setTotalFiles(controlType.getFiles().getFileNameList().size() + "");
        gdservice.createFile(controlType, CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, batchNo, locErrPath);

    }


    /**
     * @throws Exception
     *
     */
    private void cpFile(String srcFile, String destFileDir) throws Exception {
        FileInputStream reader = null;
        FileOutputStream writer = null;
        try{
            reader = new FileInputStream(srcFile);
            writer = new FileOutputStream(String.format("%s%s%s", destFileDir, File.separator, new File(srcFile).getName()));
            byte[] buffer = new byte[1024 * 2];
            int by = 0;
            while((by = reader.read(buffer)) != -1){
                writer.write(buffer, 0, by);
            }
        }catch(Exception e){
            throw e;
        }finally{
            try{
                reader.close();
                writer.flush();
                writer.close();
            }catch(Exception e){}
        }

    }


    /**
     * 删除远程中的当天本地上传的目录
     * @param uploadDate
     */
    private void deleteuploadCrdEx(String uploadDate, SFTPUtil sftp) {
        if(null == sftp){
            return;
        }
        try{

            String mainPath = String.format("%s%s%s%s",CommonUtil.getKeyValue( CommonConstant.BACK_ERR_FILE_PATH_KEY),
                    File.separator, uploadDate, File.separator);


            String[] files = new File(mainPath).list();
            sftp = new SFTPUtil();
            if(null == files || files.length == 0){
                return ;
            }

            int size = files.length;
            List<String> remotefiles = sftp.getRemoteFiles( CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY));
            for(int i = 0 ; i < size ; i++) {
                String fileName = files[i];
                if(remotefiles != null && remotefiles.contains(fileName)){
                    List<String> remotefiles1 = sftp.getRemoteFiles(
                            String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY),fileName));
                    for(String name : remotefiles1){
                        sftp.delete(String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY),fileName), name);
                    }
                    sftp.rmdir(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY), fileName);
                }

            }
            sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRD_FILE_KEY), CommonConstant.TOKEN_FILE_NAME);
        }catch(Exception e){
            log.error("delete remote file error ",e);
        }finally{
            try{
                if(null != null){
                    sftp.close();
                    sftp = null;
                }
            }catch(Exception e){}
        }

    }

    private void deleteuploadCrxEx(String uploadDate, SFTPUtil sftp) {
        if(null == sftp){
            return;
        }
        try{

            String mainPath = String.format("%s%s%s%s",CommonUtil.getKeyValue( CommonConstant.BACK_ERR_FILE_PATH_KEY),
                    File.separator, uploadDate, File.separator);


            String[] files = new File(mainPath).list();
            sftp = new SFTPUtil();
            if(null == files || files.length == 0){
                return ;
            }

            int size = files.length;
            List<String> remotefiles = sftp.getRemoteFiles( CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY));
            for(int i = 0 ; i < size ; i++) {
                String fileName = files[i];
                if(remotefiles != null && remotefiles.contains(fileName)){
                    List<String> remotefiles1 = sftp.getRemoteFiles(
                            String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY),fileName));
                    for(String name : remotefiles1){
                        sftp.delete(String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY),fileName), name);
                    }
                    sftp.rmdir(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), fileName);
                }

            }
            sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), CommonConstant.TOKEN_FILE_NAME);
        }catch(Exception e){
            log.error("delete remote file error ",e);
        }finally{
            try{
                if(null != null){
                    sftp.close();
                    sftp = null;
                }
            }catch(Exception e){}
        }

    }
}
