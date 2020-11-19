package com.kalvin.kvf.modules.mts.entity;
/**
 * @date：2020年10月16日17:45:16
 * @purpose：
 * @address：
 * @auth：jingjin
 */

import com.baomidou.mybatisplus.annotation.TableName;
import com.kalvin.kvf.common.entity.BaseEntity;

@TableName("mts_card_exp_info")
public class MTSCardExpInfo extends BaseEntity {
	
	private String certTypeCode;   
	private String ptyCountryCode; 
	private String idCode;      
	private String inputDate;
	private String dataType;
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
	 * @return the inputDate
	 */
	public String getInputDate() {
		return inputDate;
	}
	/**
	 * @param inputDate the inputDate to set
	 */
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}
	/**
	 * @param dataType the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
}
