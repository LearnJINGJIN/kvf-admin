package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.service.impl.GenMtsDataService;
import com.kalvin.kvf.modules.mts.util.CommonServiceUtil;
import com.kalvin.kvf.modules.mts.util.CommonUtil;
import com.kalvin.kvf.modules.mts.util.SFTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:crx返回信息文件名获取
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 10:58
 * @Modified by:
 */
@Slf4j
public class ReceveCRXErrFile {
    private GenMtsDataService service = SpringContextKit.getBean(GenMtsDataService.class);

    public Map<String, List<String>> doWork(String uploadDate) throws JobExecutionException {
         SFTPUtil sftp = null;
        String tokenFileStr = null;
        boolean upTokenFlag = false;
//        String uploadDate = (String)context.getAttribute("upload_date");
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
                if(!CommonServiceUtil.checkHasToken( CommonConstant.WHJ_SEND_ERR_TTCRX_KEY)){//检查是否存在Token.lock文件,若没有跳出循环
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
            List<String> errFiles = sftp.getRemoteFiles(CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_ERR_TTCRX_KEY));
            if(null == errFiles || errFiles.size() == 0){
                return null;
            }
            sftp.putFile(CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_ERR_TTCRX_KEY), token);//上传token.lock文件

            upTokenFlag = true;
            String locTodaySendDir = String.format("%s%s%s%s",CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                    File.separator, uploadDate ,File.separator);
            File locTodayFileDir = new File(locTodaySendDir);
            String[] subFiles = locTodayFileDir.list();//子文件均为控制文件目录

            Map<String, List<String>> map = new HashMap<String, List<String>>();
            List<String> impFiles = null;
            //遍历远程错误主目录,判断当天的返回文件夹是否包含在远程目录中,若包含取下该文件夹下的所有文件,并将数据文件导入数据库
            if(null != subFiles && subFiles.length > 0){
                int length = subFiles.length ;
                for(int i = 0 ; i < length ; i++){
                    String filename = subFiles[i];
                    if(errFiles.contains(filename)){
                        //下载所有文件
                        String dir = filename;
                        String remoteDir = String.format("%s%s%s",
                                CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_ERR_TTCRX_KEY), File.separator, dir);
                        List<String> files = sftp.getRemoteFiles(remoteDir);
                        if(null != files){
                            impFiles = new ArrayList<String>();
                            for(String name : files){
                                if(!name.startsWith(String.format("%s%s",CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE))
                                        && name.startsWith(CommonConstant.CRX_APP_TYPE)){
                                    impFiles.add(String.format("%s%s%s", locTodaySendDir, File.separator, name));
                                }
                            }
                            map.put(filename, impFiles);
                        }
                    }
                }
            }
            sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_ERR_TTCRX_KEY), CommonConstant.TOKEN_FILE_NAME);
            return map;
        }catch(JobExecutionException job){
            backToBeg(sftp, upTokenFlag);
            throw job;
        }catch(Exception e){
            backToBeg(sftp, upTokenFlag);
            log.error(" get err back file fail " , e);
            throw new JobExecutionException("获取反馈错误文件失败!",e);
        }finally{
            try{
                if(null != null){
                    sftp.close();
                    sftp = null;
                }
            }catch(Exception e){}
        }

    }
    private void backToBeg(SFTPUtil sftp, boolean upTokenFlag){
        try{
            if(upTokenFlag){
                sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_ERR_TTCRX_KEY), CommonConstant.TOKEN_FILE_NAME);
            }
        }catch(Exception e1){}
    }
}
