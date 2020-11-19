package com.kalvin.kvf.modules.mts.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import com.kalvin.kvf.modules.mts.constant.SFTPConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * @date：2017年7月15日
 * @time：下午3:53:27
 * @purpose：
 * @address：
 * @auth：Bob.jiang
 */
@Slf4j
public class SFTPChannel {
	
	Session session = null;
    Channel channel = null;


    
    private ChannelSftp connect() throws Exception {
    	
    	if(channel != null){
    		return (ChannelSftp) channel;
    	}    	
    	Properties pro = CommonUtil.loadConfig();
    	
    	Map<String, String> sftpDetails = new HashMap<String, String>();
    	
    	if(pro != null){
    		sftpDetails.put(SFTPConstants.SFTP_REQ_HOST, pro.getProperty(SFTPConstants.SFTP_REQ_HOST));
        	sftpDetails.put(SFTPConstants.SFTP_REQ_PORT, pro.getProperty(SFTPConstants.SFTP_REQ_PORT));
        	sftpDetails.put(SFTPConstants.SFTP_REQ_USERNAME, pro.getProperty(SFTPConstants.SFTP_REQ_USERNAME));
        	sftpDetails.put(SFTPConstants.SFTP_REQ_PASSWORD, pro.getProperty(SFTPConstants.SFTP_REQ_PASSWORD));
        	sftpDetails.put(SFTPConstants.TIMEOUT, pro.getProperty(SFTPConstants.TIMEOUT));
    	}    	   
    	return getChannel(sftpDetails);
    }
    
    private ChannelSftp getChannel(Map<String, String> sftpDetails) throws Exception {

        String ftpHost = sftpDetails.get(SFTPConstants.SFTP_REQ_HOST);
        String port = sftpDetails.get(SFTPConstants.SFTP_REQ_PORT);
        String ftpUserName = sftpDetails.get(SFTPConstants.SFTP_REQ_USERNAME);
        String ftpPassword = sftpDetails.get(SFTPConstants.SFTP_REQ_PASSWORD);
        String timeoutStr = sftpDetails.get(SFTPConstants.TIMEOUT);
        int timeout = 60000;
        if(AdpUtil.notNull(timeoutStr)){
        	timeout = Integer.valueOf(timeout);
        }
        
        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }

        JSch jsch = new JSch(); // 创建JSch对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        log.debug("Session created.");
        if (ftpPassword != null) {
//            ftpPassword = AdpUtil.DesDecode(ftpPassword);
            ftpPassword=DESUtil.decrypt(CommonConstant.DES_KEY,ftpPassword);
            session.setPassword(ftpPassword); // 设置密码       
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
//        config.put("kex", "diffie-hellman-group1-sha1");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        session.connect(); // 通过Session建立链接
        log.debug("Session connected.");

        log.debug("Opening Channel.");
        channel = session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        log.debug("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
                + ", returning: " + channel);
        System.out.println("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName
                + ", returning: " + channel);
        return (ChannelSftp) channel;
        
    }

    public void closeChannel() {
       try{
    	   if (channel != null) {
               channel.disconnect();
               channel = null;
           }
           if (session != null) {
               session.disconnect();
               session = null;
           }
       }catch(Exception e){
    	   e.printStackTrace();
       }
    	
    }
    
    public List<String> getRemoteFiles(String directory) throws Exception {
    	
		List<String> ftpFileNameList = new ArrayList<String>(100); 
    	try {
    		
    		ChannelSftp sftp = connect();

            @SuppressWarnings("unchecked")
            Vector<LsEntry> sftpFile = sftp.ls(directory);
            if(null == sftpFile){
            	return ftpFileNameList;
            }
            ChannelSftp.LsEntry isEntity = null;
            String fileName = null; 
            Iterator<LsEntry> sftpFileNames = sftpFile.iterator();
            while (sftpFileNames.hasNext()) 
            { 
                isEntity = (LsEntry) sftpFileNames.next(); 
                fileName = isEntity.getFilename();
                if(".".equals(fileName) || "..".equals(fileName)){
                	continue;
                }

                ftpFileNameList.add(fileName); 
               
            } 
		} catch (Exception e) {
			throw e;
		} finally{
//			closeChannel();
		}
    	
		return ftpFileNameList;
	}
    
    public boolean putFile(String directory, File file) throws Exception{

    	boolean flag = false; 
    	FileInputStream in = null; 
    	try{ 
    		ChannelSftp sftp = connect();
			String now = sftp.pwd(); 
			sftp.cd(directory); 
			in = new FileInputStream(file); 
			sftp.put(in, file.getName()); 
			sftp.cd(now); 
			if (file.exists()){ 
				flag = true; 
			}else { 
	          flag = false; 
			} 
    	}catch (Exception e){ 
    		log.error("uploadFile Exception : " + e);
    		throw e;
    	}finally { 

//			closeChannel();
	      try { 
	          if (null != in) { 
	              try { 
	                  in.close(); 
	              }catch (IOException e) { 
	            	  log.error("IOException : " + e);
	              } 
	          }	 
	      } catch (Exception e){ 
	    	  log.error("Exception : " + e);
	      } 
	      
    	}	      
    	return flag; 
          
    }
    
    /**
     * 可删除
     * @param ftpDir
     * @param locDir
     * @param ftpFileName
     * @param deleteFtpFile
     * @return
     * @throws Exception 
     * @throws FileNotFoundException
     */
    public File getFile(String ftpDir, String locDir, String ftpFileName, String localName, boolean deleteFtpFile) throws Exception { 
        File file = null; 
        FileOutputStream output = null; 
        String localDir = String.format("%s%s%s", locDir, File.separator, localName == null ? ftpFileName : localName);
        try{ 
    		ChannelSftp sftp = connect();
            String now = sftp.pwd(); 
            sftp.cd(ftpDir); 
            file = new File(localDir); 
            output = new FileOutputStream(file); 
            sftp.get(ftpFileName, output); 
            if (deleteFtpFile){ 
                sftp.rm(ftpFileName); 
            } 
            sftp.cd(now); 
        } catch (Exception e) {
        	log.error("Failed to download", e);
        	throw e;
		} finally{

//			closeChannel();
            if (null != output)  { 
                try  {
                    output.close(); 
                } catch (IOException e)  { 
                	log.error("create localFile failed:" + e);
                } 
            } 
        } 
        return file; 
    } 
    
    public void delete(String directory, String deleteFile) throws Exception { 
        try  { 
    		ChannelSftp sftp = connect();
            String now = sftp.pwd(); 
            sftp.cd(directory); 
            sftp.rm(deleteFile); 
            sftp.cd(now); 
        } catch (Exception e) { 
        	log.error("Failed to delete", e);
        	throw e;
        }  finally{
//			closeChannel();
		}
    } 
    
    public void mkdir(String parentDir, String dirName) throws Exception { 
        try  { 
    		ChannelSftp sftp = connect();
            String now = sftp.pwd(); 
            sftp.cd(parentDir); 
            sftp.mkdir(dirName); 
            sftp.cd(now); 
        } catch (Exception e) { 
        	log.error("Failed to mkdir", e);
        	throw e;
        }  finally{
//			closeChannel();
		}
    }
    /**
     * 只能删除空目录
     * @param parentDir
     * @param dirName
     * @throws Exception
     */
    public void rmdir(String parentDir, String dirName) throws Exception { 
        try  { 
    		ChannelSftp sftp = connect();
            String now = sftp.pwd(); 
            sftp.cd(parentDir); 
            sftp.rmdir(dirName); 
            sftp.cd(now); 
        } catch (Exception e) { 
        	log.error("Failed to rmdir", e);
         }  finally{
//			closeChannel();
		}
    }
    
     
    
    

}
