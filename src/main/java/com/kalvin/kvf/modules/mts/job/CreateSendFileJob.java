package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.service.impl.GenMtsDataService;
import com.kalvin.kvf.modules.mts.util.AdpUtil;
import com.kalvin.kvf.modules.mts.util.CommonUtil;
import com.kalvin.kvf.modules.mts.util.DateUtil;
import com.kalvin.kvf.modules.schedule.constant.JobConstant;
import com.kalvin.kvf.modules.schedule.entity.JobLog;
import com.kalvin.kvf.modules.schedule.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

/**
 * Description:
 *
 * @Auther: jingjin
 * @Date: 2020-10-20 16:25
 * @Modified by:
 */
@Slf4j
@Component
public class CreateSendFileJob extends QuartzJobBean {

    private GenMtsDataService service = SpringContextKit.getBean(GenMtsDataService.class);
    private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobLog jobLog=new JobLog();
        jobLog.setBean("CreateSendFileJob");
        Object params = jobExecutionContext.getMergedJobDataMap().get(JobConstant.JOB_MAP_KEY);
        Map<String, String> paramMap = AdpUtil.getStringToMap(params.toString());
        try{
            String date = DateUtil.getTaskJobDate(paramMap.get("tx_date"));
            String uploadDate = DateUtil.getTaskJobDate(paramMap.get("upload_date"));
            String pageNumStr = paramMap.get("page_num");
            jobExecutionContext.put("tx_date",date);
            jobExecutionContext.put("upload_date",uploadDate);
            jobExecutionContext.put("page_num",pageNumStr);
            int pageNum = null == pageNumStr ? 5000: Integer.parseInt(pageNumStr);
            String todayyMainPath = String.format("%s%s%s%s", CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
                    File.separator, uploadDate, File.separator);
            File fileDir = new File(todayyMainPath);
            if(fileDir.exists() && fileDir.isDirectory()){
                File[] listF = fileDir.listFiles();

                for(File f : listF){
                    if(f.isDirectory()){
                        File[] subList = f.listFiles();
                        for(File subf : subList){
                            subf.delete();
                        }
                    }
                    f.delete();
                }
            }

            //重新报送上一周期的错误文件
            service.recreateErrBackFile(date, uploadDate, pageNum);//重新报送上一周期的错误文件
            int crddirNum = 0;
            int crxdirNum = 0;
            String[] files = new File(todayyMainPath).list();
            if(null == files || files.length == 0){
                crddirNum = 0;
                crxdirNum = 0;
            }else{
                int length = files.length;
                String crdCrtlType = String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE);
                String crxCrtlType = String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE);
                for(int i = 0 ; i < length ; i ++){
                    String dirName = files[i];
                    if(dirName.startsWith(crxCrtlType)){//消费文件目录
                        crxdirNum++;
                    }else if(dirName.startsWith(crdCrtlType)){//提现文件目录
                        crddirNum++;
                    }
                }
            }

            CreateSendCRDFile crdStep = new CreateSendCRDFile();
            CreateSendCRXFile crxStep = new CreateSendCRXFile();

            crdStep.doWork(jobExecutionContext,crddirNum);
            crxStep.doWork(jobExecutionContext, crxdirNum);
            jobLog.setStatus(0);
            jobLog.setReason("采集行内数据\\文件上传成功");
        }catch(JobExecutionException job){
            jobLog.setStatus(1);
            jobLog.setReason(job.getMessage());
            throw job;
        }catch(Exception e){
            jobLog.setStatus(1);
            jobLog.setReason(e.getMessage());
          }finally{
            jobService.save(jobLog);
        }

    }
}
