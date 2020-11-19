package com.kalvin.kvf.modules.mts.entity;

import com.kalvin.kvf.modules.mts.util.XmlBinder;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
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
public class SAFEAnsData {

	/**
	 * 应用类型
	 */
	@XmlElement(name = "APPTYPE")
	private String appType;//应用类型
	
	/**
	 * 当前文件类型
	 */
	@XmlElement(name = "CURRENTFILE")
	private String currentFile;//当前文件类型
	
	/**
	 * 输入/输出
	 */
	@XmlElement(name = "INOUT")
	private String inOut;//输入/输出
	
	/**
	 * 文件格式错误数
	 */
	@XmlElement(name = "FORMATERRS")
	private String formaterrs;
	

	@XmlElement(name = "FORMATS")
	private Formats formats;

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
	 * @return the formaterrs
	 */
	public String getFormaterrs() {
		return formaterrs;
	}

	/**
	 * @param formaterrs the formaterrs to set
	 */
	public void setFormaterrs(String formaterrs) {
		this.formaterrs = formaterrs;
	}

	/**
	 * @return the formats
	 */
	public Formats getFormats() {
		return formats;
	}

	/**
	 * @param formats the formats to set
	 */
	public void setFormats(Formats formats) {
		this.formats = formats;
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
	 * @return the sucRecords
	 */
	public String getSucRecords() {
		return sucRecords;
	}

	/**
	 * @param sucRecords the sucRecords to set
	 */
	public void setSucRecords(String sucRecords) {
		this.sucRecords = sucRecords;
	}

	/**
	 * @return the falRecords
	 */
	public String getFalRecords() {
		return falRecords;
	}

	/**
	 * @param falRecords the falRecords to set
	 */
	public void setFalRecords(String falRecords) {
		this.falRecords = falRecords;
	}

	/**
	 * @return the errRecords
	 */
	public ErrRecords getErrRecords() {
		return errRecords;
	}

	/**
	 * @param errRecords the errRecords to set
	 */
	public void setErrRecords(ErrRecords errRecords) {
		this.errRecords = errRecords;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Formats{
		
		/**
		 * 文件格式错误描述
		 */
		@XmlElement(name = "FORMAT")
		private List<String> formatList;

		/**
		 * @return the format
		 */
		public List<String> getFormatList() {
			return formatList;
		}

		/**
		 * @param format the format to set
		 */
		public void setFormatList(List<String> formatList) {
			this.formatList = formatList;
		}
		
	}
	
	/**
	 * 总记录数
	 */
	@XmlElement(name = "TOTALRECORDS")
	private String totalRecords;
	/**
	 * 成功的记录数
	 */
	@XmlElement(name = "SUCRECORDS")
	private String sucRecords;
	/**
	 * 失败的记录数
	 */
	@XmlElement(name = "FALRECORDS")
	private String falRecords;

	/**
	 * 失败的记录
	 */
	@XmlElement(name = "ERRRECORDS")
	private ErrRecords errRecords;	
	
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ErrRecords{
		
		/**
		 * 失败的记录明细
		 */
		@XmlElement(name = "REC")
		private List<ErrRecord> errRecordList;

		/**
		 * @return the errRecords
		 */
		public List<ErrRecord> getErrRecordList() {
			return errRecordList == null ? errRecordList = new ArrayList<ErrRecord>() : errRecordList ;
		}

		/**
		 * @param errRecords the errRecords to set
		 */
		public void setErrRecordList(List<ErrRecord> errRecordList) {
			this.errRecordList = errRecordList;
		}
		
		
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ErrRecord{
		/**
		 * 业务数据主键
		 */
		@XmlElement(name = "BUSSNO")
		private String bussNo;
		/**
		 * 
		 */
		@XmlElement(name = "ERRFIELDS")
		private ErrFields errFields ;
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
		 * @return the errFields
		 */
		public ErrFields getErrFields() {
			return errFields;
		}
		/**
		 * @param errFields the errFields to set
		 */
		public void setErrFields(ErrFields errFields) {
			this.errFields = errFields;
		}
		
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ErrFields{
		
		/**
		 * 
		 */
		@XmlElement(name = "ERR")
		private List<ErrField> errFieldList;

		/**
		 * @return the errFieldList
		 */
		public List<ErrField> getErrFieldList() {
			return errFieldList == null ? errFieldList = new ArrayList<ErrField>() : errFieldList;
		}

		/**
		 * @param errFieldList the errFieldList to set
		 */
		public void setErrFieldList(List<ErrField> errFieldList) {
			this.errFieldList = errFieldList;
		}
		
		
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ErrField{
		/**
		 * 出错字段英文标识
		 */
		@XmlElement(name = "ERRFIELD")
		private String errField;
		/**
		 * 出错字段中文标识
		 */
		@XmlElement(name = "ERRFIELDCN")
		private String errFieldCn;
		/**
		 * 出错原因
		 */
		@XmlElement(name = "ERRDESC")
		private String errDesc;
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
	
	public static void main(String[] args) {
		
		String xml = "<MSG>"
				+"<APPTYPE>应用类型</APPTYPE>"
				+"<CURRENTFILE>当前文件类型</CURRENTFILE>"
				+"<INOUT>输入/输出</INOUT>"
				+"<FORMATERRS>文件格式错误数</FORMATERRS>"
				+"<FORMATS>"
				+"<FORMAT>文件格式错误描述</FORMAT>"
				+"</FORMATS>"
				+"<TOTALRECORDS>总记录数</TOTALRECORDS>"
				+"<SUCRECORDS>成功的记录数</SUCRECORDS>"
				+"<FALRECORDS>失败的记录数</FALRECORDS>"
				+"<ERRRECORDS>"
				+"<REC>"
				+"<BUSSNO>业务数据主键</BUSSNO>"
				+"<ERRFIELDS>"
				+"<ERR>"
				+"<ERRFIELD>出错字段英文标识</ERRFIELD>"
				+"<ERRFIELDCN>出错字段中文标识</ERRFIELDCN>"
				+"<ERRDESC>出错原因</ERRDESC>"
				+"</ERR>"
				+"</ERRFIELDS>"
				+"</REC>"
				+"</ERRRECORDS>"
				+"</MSG>";
		
		XmlBinder<SAFEAnsData> binder = new XmlBinder<SAFEAnsData>(SAFEAnsData.class);
		
		try {
			SAFEAnsData data = binder.unmarshal(xml);
			
			System.out.println(data.getErrRecords().getErrRecordList().get(0).getErrFields().getErrFieldList().get(0).getErrDesc());
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
