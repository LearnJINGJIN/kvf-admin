package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:获取外汇局违规交易文件
 *
 * @Auther: jingjin
 * @Date: 2020-10-20 11:13
 * @Modified by:
 */
@Slf4j
@Component
public class GetMTSCardExpInfoJob extends QuartzJobBean {

    private GenMtsDataService service = SpringContextKit.getBean(GenMtsDataService.class);
    private RedisTemplate<String, Object> template = SpringContextKit.getBean("redisTemplate");
    private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获得传入的参数
        JobLog jobLog=new JobLog();
        jobLog.setBean("GetMTSCardExpInfoJob");
        ValueOperations<String, Object> redis = template.opsForValue();
        Object params = jobExecutionContext.getMergedJobDataMap().get(JobConstant.JOB_MAP_KEY);
        Map<String, String> paramMap = AdpUtil.getStringToMap(params.toString());
         SFTPUtil sftp = null;
        String tokenFileStr = null;
        boolean upTokenFlag = false;
        try{
            String date = DateUtil.getTaskJobDate(paramMap.get("tx_date"));//
            sftp = new SFTPUtil();
            tokenFileStr = String.format("%s%s%s",
                    CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                    File.separator,
                    CommonConstant.TOKEN_FILE_NAME);//Token.lock默认放在本地主目录下
            int tryTimes = 1;
            String maxTime = CommonUtil.getKeyValue(CommonConstant.TOKEN_RETRY_TIME_KEY) ;
            int maxRetryTimes = Integer.parseInt(maxTime == null ? "21" : maxTime);
            while(true){
                if(!CommonServiceUtil.checkHasToken(CommonConstant.WHJ_SEND_EXP_FILE_KEY)){//检查是否存在Token.lock文件,若没有跳出循环
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
            sftp.putFile(CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_EXP_FILE_KEY), token);//上传token.lock文件
            upTokenFlag = true;

            List<String> fileList = sftp.getRemoteFiles(String.format("%s%s",
                    CommonUtil.getKeyValue(CommonConstant.WHJ_SEND_EXP_FILE_KEY), File.separator));

            if(null == fileList || fileList.size() == 0){
                return ;
            }
            int size = fileList.size();
            String locDir = String.format("%s%s", CommonUtil.getKeyValue(CommonConstant.CARD_EXP_FILE_PATH_KEY),File.separator);
            File errFile = new File(locDir);
            if(!errFile.exists()){
                errFile.mkdirs();
            }
            List<String> acceptFileList = new ArrayList<String>();
            redis.set("acceptFileList", acceptFileList);
            for(int i = 0 ; i < size ; i ++){
                String fileName = fileList.get(i);
                if(fileName.startsWith(date.substring(2))){//若存在子目录名开始等于当前日期的,则下载该目录下的文件
                    List<String> subfileList = sftp.getRemoteFiles(String.format("%s%s%s%s",
                            CommonUtil.getKeyValue(CommonConstant.WHJ_SEND_EXP_FILE_KEY), File.separator, fileName, File.separator));
                    for(String name :subfileList){
                        sftp.getFile(String.format("%s%s",
                                CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_EXP_FILE_KEY),fileName),
                                locDir, name, null, true);

                        acceptFileList.add(String.format("%s%s%s", locDir, File.separator, name));
                    }
                }
            }

            sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_EXP_FILE_KEY), CommonConstant.TOKEN_FILE_NAME);
            jobLog.setStatus(0);
            jobLog.setReason("获取外汇局违规交易文件成功");
        }catch(JobExecutionException job){
            backToBeg(sftp, upTokenFlag);
            jobLog.setStatus(1);
            jobLog.setReason(job.getMessage());
            throw job;
        } catch(Exception e){
            backToBeg(sftp, upTokenFlag);
            log.error("get card exp file fail " , e);
            jobLog.setStatus(1);
            jobLog.setReason(e.getMessage());
            throw new JobExecutionException("获取外汇局违规交易文件失败!",e);
        }finally{
            jobService.save(jobLog);
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
                sftp.delete(CommonUtil.getKeyValue( CommonConstant.WHJ_SEND_EXP_FILE_KEY), CommonConstant.TOKEN_FILE_NAME);
            }
        }catch(Exception e1){}
    }
}
