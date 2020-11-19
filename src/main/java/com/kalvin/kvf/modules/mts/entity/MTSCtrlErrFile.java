package com.kalvin.kvf.modules.mts.entity;

/**
 * @date：2020年10月16日17:45:16
 * @purpose：
 * @address：
 * @auth：jingjin
 */

import com.baomidou.mybatisplus.annotation.TableName;
import com.kalvin.kvf.common.entity.BaseEntity;

@TableName("mts_ctrl_err_file")
public class MTSCtrlErrFile extends BaseEntity {

	private String batchNo;//	n	varchar2(80)	n			批次号
	private String inputDate;//	n	varchar2(8)	n			导入日期
	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
	
}
