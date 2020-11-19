package com.kalvin.kvf.modules.mts.util;

import java.io.File;
import java.util.List;

/**
 * @date：2017年7月15日
 * @time：下午3:32:22
 * @purpose：
 * @address：
 * @auth：Bob.jiang
 */
public class SFTPUtil {
	
	private SFTPChannel sftp = new SFTPChannel();
		
	public void close(){
		sftp.closeChannel();
	}
	
	public List<String> getRemoteFiles(String remoteDir) throws Exception {
				
		return sftp.getRemoteFiles(remoteDir);
		
	}
	public boolean putFile(String remoteDir, File file)throws Exception{
		return sftp.putFile(remoteDir, file);
	}
	
	 public File getFile(String remoteDir, String locDir, String fileftpName, String localName, boolean deleteFtpFile)throws Exception {
		 return sftp.getFile(remoteDir, locDir, fileftpName, localName, deleteFtpFile);
	 }
	 
	 public void delete(String remoteDir, String deleteFile)throws Exception { 
		 sftp.delete(remoteDir, deleteFile);		 
	 }
	 public void mkdir(String parentDir, String dirName) throws Exception { 
		 sftp.mkdir(parentDir, dirName);
	 }
	 public void rmdir(String parentDir, String dirName) throws Exception { 
		 sftp.rmdir(parentDir, dirName);
	 }
}
