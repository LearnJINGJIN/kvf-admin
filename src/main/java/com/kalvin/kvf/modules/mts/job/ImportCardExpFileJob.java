package com.kalvin.kvf.modules.mts.job;

import com.csii.bpf.common.StringUtil;
import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.entity.MTSCardExpInfo;
import com.kalvin.kvf.modules.mts.service.MTSCardExpInfoService;
import com.kalvin.kvf.modules.mts.util.AdpUtil;
import com.kalvin.kvf.modules.mts.util.CommonUtil;
import com.kalvin.kvf.modules.mts.util.DateUtil;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:导入违规交易信息
 *
 * @Auther: jingjin
 * @Date: 2020-10-20 10:56
 * @Modified by:
 */
@Slf4j
@Component
public class ImportCardExpFileJob extends QuartzJobBean {

    private MTSCardExpInfoService service = SpringContextKit.getBean(MTSCardExpInfoService.class);
    private RedisTemplate<String, Object> template = SpringContextKit.getBean("redisTemplate");
    private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobLog jobLog=new JobLog();
        jobLog.setBean("ImportCardExpFileJob");
        // 获得传入的参数
        ValueOperations<String, Object> redis = template.opsForValue();
        Object params = jobExecutionContext.getMergedJobDataMap().get(JobConstant.JOB_MAP_KEY);
        Map<String, String> paramMap = AdpUtil.getStringToMap(params.toString());
        try{
            String date = DateUtil.getTaskJobDate(paramMap.get("tx_date"));
            @SuppressWarnings({ "unchecked", "rawtypes" })
            List<String> acceptFileList = (List<String>)redis.get("acceptFileList");
//			String locDir = String.format("%s%s", CommonUtil.getKeyValue(CommonConstant.CARD_EXP_FILE_PATH_KEY),File.separator);
            if(null == acceptFileList || acceptFileList.size() == 0){
                return;
            }
            int size = acceptFileList.size();
            MTSCardExpInfo cardExpInfo=null;
            List<MTSCardExpInfo> mtsCardExpInfoList=new ArrayList<MTSCardExpInfo>();
            for(int i = 0 ; i < size ; i++){
                String fileName = acceptFileList.get(i);
                log.info("=================fileName["+fileName+"]==================");
                if(fileName.toLowerCase().endsWith(CommonUtil.getKeyValue(CommonConstant.CARD_EXP_FILE_EXT_KEY).toLowerCase())){
                    File file1 = new File(fileName);
                    cardExpInfo= imp(fileName, date,
                            file1.getName().substring(
                                    Integer.parseInt(CommonUtil.getKeyValue(CommonConstant.CARD_EXP_FILE_TYPE_BEG_KEY)),
                                    Integer.parseInt(CommonUtil.getKeyValue(CommonConstant.CARD_EXP_FILE_TYPE_END_KEY))));
                }
                if (cardExpInfo!=null){
                    mtsCardExpInfoList.add(cardExpInfo);
                }
            }
            if (mtsCardExpInfoList.size()!=0){
                service.saveBatch(mtsCardExpInfoList);
            }
            jobLog.setStatus(0);
            jobLog.setReason("导入违规交易信息成功");
        }catch(Exception e){
            log.error(" import card exp file fail  " , e);
            jobLog.setStatus(1);
            jobLog.setReason(e.getMessage());
            throw new JobExecutionException("导入违规交易信息失败" , e);
        }finally {
            jobService.save(jobLog);
        }
    }
    private MTSCardExpInfo imp(String file, String date, String dataType) throws Exception{
         BufferedReader reader = null;
        MTSCardExpInfo cardExpInfo = null;
        try{
             reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),CommonUtil.getKeyValue(CommonConstant.TRAN_EXP_FILE_ENCODE_KEY)));	//读文件
            String realLine = null;
            int row = 0;
            while((realLine = reader.readLine()) != null ){
                if(row == 0 ){
                    row++;
                    continue;
                }
                if(AdpUtil.trimSpace(realLine).equals(CommonUtil.getKeyValue(CommonConstant.CARD_EXP_FILE_END_KEY))){
                    break;
                }
                cardExpInfo = new MTSCardExpInfo();
                String[] data = StringUtil.split(realLine, ",");
                if(data.length == 3){
                    cardExpInfo.setCertTypeCode(AdpUtil.trimSpace(data[0]));
                    cardExpInfo.setDataType(AdpUtil.trimSpace(dataType));
                    cardExpInfo.setIdCode(AdpUtil.trimSpace(data[2]));
                    cardExpInfo.setInputDate(AdpUtil.trimSpace(date));
                    cardExpInfo.setPtyCountryCode(AdpUtil.trimSpace(data[1]));
                  }
            }
            return cardExpInfo;
         }catch(Exception e){
             throw e;
        }finally{
             try{
                if(null != reader) reader.close();
            }catch(Exception e){}
        }

    }

}
