package com.kalvin.kvf.modules.mts.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
/**
 * @date：2020-10-16 18:10:00
 * @purpose：
 * @address：
 * @auth：jingjin
 */
@Slf4j
public class CommonUtil {

//    public static String confPath;

    private static Properties properties;

	public static Properties loadConfig(){
  		InputStream in = null;
		if(null != properties){
			return properties;
		}
		try{
			properties = new Properties();
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream("mts_conf.properties");
			properties.load(in);
			return properties;
		}catch(Exception e){
			log.error("error",e);
			return null;
		}finally{
			try{
				if(in != null){
					in.close();
				}
			}catch(Exception e){}
		}
	}
	
	public static String getKeyValue(String key){
		Properties pro = loadConfig();
		if(null != pro){
			return pro.getProperty(key);
		}
		return null;
	}
}
