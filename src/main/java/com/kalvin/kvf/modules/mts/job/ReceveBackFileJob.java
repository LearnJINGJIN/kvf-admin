package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.util.AdpUtil;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:获取反馈错误文件的时间必须在,报送任务之后,并且必须在同一天
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 10:51
 * @Modified by:
 */
@Slf4j
@Component
public class ReceveBackFileJob extends QuartzJobBean {
    private RedisTemplate<String, Object> template = SpringContextKit.getBean("redisTemplate");
    private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobLog jobLog=new JobLog();
        jobLog.setBean("ReceveBackFileJob");
        // 获得传入的参数
        ValueOperations<String, Object> redis = template.opsForValue();
        Object params = jobExecutionContext.getMergedJobDataMap().get(JobConstant.JOB_MAP_KEY);
        Map<String, String> paramMap = AdpUtil.getStringToMap(params.toString());
        List<String> impFiles = new ArrayList<String>();
        ReceveCRDBackFile crdStep = new ReceveCRDBackFile();
        ReceveCRXBackFile crxStep = new ReceveCRXBackFile();
        try {
            String uploadDate = DateUtil.getTaskJobDate(paramMap.get("upload_date"));
            List<String> impCrdFiles = crdStep.doWork(uploadDate);
            List<String> impCrxFiles = crxStep.doWork(uploadDate);
            if(null != impCrdFiles){
                impFiles.addAll(impCrdFiles);
            }
            if(null != impCrxFiles){
                impFiles.addAll(impCrxFiles);
            }
            ReceveCRDErrFile crdErrStep = new ReceveCRDErrFile();
            ReceveCRXErrFile crxErrStep = new ReceveCRXErrFile();
            Map<String, List<String>> impErrCrdFiles = crdErrStep.doWork(uploadDate);
            Map<String, List<String>> impErrCrxFiles = crxErrStep.doWork(uploadDate);
            redis.set("imp_err_crd_files",impErrCrdFiles);
            redis.set("imp_err_crx_files",impErrCrxFiles);
            redis.set("back_file_list",impFiles);
            jobLog.setStatus(0);
            jobLog.setReason("获取反馈错误文件目录成功");
        }catch (Exception e){
            log.error(" get err back file fail " , e);
            jobLog.setStatus(1);
            jobLog.setReason(e.getMessage());
            throw new JobExecutionException("获取反馈错误文件失败!",e);
        }finally {
            jobService.save(jobLog);
        }
    }
}
