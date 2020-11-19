package com.kalvin.kvf.modules.mts.entity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @date：2020年10月16日17:45:16
 * @purpose：
 * @address：
 * @auth：jingjin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "MSG")
public class BankReqControlData {

	/**
	 * 应用类型
	 */
	@XmlElement(name = "APPTYPE")
	private String appType;
	/**
	 * 当前文件类型
	 */
	@XmlElement(name = "CURRENTFILE")
	private String currentFile;
	/**
	 * 输入/输出
	 */
	@XmlElement(name = "INOUT")
	private String inOut;
	/**
	 * 总记录数
	 */
	@XmlElement(name = "TOTALRECORDS")
	private String totalRecords;
	
	/**
	 * 总记录数
	 */
	@XmlElement(name = "RECORDS")
	private Records Records;
	

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Records{
		/**
		 * 总记录数
		 */
		@XmlElement(name = "REC")
		private List<Record> records;

		/**
		 * @return the records
		 */
		public List<Record> getRecords() {
			return this.records == null ? this.records = new ArrayList<Record>() : this.records;
		}

		/**
		 * @param records the records to set
		 */
		public void setRecords(List<Record> records) {
			this.records = records;
		}
		
		
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Record{
		
		/**
		 * 操作类型
		 */
		@XmlElement(name = "OPER_TYPE_CODE")
		private String operTypeCode;
		/**
		 * 修改/删除原因
		 */
		@XmlElement(name = "REASON_CODE")
		private String reasonCode;
		/**
		 * 业务参号
		 */
		@XmlElement(name = "REFNO")
		private String refNo;
		/**
		 * 持卡人身份证件类型
		 */
		@XmlElement(name = "CERT_TYPE_CODE")
		private String certTypeCode;
		/**
		 * 持卡人国家/地区
		 */
		@XmlElement(name = "PTY_COUNTRY_CODE")
		private String ptyCountryCode;
		/**
		 * 身份证件号码
		 */
		@XmlElement(name = "ID_CODE")
		private String idCode;
		/**
		 * 持卡人姓名
		 */
		@XmlElement(name = "PERSON_NAME")
		private String personName;
		/**
		 * 卡号
		 */
		@XmlElement(name = "ACCTNO")
		private String acctNo;
		/**
		 * 交易货币币种
		 */
		@XmlElement(name = "JY_CCY_CODE")
		private String jyCcyCode;
		/**
		 * 交易货币金额
		 */
		@XmlElement(name = "JY_AMT")
		private String jyAmt;
		/**
		 * 交易货币折人民币金额
		 */
		@XmlElement(name = "QS_AMT_RMB")
		private String qsAmtRmb;
		/**
		 * MCC码
		 */
		@XmlElement(name = "MCC_CODE")
		private String mccCode;
		/**
		 * 银行卡类型
		 */
		@XmlElement(name = "CARD_TYPE_CODE")
		private String cardTypeCode;
		/**
		 * 银行卡清算渠道
		 */
		@XmlElement(name = "CARD_CHNL_CODE")
		private String cardChnlCode;
		/**
		 * 发卡行金融机构代码
		 */
		@XmlElement(name = "BANK_CODE")
		private String bankCode;
		/**
		 * 发卡网点所在地外汇局代码
		 */
		@XmlElement(name = "BRANCH_SAFECODE")
		private String branchSafecode;
		/**
		 * 交易授权日期及时间
		 */
		@XmlElement(name = "BIZ_DEAL_TIME")
		private String bizDealTime;
		/**
		 * 交易国家或地区
		 */
		@XmlElement(name = "COUNTRY_CODE")
		private String countryCode;
		/**
		 * 银行内部流水号
		 */
		@XmlElement(name = "BANK_SELF_NUM")
		private String bankSelfNum;
		/**
		 * 卡组织单号
		 */
		@XmlElement(name = "CARD_SELF_NUM")
		private String cardSelfNum;
		/**
		 * 交易商户名称
		 */
		@XmlElement(name = "SH_NAME")
		private String shName;
		/**
		 * 交易商户类型
		 */
		@XmlElement(name = "JY_CHNL")
		private String jyChnl;
		
		/**
		 * @return the shName
		 */
		public String getShName() {
			return shName;
		}
		/**
		 * @param shName the shName to set
		 */
		public void setShName(String shName) {
			this.shName = shName;
		}
		/**
		 * @return the jyChnl
		 */
		public String getJyChnl() {
			return jyChnl;
		}
		/**
		 * @param jyChnl the jyChnl to set
		 */
		public void setJyChnl(String jyChnl) {
			this.jyChnl = jyChnl;
		}
		/**
		 * @return the operTypeCode
		 */
		public String getOperTypeCode() {
			return operTypeCode;
		}
		/**
		 * @param operTypeCode the operTypeCode to set
		 */
		public void setOperTypeCode(String operTypeCode) {
			this.operTypeCode = operTypeCode;
		}
		/**
		 * @return the reasonCode
		 */
		public String getReasonCode() {
			return reasonCode;
		}
		/**
		 * @param reasonCode the reasonCode to set
		 */
		public void setReasonCode(String reasonCode) {
			this.reasonCode = reasonCode;
		}
		/**
		 * @return the refNo
		 */
		public String getRefNo() {
			return refNo;
		}
		/**
		 * @param refNo the refNo to set
		 */
		public void setRefNo(String refNo) {
			this.refNo = refNo;
		}
		/**
		 * @return the certTypeCode
		 */
		public String getCertTypeCode() {
			return certTypeCode;
		}
		/**
		 * @param certTypeCode the certTypeCode to set
		 */
		public void setCertTypeCode(String certTypeCode) {
			this.certTypeCode = certTypeCode;
		}
		/**
		 * @return the ptyCountryCode
		 */
		public String getPtyCountryCode() {
			return ptyCountryCode;
		}
		/**
		 * @param ptyCountryCode the ptyCountryCode to set
		 */
		public void setPtyCountryCode(String ptyCountryCode) {
			this.ptyCountryCode = ptyCountryCode;
		}
		/**
		 * @return the idCode
		 */
		public String getIdCode() {
			return idCode;
		}
		/**
		 * @param idCode the idCode to set
		 */
		public void setIdCode(String idCode) {
			this.idCode = idCode;
		}
		/**
		 * @return the personName
		 */
		public String getPersonName() {
			return personName;
		}
		/**
		 * @param personName the personName to set
		 */
		public void setPersonName(String personName) {
			this.personName = personName;
		}
		/**
		 * @return the acctNo
		 */
		public String getAcctNo() {
			return acctNo;
		}
		/**
		 * @param acctNo the acctNo to set
		 */
		public void setAcctNo(String acctNo) {
			this.acctNo = acctNo;
		}
		/**
		 * @return the jyCcyCode
		 */
		public String getJyCcyCode() {
			return jyCcyCode;
		}
		/**
		 * @param jyCcyCode the jyCcyCode to set
		 */
		public void setJyCcyCode(String jyCcyCode) {
			this.jyCcyCode = jyCcyCode;
		}
		/**
		 * @return the jyAmt
		 */
		public String getJyAmt() {
			return jyAmt;
		}
		/**
		 * @param jyAmt the jyAmt to set
		 */
		public void setJyAmt(String jyAmt) {
			this.jyAmt = jyAmt;
		}
		/**
		 * @return the qsAmtRmb
		 */
		public String getQsAmtRmb() {
			return qsAmtRmb;
		}
		/**
		 * @param qsAmtRmb the qsAmtRmb to set
		 */
		public void setQsAmtRmb(String qsAmtRmb) {
			this.qsAmtRmb = qsAmtRmb;
		}
		/**
		 * @return the mccCode
		 */
		public String getMccCode() {
			return mccCode;
		}
		/**
		 * @param mccCode the mccCode to set
		 */
		public void setMccCode(String mccCode) {
			this.mccCode = mccCode;
		}
		/**
		 * @return the cardTypeCode
		 */
		public String getCardTypeCode() {
			return cardTypeCode;
		}
		/**
		 * @param cardTypeCode the cardTypeCode to set
		 */
		public void setCardTypeCode(String cardTypeCode) {
			this.cardTypeCode = cardTypeCode;
		}
		/**
		 * @return the cardChnlCode
		 */
		public String getCardChnlCode() {
			return cardChnlCode;
		}
		/**
		 * @param cardChnlCode the cardChnlCode to set
		 */
		public void setCardChnlCode(String cardChnlCode) {
			this.cardChnlCode = cardChnlCode;
		}
		/**
		 * @return the bankCode
		 */
		public String getBankCode() {
			return bankCode;
		}
		/**
		 * @param bankCode the bankCode to set
		 */
		public void setBankCode(String bankCode) {
			this.bankCode = bankCode;
		}
		/**
		 * @return the branchSafecode
		 */
		public String getBranchSafecode() {
			return branchSafecode;
		}
		/**
		 * @param branchSafecode the branchSafecode to set
		 */
		public void setBranchSafecode(String branchSafecode) {
			this.branchSafecode = branchSafecode;
		}
		/**
		 * @return the bizDealTime
		 */
		public String getBizDealTime() {
			return bizDealTime;
		}
		/**
		 * @param bizDealTime the bizDealTime to set
		 */
		public void setBizDealTime(String bizDealTime) {
			this.bizDealTime = bizDealTime;
		}
		/**
		 * @return the countryCode
		 */
		public String getCountryCode() {
			return countryCode;
		}
		/**
		 * @param countryCode the countryCode to set
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
		/**
		 * @return the bankSelfNum
		 */
		public String getBankSelfNum() {
			return bankSelfNum;
		}
		/**
		 * @param bankSelfNum the bankSelfNum to set
		 */
		public void setBankSelfNum(String bankSelfNum) {
			this.bankSelfNum = bankSelfNum;
		}
		/**
		 * @return the cardSelfNum
		 */
		public String getCardSelfNum() {
			return cardSelfNum;
		}
		/**
		 * @param cardSelfNum the cardSelfNum to set
		 */
		public void setCardSelfNum(String cardSelfNum) {
			this.cardSelfNum = cardSelfNum;
		}
		
	}

	/**
	 * @return the appType
	 */
	public String getAppType() {
		return appType;
	}

	/**
	 * @param appType the appType to set
	 */
	public void setAppType(String appType) {
		this.appType = appType;
	}

	/**
	 * @return the currentFile
	 */
	public String getCurrentFile() {
		return currentFile;
	}

	/**
	 * @param currentFile the currentFile to set
	 */
	public void setCurrentFile(String currentFile) {
		this.currentFile = currentFile;
	}

	/**
	 * @return the inOut
	 */
	public String getInOut() {
		return inOut;
	}

	/**
	 * @param inOut the inOut to set
	 */
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}

	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the records
	 */
	public Records getRecords() {
		return Records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(Records records) {
		Records = records;
	}
	
	
	
}
