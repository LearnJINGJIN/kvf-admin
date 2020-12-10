package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.entity.FeedBackField;
import com.kalvin.kvf.modules.mts.entity.SAFEAnsData;
import com.kalvin.kvf.modules.mts.entity.SAFEAnsData.ErrField;
import com.kalvin.kvf.modules.mts.entity.SAFEAnsData.ErrFields;
import com.kalvin.kvf.modules.mts.entity.SAFEAnsData.ErrRecord;
import com.kalvin.kvf.modules.mts.service.FeedBackFieldService;
import com.kalvin.kvf.modules.mts.util.AdpUtil;
import com.kalvin.kvf.modules.mts.util.CommonUtil;
import com.kalvin.kvf.modules.mts.util.DateUtil;
import com.kalvin.kvf.modules.mts.util.XmlBinder;
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
 * Description:获取反馈错误文件
 *
 * @Auther: jingjin
 * @Date: 2020-10-20 11:22
 * @Modified by:
 */
@Slf4j
@Component
public class ImportBackFileJob extends QuartzJobBean {

    private FeedBackFieldService service = SpringContextKit.getBean(FeedBackFieldService.class);
    private RedisTemplate<String, Object> template = SpringContextKit.getBean("redisTemplate");
    private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobLog jobLog=new JobLog();
        jobLog.setBean("ImportBackFileJob");
      // 获得传入的参数
        ValueOperations<String, Object> redis = template.opsForValue();
        Object params = jobExecutionContext.getMergedJobDataMap().get(JobConstant.JOB_MAP_KEY);
        Map<String, String> paramMap = AdpUtil.getStringToMap(params.toString());
        try{
            String uploadDate = DateUtil.getTaskJobDate(paramMap.get("upload_date"));
            @SuppressWarnings({ "unchecked", "rawtypes" })
            List<String> acceptFileList = (List<String>)redis.get("back_file_list");
            if (acceptFileList!=null||acceptFileList.size()!=0){
                List<FeedBackField> feedBackList=impFiles(acceptFileList, uploadDate);
                service.saveBatch(feedBackList);
            }
            jobLog.setStatus(0);
            jobLog.setReason("获取反馈错误文件成功");
        }catch(Exception e){
            log.error(" get err back file fail " , e);
            jobLog.setStatus(1);
            jobLog.setReason(e.getMessage());
            throw new JobExecutionException("获取反馈错误文件失败!",e);
        }finally{
            jobService.save(jobLog);
        }
    }


    private List<FeedBackField> impFiles( List<String> fileList, String update) throws Exception{
        SAFEAnsData ansData = null;
        BufferedReader reader = null;
        List<FeedBackField> feedBackFieldList=new ArrayList<FeedBackField>();
        try{
            for (String file: fileList) {
                String fileName = new File(file).getName();
                String dataType = null;
                XmlBinder<SAFEAnsData> binder = new XmlBinder<SAFEAnsData>(SAFEAnsData.class);

                if(fileName.startsWith(
                        String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_OTHER_FILE_TYPE))){//提现其它
                    dataType = String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_OTHER_FILE_TYPE);
                }else if(fileName.startsWith(
                        String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CRDT_FILE_TYPE))){//提现贷记
                    dataType = String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CRDT_FILE_TYPE);
                }else if(fileName.startsWith(
                        String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_DBIT_FILE_TYPE))){//提现借记
                    dataType = String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_DBIT_FILE_TYPE);
                }else if(fileName.startsWith(
                        String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_OTHER_FILE_TYPE))){//消费其它
                    dataType = String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_OTHER_FILE_TYPE);
                }else if(fileName.startsWith(
                        String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CRDT_FILE_TYPE))){////消费贷记
                    dataType = String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CRDT_FILE_TYPE);
                }else if(fileName.startsWith(
                        String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_DBIT_FILE_TYPE))){//消费借记
                    dataType = String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_DBIT_FILE_TYPE);
                }
                if(dataType != null){

                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),
                            CommonUtil.getKeyValue(CommonConstant.BACK_FILE_ENCODE_KEY)));
                    String realLine = null;
                    StringBuilder buff = new StringBuilder();
                    while((realLine = reader.readLine()) != null ){
                        if(AdpUtil.notNull(realLine)){
                            buff.append(realLine);
                        }
                    }
                    ansData = binder.unmarshal(buff.toString());
                }
                if(ansData == null || AdpUtil.trimSpace(ansData.getFalRecords()).equals("0")){
                    continue;
                }
                List<ErrRecord> listErrRec = ansData.getErrRecords().getErrRecordList();
                int sizeRec = listErrRec.size();
                FeedBackField backfield = null;
                for(int j = 0 ; j < sizeRec ; j ++){
                    backfield = new FeedBackField();
                    ErrRecord errRec = listErrRec.get(j);
                    String bussNo = errRec.getBussNo();
                    ErrFields errfields = errRec.getErrFields();
                    if(null != errfields && errfields.getErrFieldList().size() > 0){
                        List<ErrField> erfields = errfields.getErrFieldList();
                        int fieldSize = erfields.size();
                        for (int k = 0; k < fieldSize ; k++){
                            ErrField field = erfields.get(k);
                            backfield.setBussNo(bussNo);
                            backfield.setDataType(dataType);
                            backfield.setInputDate(update);
                            backfield.setErrDesc(field.getErrDesc());
                            backfield.setErrField(field.getErrField());
                            backfield.setErrFieldCn(field.getErrFieldCn());
                            feedBackFieldList.add(backfield);
                         }
                    }
                }
            }

        }catch(Exception e){
            throw e;
        }finally{
            try {
                if (null != reader) {
                    reader.close();
                    reader = null;
                }
            }catch (Exception e2){

            }
        }
        return feedBackFieldList;
    }
}
