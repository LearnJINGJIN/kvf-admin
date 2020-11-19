package com.kalvin.kvf.modules.mts.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.entity.CRDReqData;
import com.kalvin.kvf.modules.mts.entity.CRXReqData;
import com.kalvin.kvf.modules.mts.service.CRDReqDataService;
import com.kalvin.kvf.modules.mts.service.CRXReqDataService;
import com.kalvin.kvf.modules.mts.util.AdpUtil;
import com.kalvin.kvf.modules.mts.util.CommonUtil;
import com.kalvin.kvf.modules.mts.util.DateUtil;
import com.kalvin.kvf.modules.mts.util.SFTPUtil;
import com.kalvin.kvf.modules.schedule.constant.JobConstant;
import com.kalvin.kvf.modules.schedule.entity.JobLog;
import com.kalvin.kvf.modules.schedule.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
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
import java.util.concurrent.TimeUnit;


/**
 * Description:将crd和crx数据每天入库
 *
 * @Auther: jingjin
 * @Date: 2020-10-21 14:39
 * @Modified by:
 */
@Slf4j
@Component
public class ImportCsvToDBJob extends QuartzJobBean {
   private CRDReqDataService crdService = SpringContextKit.getBean(CRDReqDataService.class);
   private CRXReqDataService crxService = SpringContextKit.getBean(CRXReqDataService.class);
    private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);
    private RedisTemplate<String, Object> template = SpringContextKit.getBean("redisTemplate");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ValueOperations<String, Object> redis = template.opsForValue();

        JobLog jobLog=new JobLog();
        jobLog.setBean("ImportCsvToDBJob");
        // 获得传入的参数
        Object params = jobExecutionContext.getMergedJobDataMap().get(JobConstant.JOB_MAP_KEY);
        Map<String, String> paramMap = AdpUtil.getStringToMap(params.toString());
         try{
            String date=DateUtil.getTaskJobDate(paramMap.get("tx_date"));
            if("1".equals(redis.get(date+"csvToDB"))||redis.get(date+"csvToDB")!=null){
                 jobLog.setStatus(0);
                jobLog.setReason(date+"日crx和crd已经入库了");
            }else{
                 //数据地址

                String impfile=String.format("%s%s%s",
                        CommonUtil.getKeyValue(CommonConstant.SJ_INSERT_CR_PATH),
                        File.separator, date);
                List<String> csvFiles = AdpUtil.getLocalFileLists(impfile);
                 if(null == csvFiles || csvFiles.size() == 0){
                    //看是否进行短信通知？
                    throw new JobExecutionException("数据平台未生成"+date+"日的crx和crd文件！" );
                }
                if (csvFiles.size() != 2){
                    throw new JobExecutionException("crx和crd文件不全!" );
                }
                 //导入crd和crx
                List<CRDReqData> crdList=null;
                List<CRXReqData> crxList=null;
                 for(String file : csvFiles){
                     if(file.contains(String.format("%s%s", CommonConstant.CRD_APP_TYPE ,date ))){
                         crdList=impcrd(impfile,file);
                        if (crdList.size()!=0){
                            crdService.saveBatch(crdList);
                         }
                        continue;
                    }
                    if(file.contains(String.format("%s%s", CommonConstant.CRX_APP_TYPE,date))){
                         crxList=impcrx(impfile,file);
                        if (crxList.size()!=0){
                            crxService.saveBatch(crxList);
                        }
                        continue;
                    }
                }
                redis.set(date+"csvToDB","1",Integer.valueOf(CommonUtil.getKeyValue(CommonConstant.REDIS_OUT_TIME)), TimeUnit.HOURS);
                jobLog.setStatus(0);
                jobLog.setReason("crx和crd文件入库成功");
            }
          }catch(Exception e){
             jobLog.setStatus(1);
            jobLog.setReason(e.getMessage());
            throw new JobExecutionException("crx和crd文件入库失败" , e);
        }finally {
            jobService.save(jobLog);

        }
    }
    //导入crx
    private List<CRDReqData> impcrd(String impfile,String crdFile) throws DocumentException {
         List<CRDReqData> crdList = new ArrayList<CRDReqData>(); //用来保存数据
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(impfile+File.separator+crdFile));
        //获取根元素
        Element root = doc.getRootElement();
        String type=root.elementTextTrim("APPTYPE");
        if (!"CRD".equals(type)){
            return crdList;
        }
        int total=Integer.valueOf(root.elementTextTrim("TOTALRECORDS"));
        if (total==0){
            return crdList;
        }
        //解析具体数据
        Element records = root.element("RECORDS");
        List<Element> recs = records.elements("REC");
        CRDReqData crd=null;
        for (Element rec:recs) {
            crd=new CRDReqData();
            crd.setOperTypeCode(rec.elementTextTrim("OPER_TYPE_CODE"));
            crd.setReasonCode(rec.elementTextTrim("REASON_CODE"));
            crd.setRefno(rec.elementTextTrim("REFNO"));
            crd.setCertTypeCode(rec.elementTextTrim("CERT_TYPE_CODE"));
            crd.setPtyCountryCode(rec.elementTextTrim("PTY_COUNTRY_CODE"));
            crd.setIdCode(rec.elementTextTrim("ID_CODE"));
            crd.setPersonName(rec.elementTextTrim("PERSON_NAME"));
            crd.setAcctno(rec.elementTextTrim("ACCTNO"));
            crd.setJyCcyCode(rec.elementTextTrim("JY_CCY_CODE"));
            crd.setJyAmt(rec.elementTextTrim("JY_AMT"));
            crd.setQsAmtRmb(rec.elementTextTrim("QS_AMT_RMB"));
            crd.setMccCode(rec.elementTextTrim("MCC_CODE"));
            crd.setCardTypeCode(rec.elementTextTrim("CARD_TYPE_CODE"));
            crd.setCardChnlCode(rec.elementTextTrim("CARD_CHNL_CODE"));
            crd.setBankCode(rec.elementTextTrim("BANK_CODE"));
            crd.setBranchSafecode(rec.elementTextTrim("BRANCH_SAFECODE"));
            crd.setBizDealTime(rec.elementTextTrim("BIZ_DEAL_TIME"));
            crd.setCountryCode(rec.elementTextTrim("COUNTRY_CODE"));
            crd.setBankSelfNum(rec.elementTextTrim("BANK_SELF_NUM"));
            crd.setCardSelfNum(rec.elementTextTrim("CARD_SELF_NUM"));
            crd.setDataDate(rec.elementTextTrim("DATA_DATE"));
            crdList.add(crd);
        }
       return crdList;
    }
    //导入crd
    private List<CRXReqData> impcrx(String impfile,String crxFile) throws DocumentException {
        log.info("crd地址："+crxFile);
        List<CRXReqData> crxList = new ArrayList<CRXReqData>(); //用来保存数据
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(impfile+File.separator+crxFile));
        //获取根元素
        Element root = doc.getRootElement();
        String type=root.elementTextTrim("APPTYPE");
        if (!"CRX".equals(type)){
            return crxList;
        }
        int total=Integer.valueOf(root.elementTextTrim("TOTALRECORDS"));
        if (total==0){
            return crxList;
        }
        //解析具体数据
        Element records = root.element("RECORDS");
        List<Element> recs = records.elements("REC");
        CRXReqData crx=null;
        for (Element rec:recs) {
            crx = new CRXReqData();
            //todo 此次开始处理获取到的数据，可以向数据库中进行插入
            crx.setOperTypeCode(rec.elementTextTrim("OPER_TYPE_CODE"));
            crx.setReasonCode(rec.elementTextTrim("REASON_CODE"));
            crx.setRefno(rec.elementTextTrim("REFNO"));
            crx.setCertTypeCode(rec.elementTextTrim("CERT_TYPE_CODE"));
            crx.setPtyCountryCode(rec.elementTextTrim("PTY_COUNTRY_CODE"));
            crx.setIdCode(rec.elementTextTrim("ID_CODE"));
            crx.setPersonName(rec.elementTextTrim("PERSON_NAME"));
            crx.setAcctno(rec.elementTextTrim("ACCTNO"));
            crx.setJyCcyCode(rec.elementTextTrim("JY_CCY_CODE"));
            crx.setJyAmt(rec.elementTextTrim("JY_AMT"));
            crx.setQsAmtRmb(rec.elementTextTrim("QS_AMT_RMB"));
            crx.setMccCode(rec.elementTextTrim("MCC_CODE"));
            crx.setCardTypeCode(rec.elementTextTrim("CARD_TYPE_CODE"));
            crx.setCardChnlCode(rec.elementTextTrim("CARD_CHNL_CODE"));
            crx.setBankCode(rec.elementTextTrim("BANK_CODE"));
            crx.setBranchSafecode(rec.elementTextTrim("BRANCH_SAFECODE"));
            crx.setBizDealTime(rec.elementTextTrim("BIZ_DEAL_TIME"));
            crx.setCountryCode(rec.elementTextTrim("COUNTRY_CODE"));
            crx.setBankSelfNum(rec.elementTextTrim("BANK_SELF_NUM"));
            crx.setCardSelfNum(rec.elementTextTrim("CARD_SELF_NUM"));
            crx.setShName(rec.elementTextTrim("SH_NAME"));
            crx.setJyChnl(rec.elementTextTrim("JY_CHNL"));
            crx.setDataDate(rec.elementTextTrim("DATA_DATE"));
            crxList.add(crx);
        }
        return crxList;
     }
}
