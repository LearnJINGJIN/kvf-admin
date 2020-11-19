package com.kalvin.kvf.modules.mts.entity;

import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.util.XmlBinder;

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
public class ReqControlType {


	/**
	 * 应用类型
	 */
	@XmlElement(name = "APPTYPE")
	private String appType;//应用类型
	
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
	 * 总文件数
	 */
	@XmlElement(name = "TOTALFILES")
	private String totalFiles;
	/**
	 * 
	 */
	@XmlElement(name = "FILES")
	private Files files;

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Files{
		/**
		 * 文件名
		 */
		@XmlElement(name = "FILENAME")
		private List<String> fileNameList;

		/**
		 * @return the fileFameList
		 */
		public List<String> getFileNameList() {
			return this.fileNameList == null ? this.fileNameList = new ArrayList<String>() : this.fileNameList;
		}

		/**
		 * @param   fileFameList to set
		 */
		public void setFileNameList(List<String> fileNameList) {
			this.fileNameList = fileNameList;
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
	 * @return the totalFiles
	 */
	public String getTotalFiles() {
		return totalFiles;
	}

	/**
	 * @param totalFiles the totalFiles to set
	 */
	public void setTotalFiles(String totalFiles) {
		this.totalFiles = totalFiles;
	}

	/**
	 * @return the files
	 */
	public Files getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(Files files) {
		this.files = files;
	}
	
	public static void main(String[] args) {
		
		
		XmlBinder<ReqControlType> binder = new XmlBinder<ReqControlType>(ReqControlType.class);
		ReqControlType controlType = new ReqControlType();
		Files typeFiles = new Files();
		controlType.setFiles(typeFiles);
		List<String> list1 = new ArrayList<String>();
		list1.add("12");
		list1.add("123");
		
		List<String> list2 = new ArrayList<String>();
		list2.add("22");
		list2.add("223");
		System.out.println(typeFiles.getFileNameList().size());
		List<String> list3 = typeFiles.getFileNameList();
		boolean flag = typeFiles.getFileNameList().addAll(list1);
		System.out.println(typeFiles.getFileNameList().size()+"|"+flag);
		typeFiles.getFileNameList().addAll(list2);
		System.out.println(list3 == typeFiles.getFileNameList());
		
		controlType.setTotalFiles(typeFiles.getFileNameList().size() + ">\"");
		controlType.setCurrentFile(CommonConstant.CRD_CONTROL_FILE_TYPE);
		controlType.setAppType(CommonConstant.CRD_APP_TYPE);
		controlType.setInOut(CommonConstant.BANK_INPUT_TYPE);
		
		
		try {
			String data = binder.marshalString(controlType, CommonConstant.AQC_ENCODING);
			
			System.out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
