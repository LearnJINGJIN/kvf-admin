package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.entity.ReqControlType;
import com.kalvin.kvf.modules.mts.service.impl.GenMtsDataService;
import com.kalvin.kvf.modules.mts.util.CommonServiceUtil;
import com.kalvin.kvf.modules.mts.util.CommonUtil;
import com.kalvin.kvf.modules.mts.util.SFTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Description:采集行内数据失败\\文件上传CRX
 *
 * @Auther: jingjin
 * @Date: 2020-10-20 15:23
 * @Modified by:
 */
@Slf4j
public class CreateSendCRXFile {
    private GenMtsDataService service = SpringContextKit.getBean(GenMtsDataService.class);

    public void doWork(JobExecutionContext context, int dirNum) throws JobExecutionException {
        SFTPUtil sftp = null;
        String tokenFileStr = null;
        boolean upTokenFlag = false;
        String date = (String)context.get("tx_date");
        String pageNumStr = (String)context.get("page_num");
        String uploadDate = (String)context.get("upload_date");

        try{
            int pageNum = null == pageNumStr ? 5000: Integer.parseInt(pageNumStr);
            sftp = new SFTPUtil();
            tokenFileStr = String.format("%s%s%s",
                    CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                    File.separator,
                    CommonConstant.TOKEN_FILE_NAME);//Token.lock默认放在本地主目录下
            int tryTimes = 1;
            String maxTime = CommonUtil.getKeyValue(CommonConstant.TOKEN_RETRY_TIME_KEY) ;
            int maxRetryTimes = Integer.parseInt(maxTime == null ? "21" : maxTime);
            while(true){
                if(!CommonServiceUtil.checkHasToken( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY )){//检查是否存在Token.lock文件,若没有跳出循环
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
                service.createTokenFile(tokenFileStr);
            }
            service.genCRXCrdtFile(date, pageNum, uploadDate, dirNum);//生成消费贷记文件
            service.genCRXDbitFile(date, pageNum, uploadDate, dirNum);//生成消费借记文件
            service.genCRXOtherFile(date, pageNum, uploadDate, dirNum);//生成消费其它文件

            sftp.putFile(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), token);//上传token.lock文件
            upTokenFlag = true;

            /***********************************************控制文件 beg********************************************/
            uploadFile(uploadDate, sftp);
            /***********************************************控制文件 end********************************************/

            sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), CommonConstant.TOKEN_FILE_NAME);

        }catch(JobExecutionException job){
            if(upTokenFlag){
                deleteuploadEx(uploadDate, sftp);
            }
            throw job;
        }catch(Exception e){

            if(upTokenFlag){
                deleteuploadEx(uploadDate, sftp);
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
     * @throws Exception
     *
     */
    private void uploadFile(String uploadDate, SFTPUtil sftp) throws Exception {
        String todayMainPath = String.format("%s%s%s%s",CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                File.separator, uploadDate, File.separator);
        String[] files = new File(todayMainPath).list();
        if(null == files || files.length == 0){
            return;
        }
        int length = files.length;
        String crxCrtlType = String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE);
        for(int i = 0 ; i < length ; i ++){
            String dirName = files[i];
            if(dirName.startsWith(crxCrtlType)){//消费文件目录
                uploadCrxFile(uploadDate, sftp, dirName);
            }
        }
    }

    /**
     * @throws Exception
     *
     */
    private void uploadCrxFile(String uploadDate, SFTPUtil sftp, String dirName) throws Exception {

        String currPath = String.format("%s%s%s%s%s%s",CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                File.separator, uploadDate, File.separator, dirName, File.separator);
        File currDirFile = new File(currPath);
        String[] files = currDirFile.list();
        if(null == files || files.length == 0){
            return;
        }
        int length = files.length;
        String crxType = CommonConstant.CRX_APP_TYPE;

        ReqControlType controlType1 = new ReqControlType();
        ReqControlType.Files typeFiles1 = new ReqControlType.Files();
        controlType1.setFiles(typeFiles1);
        for(int i = 0 ; i < length ; i++){
            String name = files[i];
            if(name.startsWith(crxType) && !(name.startsWith(String.format("%s%s", crxType,CommonConstant.CRX_CONTROL_FILE_TYPE)))){
                typeFiles1.getFileNameList().add(name);
            }
        }


        controlType1.setCurrentFile(CommonConstant.CRX_CONTROL_FILE_TYPE);
        controlType1.setAppType(CommonConstant.CRX_APP_TYPE);
        controlType1.setInOut(CommonConstant.BANK_INPUT_TYPE);

        controlType1.setTotalFiles(controlType1.getFiles().getFileNameList().size() + "");
        String controlFile1 = service.createFile(controlType1, CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, dirName, currPath);
        sftp.mkdir(CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), dirName);
        sftp.putFile(String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), dirName),new File(controlFile1));

        /***********************************************消费文件 beg********************************************/
        int size = typeFiles1.getFileNameList().size();
        for(int i = 0 ; i < size ; i ++){
            String fileName = typeFiles1.getFileNameList().get(i);
            String file = String.format("%s%s%s",currPath, File.separator,fileName);
            sftp.putFile(String.format("%s%s", CommonUtil.getKeyValue( CommonConstant.WHJ_ACCEPT_CRX_FILE_KEY), dirName), new File(file));
        }
        /***********************************************消费文件 end********************************************/

    }



    /**
     * 删除远程中的当天本地上传的目录
     * @param uploadDate
     */
    private void deleteuploadEx(String uploadDate, SFTPUtil sftp) {
        if(null == sftp){
            return;
        }
        try{

            String mainPath = String.format("%s%s%s%s",CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
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
