package com.kalvin.kvf.modules.mts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kalvin.kvf.common.entity.BaseEntity;

/**
 * @date：2020年10月16日17:45:16
 * @purpose：
 * @address：
 * @auth：jingjin
 */
@TableName("dc_mts_crd")
public class CRDReqData extends BaseEntity {

	/**
	 * 操作类型
	 */
	private String operTypeCode;
	
	/**
	 * 修改/删除原因
	 */
	private String reasonCode;
	/**
	 * 业务参号
	 */
	private String refno;
	/**
	 * 持卡人身份证件类型
	 */
	private String certTypeCode;
	/**
	 * 持卡人国家/地区
	 */
	private String ptyCountryCode;
	/**
	 * 身份证件号码
	 */
	private String idCode;
	/**
	 * 持卡人姓名
	 */
	private String personName;
	/**
	 * 卡号
	 */
	private String acctno;
	/**
	 * 交易货币币种
	 */
	private String jyCcyCode;
	/**
	 * 交易货币金额
	 */
	private String jyAmt;
	/**
	 * 交易货币折人民币金额
	 */
	private String qsAmtRmb;
	/**
	 * MCC 码
	 */
	private String mccCode;
	/**
	 * 银行卡类型
	 */
	private String cardTypeCode;
	/**
	 * 银行卡清算渠道
	 */
	private String cardChnlCode;
	/**
	 * 发卡行金融机构代码
	 */
	private String bankCode;
	/**
	 * 发卡网点所在地外汇局代码
	 */
	private String branchSafecode;
	/**
	 * 交易授权日期及时间
	 */
	private String bizDealTime;
	/**
	 * 交易国家或地区
	 */
	private String countryCode;
	/**
	 * 银行内部流水号
	 */
	private String bankSelfNum;
	/**
	 * 卡组织单号
	 */
	private String cardSelfNum;
	
	private String dataDate;
	
	
	/**
	 * @return the dataDate
	 */
	public String getDataDate() {
		return dataDate;
	}
	/**
	 * @param dataDate the dataDate to set
	 */
	public void setDataDate(String dataDate) {
		this.dataDate = dataDate;
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
	 * @return the refno
	 */
	public String getRefno() {
		return refno;
	}
	/**
	 * @param refno the refno to set
	 */
	public void setRefno(String refno) {
		this.refno = refno;
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
	 * @return the acctno
	 */
	public String getAcctno() {
		return acctno;
	}
	/**
	 * @param acctno the acctno to set
	 */
	public void setAcctno(String acctno) {
		this.acctno = acctno;
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
