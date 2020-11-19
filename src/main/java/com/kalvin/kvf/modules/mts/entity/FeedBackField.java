package com.kalvin.kvf.modules.mts.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.kalvin.kvf.common.entity.BaseEntity;

/**
 * @date：2020年10月16日17:45:16
 * @purpose：
 * @address：
 * @auth：jingjin
 */
@TableName("mts_feed_back_field")
public class FeedBackField extends BaseEntity {

	private String bussNo;
	private String errField;
	private String errFieldCn;
	private String errDesc;
	private String inputDate;
	private String dataType;
	
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
	/**
	 * @return the bussNo
	 */
	public String getBussNo() {
		return bussNo;
	}
	/**
	 * @param bussNo the bussNo to set
	 */
	public void setBussNo(String bussNo) {
		this.bussNo = bussNo;
	}
	/**
	 * @return the errField
	 */
	public String getErrField() {
		return errField;
	}
	/**
	 * @param errField the errField to set
	 */
	public void setErrField(String errField) {
		this.errField = errField;
	}
	/**
	 * @return the errFieldCn
	 */
	public String getErrFieldCn() {
		return errFieldCn;
	}
	/**
	 * @param errFieldCn the errFieldCn to set
	 */
	public void setErrFieldCn(String errFieldCn) {
		this.errFieldCn = errFieldCn;
	}
	/**
	 * @return the errDesc
	 */
	public String getErrDesc() {
		return errDesc;
	}
	/**
	 * @param errDesc the errDesc to set
	 */
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	
}
