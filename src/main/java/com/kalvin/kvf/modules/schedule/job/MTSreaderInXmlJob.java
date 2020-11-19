package com.kalvin.kvf.modules.schedule.job;

import com.kalvin.kvf.common.utils.SpringContextKit;
import com.kalvin.kvf.modules.mts.entity.CRDReqData;
import com.kalvin.kvf.modules.mts.service.CRDReqDataService;
import com.kalvin.kvf.modules.schedule.entity.JobLog;
import com.kalvin.kvf.modules.schedule.service.JobLogService;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Description:MTS系統定时任务，用于每日查询推送过来的crd提现业务
 *
 * @Auther: jingjin
 * @Date: 2020-10-14 16:39
 * @Modified by:
 */
@Slf4j
@Component
public class MTSreaderInXmlJob extends QuartzJobBean {
    private CRDReqDataService service = SpringContextKit.getBean(CRDReqDataService.class);
     private JobLogService jobService = SpringContextKit.getBean(JobLogService.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobLog jobLog=new JobLog();
        jobLog.setBean("CreateSendFileJob");

        try {
            String crxFile="E:\\开发项目\\Jingj\\MTS系统\\需求文件\\mts\\CRD\\20201012\\CRDTT51030002020120101200\\CRDTB51030002020120101200.xml";
            SAXReader reader = new SAXReader();
            Document doc = null;
            doc = reader.read(new File(crxFile));
            //获取根元素
            Element root = doc.getRootElement();
            String type=root.elementTextTrim("APPTYPE");
            if (!"CRD".equals(type)){
                log.info("CRD IS NOT FIND");
            }
            int total=Integer.valueOf(root.elementTextTrim("TOTALRECORDS"));
            if (total==0){
                log.info("CRD IS NULL");
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
                service.save(crd);
            }
            jobExecutionContext.put("crd","ces");
            jobLog.setStatus(0);
            jobLog.setReason("采集行内数据失败\\文件上传成功");
        } catch (DocumentException e) {
            jobLog.setStatus(1);
             jobLog.setReason(e.getMessage());
            e.printStackTrace();
        }finally {
            jobService.save(jobLog);
        }
    }

}
