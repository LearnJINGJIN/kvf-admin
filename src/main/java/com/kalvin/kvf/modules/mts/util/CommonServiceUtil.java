package com.kalvin.kvf.modules.mts.util;

import com.kalvin.kvf.modules.mts.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * @date：2020年10月23日11:34:20
 * @purpose：
 * @address：
 * @auth：jingjin
 */
@Slf4j
public class CommonServiceUtil {
	public static boolean checkHasToken(String remotePath) {
		SFTPUtil sftp = null;
		try{
			sftp = new SFTPUtil();
			List<String> files = sftp.getRemoteFiles(CommonUtil.getKeyValue( remotePath ));
			if(null == files || files.size() == 0){
				return false;
			}
			if(files.contains(CommonConstant.TOKEN_FILE_NAME)){
				return true;
			}
			return false;
		}catch(Exception e){
			log.error("check remote file 'Token.lock' error ",e);
			return true;
		}finally{
			try{
				if(null != null){
					sftp.close();
					sftp = null;
				}
			}catch(Exception e){}
		}

	}
		
	
}
