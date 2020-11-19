package com.kalvin.kvf.modules.mts.service.impl;


import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.entity.*;
import com.kalvin.kvf.modules.mts.entity.BankReqControlData.Record;
import com.kalvin.kvf.modules.mts.mapper.FeedBackFieldMapper;
import com.kalvin.kvf.modules.mts.service.CRDReqDataService;
import com.kalvin.kvf.modules.mts.service.CRXReqDataService;
import com.kalvin.kvf.modules.mts.service.FeedBackFieldService;
import com.kalvin.kvf.modules.mts.util.AdpUtil;
import com.kalvin.kvf.modules.mts.util.CommonUtil;
import com.kalvin.kvf.modules.mts.util.XmlBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @date：2017年7月17日
 * @time：上午11:03:46
 * @purpose：
 * @address：
 * @auth：Bob.jiang
 */
@Slf4j
@Component
public class GenMtsDataService {

    @Autowired
	private CRDReqDataService crdService;
    @Autowired
	private CRXReqDataService crxService;
    @Autowired
	private FeedBackFieldService ffService;
	
	private int batchNoMax = Integer.parseInt(CommonUtil.getKeyValue(CommonConstant.BATCH_NO_MAX_SEQ_KEY));
	
	
	private String createBatchNoDir(String appType, String fileType, String  uploadDate, int seqNo){
		
		String fileName = String.format("%s%s%s%s%s", 
				appType,
				fileType,
				CommonUtil.getKeyValue(CommonConstant.BANK_CODE_KEY),
				uploadDate.substring(2),
				String.format("%02d", seqNo));
		String fullFilePath = String.format("%s%s%s%s%s%s%s",CommonUtil.getKeyValue( CommonConstant.MAIN_XMLFILE_PATH_KEY),
				File.separator, uploadDate, File.separator,fileName, File.separator,".txt");
		
		File file = new File(fullFilePath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		return String.format("%s%s", file.getParentFile().getPath(),File.separator);
	}
	
	/**
	 * 生成提现借记文件
	 * @throws Exception 
	 */
	public void genCrdDbitFile(String date, int pageNum, String uploadDate, int dirNum) throws Exception {
		BankReqControlData controlData = new BankReqControlData();
		List<CRDReqData> list = crdService.queryData(date,"1");
		
		CRDReqData data = null;
		controlData.setAppType(CommonConstant.CRD_APP_TYPE);
		controlData.setCurrentFile(CommonConstant.CRD_DBIT_FILE_TYPE);
		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
		BankReqControlData.Records records = new BankReqControlData.Records();
		controlData.setRecords(records);
		List<Record> listRec = records.getRecords();
		Record record = null;
		if(pageNum == 0){
			pageNum = 5000;
		}

		int batchno = dirNum;
		String filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, uploadDate, batchno++);
		int seqNo = 0;
		if(null == list || list.size() == 0){
			/**
			 *生成文件 
			 */
			controlData.setTotalRecords("0");
			createFile(controlData,
					CommonConstant.CRD_APP_TYPE,
					CommonConstant.CRD_DBIT_FILE_TYPE, uploadDate, seqNo, filePath);	
			
		}
		int size = list.size();

		for(int i = 0 ; i < size ; i ++){
			record = new Record();
			data = list.get(i);			
			convert(record, data);
			if(i != 0 && i%pageNum == 0){				
				/**
				 *生成文件 
				 */				
				if(seqNo > batchNoMax){
					filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, uploadDate, batchno++);
					seqNo = 0;
				}
				controlData.setTotalRecords(""+pageNum);
				createFile(controlData, 
						CommonConstant.CRD_APP_TYPE,
						CommonConstant.CRD_DBIT_FILE_TYPE,uploadDate, seqNo, filePath);				
				listRec  = new ArrayList<Record>();
				records.setRecords(listRec);	
				seqNo ++;
			}		

			listRec.add(record);
		}
		if(listRec.size() > 0){
			/**
			 *生成文件 
			 */
			controlData.setTotalRecords(""+listRec.size());
			createFile(controlData,
					CommonConstant.CRD_APP_TYPE,
					CommonConstant.CRD_DBIT_FILE_TYPE, uploadDate, seqNo, filePath);
		}

	}
	/**
	 * 生成提现贷记文件
	 * @param date
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public void genCrdCRDTFile(String date, int pageNum, String uploadDate, int dirNum) throws Exception {
		BankReqControlData controlData = new BankReqControlData();
		List<CRDReqData> list = crdService.queryData(date,"2");
		
		CRDReqData data = null;
		controlData.setAppType(CommonConstant.CRD_APP_TYPE);
		controlData.setCurrentFile(CommonConstant.CRD_CRDT_FILE_TYPE);
		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
		BankReqControlData.Records records = new BankReqControlData.Records();
		controlData.setRecords(records);
		List<Record> listRec = records.getRecords();
		Record record = null;
		if(pageNum == 0){
			pageNum = 5000;
		}
		
		int seqNo = 0;
		int batchno = dirNum;
		String filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, uploadDate, batchno++);
		if(null == list || list.size() == 0){
			/**
			 *生成文件 
			 */
			controlData.setTotalRecords("0");
			createFile(controlData,
					CommonConstant.CRD_APP_TYPE,
					CommonConstant.CRD_CRDT_FILE_TYPE, uploadDate, seqNo, filePath);	
			
		}
		int size = list.size();
		
		for(int i = 0 ; i < size ; i ++){
			record = new Record();
			data = list.get(i);			
			convert(record, data);
			if(i != 0 && i%pageNum == 0){				
				/**
				 *生成文件 
				 */				
				if(seqNo > batchNoMax){
					filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, uploadDate, batchno++);
					seqNo = 0;
				}

				controlData.setTotalRecords(""+pageNum);
				createFile(controlData, 
						CommonConstant.CRD_APP_TYPE,
						CommonConstant.CRD_CRDT_FILE_TYPE,uploadDate, seqNo, filePath);
				
				listRec  = new ArrayList<Record>();
				records.setRecords(listRec);	
				seqNo ++;
			}		

			listRec.add(record);
		}
		if(listRec.size() > 0){
			/**
			 *生成文件 
			 */

			controlData.setTotalRecords(""+listRec.size());
			createFile(controlData,
					CommonConstant.CRD_APP_TYPE,
					CommonConstant.CRD_CRDT_FILE_TYPE, uploadDate, seqNo, filePath);
			
		}
	}
	
	/**
	 * 生成其它文件
	 * @param date
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public void genCrdOtherFile(String date, int pageNum, String uploadDate, int dirNum) throws Exception {
		BankReqControlData controlData = new BankReqControlData();
		List<CRDReqData> list = crdService.queryData(date,"3");
		
		CRDReqData data = null;
		controlData.setAppType(CommonConstant.CRD_APP_TYPE);
		controlData.setCurrentFile(CommonConstant.CRD_OTHER_FILE_TYPE);
		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
		BankReqControlData.Records records = new BankReqControlData.Records();
		controlData.setRecords(records);
		List<Record> listRec = records.getRecords();
		Record record = null;
		if(pageNum == 0){
			pageNum = 5000;
		}
		
		int seqNo = 0;
		int batchno = dirNum;
		String filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, uploadDate, batchno++);
		if(null == list || list.size() == 0){
			/**
			 *生成文件 
			 */
			controlData.setTotalRecords("0");
			createFile(controlData,
					CommonConstant.CRD_APP_TYPE,
					CommonConstant.CRD_OTHER_FILE_TYPE, uploadDate, seqNo, filePath);			
		}
		int size = list.size();
		
		for(int i = 0 ; i < size ; i ++){
			record = new Record();
			data = list.get(i);			
			convert(record, data);
			if(i != 0 && i%pageNum == 0){				
				/**
				 *生成文件 
				 */			
				if(seqNo > batchNoMax){
					filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, uploadDate, batchno++);
					seqNo = 0;
				}

				controlData.setTotalRecords(""+pageNum);
				createFile(controlData, 
						CommonConstant.CRD_APP_TYPE,
						CommonConstant.CRD_OTHER_FILE_TYPE,uploadDate, seqNo, filePath);
				listRec  = new ArrayList<Record>();
				records.setRecords(listRec);	
				seqNo ++;
			}		

			listRec.add(record);
		}
		if(listRec.size() > 0){
			/**
			 *生成文件 
			 */

			controlData.setTotalRecords(""+listRec.size());
			createFile(controlData,
					CommonConstant.CRD_APP_TYPE,
					CommonConstant.CRD_OTHER_FILE_TYPE, uploadDate, seqNo, filePath);
			
		}
	}
	
	
	/**
	 * 生成消费借记文件
	 * @throws Exception 
	 */
	public void genCRXDbitFile(String date, int pageNum, String uploadDate, int dirNum) throws Exception {
		BankReqControlData controlData = new BankReqControlData();
		List<CRXReqData> list = crxService.queryData(date,"1");
		
		CRXReqData data = null;
		controlData.setAppType(CommonConstant.CRX_APP_TYPE);
		controlData.setCurrentFile(CommonConstant.CRX_DBIT_FILE_TYPE);
		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
		BankReqControlData.Records records = new BankReqControlData.Records();
		controlData.setRecords(records);
		List<Record> listRec = records.getRecords();
		Record record = null;
		if(pageNum == 0){
			pageNum = 5000;
		}
		
		int seqNo = 0;		
		int batchno = dirNum;
		String filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, uploadDate, batchno++);
		if(null == list || list.size() == 0){
			/**
			 *生成文件 
			 */
			controlData.setTotalRecords("0");
			createFile(controlData,
					CommonConstant.CRX_APP_TYPE,
					CommonConstant.CRX_DBIT_FILE_TYPE, uploadDate, seqNo, filePath);	
		}
		int size = list.size();

		for(int i = 0 ; i < size ; i ++){
			record = new Record();
			data = list.get(i);			
			convert(record, data);
			if(i != 0 && i%pageNum == 0){				
				/**
				 *生成文件 
				 */			
				if(seqNo > batchNoMax){
					filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, uploadDate, batchno++);
					seqNo = 0;
				}

				controlData.setTotalRecords(""+pageNum);
				createFile(controlData, 
						CommonConstant.CRX_APP_TYPE,
						CommonConstant.CRX_DBIT_FILE_TYPE,uploadDate, seqNo, filePath);				
				listRec  = new ArrayList<Record>();
				records.setRecords(listRec);	
				seqNo ++;
			}		

			listRec.add(record);
		}
		if(listRec.size() > 0){
			/**
			 *生成文件 
			 */

			controlData.setTotalRecords(""+listRec.size());
			createFile(controlData,
					CommonConstant.CRX_APP_TYPE,
					CommonConstant.CRX_DBIT_FILE_TYPE, uploadDate, seqNo, filePath);
			
		}
	}
	/**
	 * 生成消费贷记文件
	 * @param date
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public void genCRXCrdtFile(String date, int pageNum, String uploadDate, int dirNum) throws Exception {
		BankReqControlData controlData = new BankReqControlData();
		List<CRXReqData> list = crxService.queryData(date,"2");
		
		CRXReqData data = null;
		controlData.setAppType(CommonConstant.CRX_APP_TYPE);
		controlData.setCurrentFile(CommonConstant.CRX_CRDT_FILE_TYPE);
		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
		BankReqControlData.Records records = new BankReqControlData.Records();
		controlData.setRecords(records);
		List<Record> listRec = records.getRecords();
		Record record = null;
		if(pageNum == 0){
			pageNum = 5000;
		}
		
		int seqNo = 0;	
		int batchno = dirNum ;
		String filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, uploadDate, batchno++);
		
		if(null == list || list.size() == 0){
			/**
			 *生成文件 
			 */
			controlData.setTotalRecords("0");
			createFile(controlData,
					CommonConstant.CRX_APP_TYPE,
					CommonConstant.CRX_CRDT_FILE_TYPE, uploadDate, seqNo, filePath);	
		}
		int size = list.size();

		for(int i = 0 ; i < size ; i ++){
			record = new Record();
			data = list.get(i);			
			convert(record, data);
			if(i != 0 && i%pageNum == 0){				
				/**
				 *生成文件 
				 */				
				if(seqNo > batchNoMax){
					filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, uploadDate, batchno++);
					seqNo = 0;					
				}

				controlData.setTotalRecords(""+pageNum);
				createFile(controlData, 
						CommonConstant.CRX_APP_TYPE,
						CommonConstant.CRX_CRDT_FILE_TYPE,uploadDate, seqNo, filePath);
				
				listRec  = new ArrayList<Record>();
				records.setRecords(listRec);	
				seqNo ++;
			}		

			listRec.add(record);
		}
		if(listRec.size() > 0){
			/**
			 *生成文件 
			 */

			controlData.setTotalRecords(""+listRec.size());
			createFile(controlData,
					CommonConstant.CRX_APP_TYPE,
					CommonConstant.CRX_CRDT_FILE_TYPE, uploadDate, seqNo, filePath);
			
		}
	}
	
	/**
	 * 生成消费其它文件
	 * @param date
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public void genCRXOtherFile(String date, int pageNum, String uploadDate, int dirNum) throws Exception {
		BankReqControlData controlData = new BankReqControlData();
		List<CRXReqData> list = crxService.queryData(date,"3");
		
		CRXReqData data = null;
		controlData.setAppType(CommonConstant.CRX_APP_TYPE);
		controlData.setCurrentFile(CommonConstant.CRX_OTHER_FILE_TYPE);
		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
		BankReqControlData.Records records = new BankReqControlData.Records();
		controlData.setRecords(records);
		List<Record> listRec = records.getRecords();
		Record record = null;
		if(pageNum == 0){
			pageNum = 5000;
		}
		
		int seqNo = 0;
		int batchno = dirNum;
		String filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, uploadDate, batchno++);
		if(null == list || list.size() == 0){
			/**
			 *生成文件 
			 */
			controlData.setTotalRecords("0");
			createFile(controlData,
					CommonConstant.CRX_APP_TYPE,
					CommonConstant.CRX_OTHER_FILE_TYPE, uploadDate, seqNo, filePath);	
		}
		int size = list.size();
		
		for(int i = 0 ; i < size ; i ++){
			record = new Record();
			data = list.get(i);			
			convert(record, data);
			if(i != 0 && i%pageNum == 0){				
				/**
				 *生成文件 
				 */				
				if(seqNo > batchNoMax){
					filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, uploadDate, batchno++);
					seqNo = 0;
				}
				controlData.setTotalRecords(""+pageNum);
				createFile(controlData, 
						CommonConstant.CRX_APP_TYPE,
						CommonConstant.CRX_OTHER_FILE_TYPE,uploadDate, seqNo, filePath );
				
				listRec  = new ArrayList<Record>();
				records.setRecords(listRec);	
				seqNo ++;
			}		

			listRec.add(record);
		}
		if(listRec.size() > 0){
			/**
			 *生成文件 
			 */

			controlData.setTotalRecords(""+listRec.size());
			createFile(controlData,
					CommonConstant.CRX_APP_TYPE,
					CommonConstant.CRX_OTHER_FILE_TYPE, uploadDate, seqNo, filePath);
		}
		
	}
	
	
	private void convert(Record record, CRDReqData data){
		if(null == data){
			return;
		}
		record.setAcctNo(data.getAcctno());
		record.setBankCode(data.getBankCode());
		record.setBankSelfNum(data.getBankSelfNum());
		record.setBizDealTime(data.getBizDealTime());
		record.setBranchSafecode(data.getBranchSafecode());
		record.setCardChnlCode(data.getCardChnlCode());
		record.setCardSelfNum(data.getCardSelfNum());
		record.setCardTypeCode(data.getCardTypeCode());
		record.setCertTypeCode(data.getCertTypeCode());
		record.setCountryCode(data.getCountryCode());
		record.setIdCode(data.getIdCode());
		record.setJyAmt(AdpUtil.formatAmtField(data.getJyAmt()));
		record.setJyCcyCode(data.getJyCcyCode());
		record.setMccCode(data.getMccCode());
		record.setOperTypeCode(data.getOperTypeCode());
		record.setPersonName(convertEncode(data.getPersonName()));
		record.setPtyCountryCode(data.getPtyCountryCode());
		record.setQsAmtRmb(AdpUtil.formatAmtField(data.getQsAmtRmb()));
		record.setReasonCode(data.getReasonCode() == null ? "" : data.getReasonCode());
		record.setRefNo(data.getRefno());
	}

	private void convert(Record record, CRXReqData data){
		if(null == data){
			return;
		}
		record.setAcctNo(data.getAcctno());
		record.setBankCode(data.getBankCode());
		record.setBankSelfNum(data.getBankSelfNum());
		record.setBizDealTime(data.getBizDealTime());
		record.setBranchSafecode(data.getBranchSafecode());
		record.setCardChnlCode(data.getCardChnlCode());
		record.setCardSelfNum(data.getCardSelfNum());
		record.setCardTypeCode(data.getCardTypeCode());
		record.setCertTypeCode(data.getCertTypeCode());
		record.setCountryCode(data.getCountryCode());
		record.setIdCode(data.getIdCode());
		record.setJyAmt(AdpUtil.formatAmtField(data.getJyAmt()));
		record.setJyCcyCode(data.getJyCcyCode());
		record.setMccCode(data.getMccCode());
		record.setOperTypeCode(data.getOperTypeCode());
		record.setPersonName(convertEncode(data.getPersonName()));
		record.setPtyCountryCode(data.getPtyCountryCode());
		record.setQsAmtRmb(AdpUtil.formatAmtField(data.getQsAmtRmb()));
		record.setReasonCode(data.getReasonCode() == null ? "" : data.getReasonCode());
		record.setRefNo(data.getRefno());
		record.setShName(convertEncode(data.getShName()));
		record.setJyChnl(data.getJyChnl());
	}
	
	private String convertEncode(String str){
		try {
			/*
			<  	:	&lt;
			&	:	&amp;
			>	:	&gt;
			"	:	&quot;
			'	:	&apos;
			new String(str.getBytes(),CommonConstant.AQC_ENCODING)
			 */		
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("\"", "&quot;");
			str = str.replaceAll("'", "&apos;");
			return str;
		} catch (Exception e) {
			return str;
		}
	}
	/**
	 * 生成数据文件文件
	 * @param data
	 * @param appType
	 * @param fileType
	 * @param date
	 * @param seqNo
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	private String createFile(BankReqControlData data, String appType, String fileType, String date, int seqNo, String filePath) throws Exception{
		XmlBinder<BankReqControlData> binder = new XmlBinder<BankReqControlData>(BankReqControlData.class);
		try {
			String xmlStr = binder.marshalString(data, CommonConstant.AQC_ENCODING);
			//数据类型     +	12位金融机构代码 	+ 	6位日期	+	2位序号	+	.xml
			String fileName = String.format("%s%s%s%s%s%s", 
					appType,
					fileType,
					CommonUtil.getKeyValue(CommonConstant.BANK_CODE_KEY),
					date.substring(2),
					String.format("%02d", seqNo),
					CommonConstant.XML_EXT);
			String fullFilePath = String.format("%s%s%s",filePath, File.separator, fileName);
			
			createXmlFile(xmlStr, fullFilePath);
			return fileName;
		} catch (Exception e) {
			log.error("create file fail:",e);
			throw e;
		}
		
		
	}
	/**
	 * 控制接口文件
	 * @param data
	 * @param appType
	 * @param fileType
	 * @param dirName
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public String createFile(ReqControlType data, String appType, String fileType, String dirName, String filePath) throws Exception{
		XmlBinder<ReqControlType> binder = new XmlBinder<ReqControlType>(ReqControlType.class);
		try {
			String xmlStr = binder.marshalString(data, CommonConstant.AQC_ENCODING);
			
			String fullFilePath = String.format("%s%s%s%s",filePath, File.separator, dirName, CommonConstant.XML_EXT);

			createXmlFile(xmlStr, fullFilePath);
			return fullFilePath;
		} catch (Exception e) {
			log.error("create file fail:",e);
			throw e;
		}
		
		
	}
	
	
	
	
    private void createXmlFile(String xmlStr, String fileNameFullPath) throws Exception{
		
		/**
		 * 生成文件
		 */
		File file = null;
		BufferedWriter writer = null;
		try{
			file = new File(fileNameFullPath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(file.exists()){
				file.delete();
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), CommonConstant.AQC_ENCODING));
			writer.write(xmlStr);
		}catch(Exception e){
			if(file != null && file.exists()){
				file.delete();
			}
			throw e;
		}finally{
			try{
				if(writer != null){
					writer.flush();
					writer.close();
				}
			}catch(Exception e){}
			
		}
		
	}
    
    public void createTokenFile( String fileNameFullPath) throws Exception{
		
		/**
		 * 生成文件
		 */
		File file = null;
		BufferedWriter writer = null;
		try{
			file = new File(fileNameFullPath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(file.exists()){
				file.delete();
			}
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(" ");
		}catch(Exception e){
			if(file != null && file.exists()){
				file.delete();
			}
			throw e;
		}finally{
			try{
				if(writer != null){
					writer.flush();
					writer.close();
				}
			}catch(Exception e){}
			
		}
		
	}
    
    public void recreateErrBackFile(String yesterday, String today, int pageNum) throws Exception{
    	
		int crddirNum = 0;
		int crxdirNum = 0;


		//提现借记数据
		List<FeedBackField> listcrdDbit = ffService.getFeedBackField(yesterday,
				String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_DBIT_FILE_TYPE));
		getCrdDbitErrFile(listcrdDbit, today, pageNum, crddirNum);
		//提现贷记数据
		List<FeedBackField> listcrdCrdt = ffService.getFeedBackField(yesterday,
				String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CRDT_FILE_TYPE));
		getCrdCrdtErrFile(listcrdCrdt, today, pageNum, crddirNum);
		//提现其它数据
		List<FeedBackField> listcrdOther = ffService.getFeedBackField(yesterday,
				String.format("%s%s", CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_OTHER_FILE_TYPE));
		getCrdOtherErrFile(listcrdOther, today, pageNum, crddirNum);
		//消费借记数据
		List<FeedBackField> listcrxDbit = ffService.getFeedBackField(yesterday,
				String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_DBIT_FILE_TYPE));
		getCrxDbitErrFile(listcrxDbit, today, pageNum, crxdirNum);
		//消费贷记数据
		List<FeedBackField> listcrxCrdt = ffService.getFeedBackField(yesterday,
				String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CRDT_FILE_TYPE));
		getCrxCrdtErrFile(listcrxCrdt, today, pageNum, crxdirNum);
		//消费其它数据
		List<FeedBackField> listcrxOther = ffService.getFeedBackField(yesterday,
				String.format("%s%s", CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_OTHER_FILE_TYPE));
		getCrxOtherErrFile(listcrxOther, today, pageNum, crxdirNum);
    	
    }
    
    private void getCrdDbitErrFile(List<FeedBackField> listcrdDbit, String today, int pageNum, int batchno) throws Exception{
    	
    	try{
    		if(null == listcrdDbit || listcrdDbit.size() == 0){
        		return;
        	}
        	int size = listcrdDbit.size();
        	FeedBackField field = null;
        	BankReqControlData controlData = new BankReqControlData();
    		controlData.setAppType(CommonConstant.CRD_APP_TYPE);
    		controlData.setCurrentFile(CommonConstant.CRD_DBIT_FILE_TYPE);
    		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
    		BankReqControlData.Records records = new BankReqControlData.Records();
    		controlData.setRecords(records);
    		List<Record> listRec = records.getRecords();
    		Record record = null;
    		if(pageNum == 0){
    			pageNum = 5000;
    		}    		
    		int seqNo = 0;
    		String filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, 
    				CommonConstant.CRD_CONTROL_FILE_TYPE, today, batchno++);
        	for(int i = 0 ; i < size ; i ++){
        		field = listcrdDbit.get(i);
        		record = new Record();
        		CRDReqData crdreqdata = crdService.queryCRDReqData(field.getBussNo());
        		convert(record, crdreqdata);
        		if(i != 0 && i%pageNum == 0){				
    				/**
    				 *生成文件 
    				 */				
    				if(seqNo > batchNoMax){
    					filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, today, batchno++);
    					seqNo = 0;
    				}
    				controlData.setTotalRecords(""+pageNum);
    				createFile(controlData, 
    						CommonConstant.CRD_APP_TYPE,
    						CommonConstant.CRD_DBIT_FILE_TYPE,today, seqNo, filePath );
    				
    				listRec  = new ArrayList<Record>();
    				records.setRecords(listRec);	
    				seqNo ++;
    			}		
        		listRec.add(record);
        		
        	}
        	if(listRec.size() > 0){
    			/**
    			 *生成文件 
    			 */
    			controlData.setTotalRecords(""+listRec.size());
    			createFile(controlData,
    					CommonConstant.CRD_APP_TYPE,
    					CommonConstant.CRD_DBIT_FILE_TYPE, today, seqNo, filePath);
    			
    		}    		
    	}catch(Exception e){
    		log.error(" create err back file fail ", e);
    		throw e;
    	}    	
    	
    }
    
    private void getCrdCrdtErrFile(List<FeedBackField> listcrdCrdt, String today, int pageNum, int batchno) throws Exception{
    	
    	try{
    		if(null == listcrdCrdt || listcrdCrdt.size() == 0){
        		return;
        	}
        	int size = listcrdCrdt.size();
        	FeedBackField field = null;
        	BankReqControlData controlData = new BankReqControlData();
    		controlData.setAppType(CommonConstant.CRD_APP_TYPE);
    		controlData.setCurrentFile(CommonConstant.CRD_CRDT_FILE_TYPE);
    		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
    		BankReqControlData.Records records = new BankReqControlData.Records();
    		controlData.setRecords(records);
    		List<Record> listRec = records.getRecords();
    		Record record = null;
    		if(pageNum == 0){
    			pageNum = 5000;
    		}    		
    		int seqNo = 0;
    		String filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, 
    				CommonConstant.CRD_CONTROL_FILE_TYPE, today, batchno++);
        	for(int i = 0 ; i < size ; i ++){
        		field = listcrdCrdt.get(i);
        		record = new Record();
        		CRDReqData crdreqdata = crdService.queryCRDReqData(field.getBussNo());
        		convert(record, crdreqdata);
        		if(i != 0 && i%pageNum == 0){				
    				/**
    				 *生成文件 
    				 */				
    				if(seqNo > batchNoMax){
    					filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, today, batchno++);
    					seqNo = 0;
    				}
    				controlData.setTotalRecords(""+pageNum);
    				createFile(controlData, 
    						CommonConstant.CRD_APP_TYPE,
    						CommonConstant.CRD_CRDT_FILE_TYPE,today, seqNo, filePath );
    				
    				listRec  = new ArrayList<Record>();
    				records.setRecords(listRec);	
    				seqNo ++;
    			}		
        		listRec.add(record);
        		
        	}
        	if(listRec.size() > 0){
    			/**
    			 *生成文件 
    			 */
    			controlData.setTotalRecords(""+listRec.size());
    			createFile(controlData,
    					CommonConstant.CRD_APP_TYPE,
    					CommonConstant.CRD_CRDT_FILE_TYPE, today, seqNo, filePath);
    			
    		}    		
    	}catch(Exception e){
    		log.error(" create err back file fail ", e);
    		throw e;
    	}    	
    	
    }
    
    
    private void getCrdOtherErrFile(List<FeedBackField> listcrdOther, String today, int pageNum, int batchno) throws Exception{
    	
    	try{
    		if(null == listcrdOther || listcrdOther.size() == 0){
        		return;
        	}
        	int size = listcrdOther.size();
        	FeedBackField field = null;
        	BankReqControlData controlData = new BankReqControlData();
    		controlData.setAppType(CommonConstant.CRD_APP_TYPE);
    		controlData.setCurrentFile(CommonConstant.CRD_OTHER_FILE_TYPE);
    		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
    		BankReqControlData.Records records = new BankReqControlData.Records();
    		controlData.setRecords(records);
    		List<Record> listRec = records.getRecords();
    		Record record = null;
    		if(pageNum == 0){
    			pageNum = 5000;
    		}    		
    		int seqNo = 0;
    		String filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, 
    				CommonConstant.CRD_CONTROL_FILE_TYPE, today, batchno++);
        	for(int i = 0 ; i < size ; i ++){
        		field = listcrdOther.get(i);
        		record = new Record();
        		CRDReqData crdreqdata = crdService.queryCRDReqData(field.getBussNo());
        		convert(record, crdreqdata);
        		if(i != 0 && i%pageNum == 0){				
    				/**
    				 *生成文件 
    				 */				
    				if(seqNo > batchNoMax){
    					filePath = createBatchNoDir(CommonConstant.CRD_APP_TYPE, CommonConstant.CRD_CONTROL_FILE_TYPE, today, batchno++);
    					seqNo = 0;
    				}
    				controlData.setTotalRecords(""+pageNum);
    				createFile(controlData, 
    						CommonConstant.CRD_APP_TYPE,
    						CommonConstant.CRD_OTHER_FILE_TYPE,today, seqNo, filePath );
    				
    				listRec  = new ArrayList<Record>();
    				records.setRecords(listRec);	
    				seqNo ++;
    			}		
        		listRec.add(record);
        		
        	}
        	if(listRec.size() > 0){
    			/**
    			 *生成文件 
    			 */
    			controlData.setTotalRecords(""+listRec.size());
    			createFile(controlData,
    					CommonConstant.CRD_APP_TYPE,
    					CommonConstant.CRD_OTHER_FILE_TYPE, today, seqNo, filePath);
    			
    		}    		
    	}catch(Exception e){
    		log.error(" create err back file fail ", e);
    		throw e;
    	}    	
    	
    }
    /**
     * 消费借记错误文件
     * @param listcrxDbit
     * @param today
     * @param pageNum
     * @param batchno
     * @throws Exception
     */
    private void getCrxDbitErrFile(List<FeedBackField> listcrxDbit, String today, int pageNum, int batchno) throws Exception{
    	
    	try{
    		if(null == listcrxDbit || listcrxDbit.size() == 0){
        		return;
        	}
        	int size = listcrxDbit.size();
        	FeedBackField field = null;
        	BankReqControlData controlData = new BankReqControlData();
    		controlData.setAppType(CommonConstant.CRX_APP_TYPE);
    		controlData.setCurrentFile(CommonConstant.CRX_DBIT_FILE_TYPE);
    		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
    		BankReqControlData.Records records = new BankReqControlData.Records();
    		controlData.setRecords(records);
    		List<Record> listRec = records.getRecords();
    		Record record = null;
    		if(pageNum == 0){
    			pageNum = 5000;
    		}    		
    		int seqNo = 0;
    		String filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, 
    				CommonConstant.CRX_CONTROL_FILE_TYPE, today, batchno++);
        	for(int i = 0 ; i < size ; i ++){
        		field = listcrxDbit.get(i);
        		record = new Record();
        		CRXReqData crdreqdata = crxService.queryCRXReqData(field.getBussNo());
        		convert(record, crdreqdata);
        		if(i != 0 && i%pageNum == 0){				
    				/**
    				 *生成文件 
    				 */				
    				if(seqNo > batchNoMax){
    					filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, today, batchno++);
    					seqNo = 0;
    				}
    				controlData.setTotalRecords(""+pageNum);
    				createFile(controlData, 
    						CommonConstant.CRX_APP_TYPE,
    						CommonConstant.CRX_DBIT_FILE_TYPE,today, seqNo, filePath );
    				
    				listRec  = new ArrayList<Record>();
    				records.setRecords(listRec);	
    				seqNo ++;
    			}		
        		listRec.add(record);
        		
        	}
        	if(listRec.size() > 0){
    			/**
    			 *生成文件 
    			 */
    			controlData.setTotalRecords(""+listRec.size());
    			createFile(controlData,
    					CommonConstant.CRX_APP_TYPE,
    					CommonConstant.CRX_DBIT_FILE_TYPE, today, seqNo, filePath);
    			
    		}    		
    	}catch(Exception e){
    		log.error(" create err back file fail ", e);
    		throw e;
    	}    	
    	
    }
    /**
     * 消费贷记错误文件
     * @param listcrxCrdt
     * @param today
     * @param pageNum
     * @param batchno
     * @throws Exception
     */
    private void getCrxCrdtErrFile(List<FeedBackField> listcrxCrdt, String today, int pageNum, int batchno) throws Exception{
    	
    	try{
    		if(null == listcrxCrdt || listcrxCrdt.size() == 0){
        		return;
        	}
        	int size = listcrxCrdt.size();
        	FeedBackField field = null;
        	BankReqControlData controlData = new BankReqControlData();
    		controlData.setAppType(CommonConstant.CRX_APP_TYPE);
    		controlData.setCurrentFile(CommonConstant.CRX_CRDT_FILE_TYPE);
    		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
    		BankReqControlData.Records records = new BankReqControlData.Records();
    		controlData.setRecords(records);
    		List<Record> listRec = records.getRecords();
    		Record record = null;
    		if(pageNum == 0){
    			pageNum = 5000;
    		}    		
    		int seqNo = 0;
    		String filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, 
    				CommonConstant.CRX_CONTROL_FILE_TYPE, today, batchno++);
        	for(int i = 0 ; i < size ; i ++){
        		field = listcrxCrdt.get(i);
        		record = new Record();
        		CRXReqData crdreqdata = crxService.queryCRXReqData(field.getBussNo());
        		convert(record, crdreqdata);
        		if(i != 0 && i%pageNum == 0){				
    				/**
    				 *生成文件 
    				 */				
    				if(seqNo > batchNoMax){
    					filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, today, batchno++);
    					seqNo = 0;
    				}
    				controlData.setTotalRecords(""+pageNum);
    				createFile(controlData, 
    						CommonConstant.CRX_APP_TYPE,
    						CommonConstant.CRX_CRDT_FILE_TYPE,today, seqNo, filePath );
    				
    				listRec  = new ArrayList<Record>();
    				records.setRecords(listRec);	
    				seqNo ++;
    			}		
        		listRec.add(record);
        		
        	}
        	if(listRec.size() > 0){
    			/**
    			 *生成文件 
    			 */
    			controlData.setTotalRecords(""+listRec.size());
    			createFile(controlData,
    					CommonConstant.CRX_APP_TYPE,
    					CommonConstant.CRX_CRDT_FILE_TYPE, today, seqNo, filePath);
    			
    		}    		
    	}catch(Exception e){
    		log.error(" create err back file fail ", e);
    		throw e;
    	}    	
    	
    }
    
    /**
     * 消费错误其它文件
     * @param listcrxOther
     * @param today
     * @param pageNum
     * @param batchno
     * @throws Exception
     */
    private void getCrxOtherErrFile(List<FeedBackField> listcrxOther, String today, int pageNum, int batchno) throws Exception{
    	
    	try{
    		if(null == listcrxOther || listcrxOther.size() == 0){
        		return;
        	}
        	int size = listcrxOther.size();
        	FeedBackField field = null;
        	BankReqControlData controlData = new BankReqControlData();
    		controlData.setAppType(CommonConstant.CRX_APP_TYPE);
    		controlData.setCurrentFile(CommonConstant.CRX_OTHER_FILE_TYPE);
    		controlData.setInOut(CommonConstant.BANK_INPUT_TYPE);
    		BankReqControlData.Records records = new BankReqControlData.Records();
    		controlData.setRecords(records);
    		List<Record> listRec = records.getRecords();
    		Record record = null;
    		if(pageNum == 0){
    			pageNum = 5000;
    		}    		
    		int seqNo = 0;
    		String filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, 
    				CommonConstant.CRX_CONTROL_FILE_TYPE, today, batchno++);
        	for(int i = 0 ; i < size ; i ++){
        		field = listcrxOther.get(i);
        		record = new Record();
        		CRXReqData crdreqdata = crxService.queryCRXReqData(field.getBussNo());
        		convert(record, crdreqdata);
        		if(i != 0 && i%pageNum == 0){				
    				/**
    				 *生成文件 
    				 */				
    				if(seqNo > batchNoMax){
    					filePath = createBatchNoDir(CommonConstant.CRX_APP_TYPE, CommonConstant.CRX_CONTROL_FILE_TYPE, today, batchno++);
    					seqNo = 0;
    				}
    				controlData.setTotalRecords(""+pageNum);
    				createFile(controlData, 
    						CommonConstant.CRX_APP_TYPE,
    						CommonConstant.CRX_OTHER_FILE_TYPE,today, seqNo, filePath );
    				
    				listRec  = new ArrayList<Record>();
    				records.setRecords(listRec);	
    				seqNo ++;
    			}		
        		listRec.add(record);
        		
        	}
        	if(listRec.size() > 0){
    			/**
    			 *生成文件 
    			 */
    			controlData.setTotalRecords(""+listRec.size());
    			createFile(controlData,
    					CommonConstant.CRX_APP_TYPE,
    					CommonConstant.CRX_OTHER_FILE_TYPE, today, seqNo, filePath);
    			
    		}    		
    	}catch(Exception e){
    		log.error(" create err back file fail ", e);
    		throw e;
    	}    	
    	
    }
	
}
