package com.kalvin.kvf.modules.mts.util;

import com.csii.bpf.common.Base64;
import com.csii.bpf.common.DateUtil;
import com.csii.bpf.exceptions.AppException;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @date：2016年4月9日
 * @time：上午11:16:56
 * @purpose：
 * @address：
 * @auth：Bob.jiang
 */
public class AdpUtil {

	/**
	 * 除去字符串的空格
	 * 如果字符串为'空'或者为"null","undefined"均转换为空字符串""
	 * @param target
	 * @return
	 */
	public static String trimSpace( String target ){		
		if(target != null && !"undefined".equals(target) && !"null".equals(target)){
			target = target.trim();
		}else {
			target = "";
		}
		return target;
	}
	/**
	 * 判断字符串是否不为空
	 * null,"null","undefined"均视为空
	 * @param str
	 * @return
	 */
	public static boolean notNull(String str){		
		if (str != null && str.trim().length() != 0
				&& !str.equals("undefined") && !str.equals("null") ) {
			return true;			
		}else{
			return false;
		}		
	}
	/**
	 * 判断字符串是否为空
	 * null,"null","undefined"均视为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(str != null)str = str.trim();
		
		if (str == null || str.length() == 0 ||str.equals("undefined") 
				|| str.equals("null")) {
			return true;			
		}else{
			return false;
		}		
	}
	/**
	 * 字符向字节转换方法（空格补位）
	 * @param obj 字符串
	 * @param length 需要格式化的长度
	 * @param format 格式
	 * @return
	 */
	public static byte[] formatStringToFixedLengthGBK(String obj , Integer length,String format) throws IOException{
		
		if( obj == null ){
			obj = "";
		}
		if(length == null ){
			throw new RuntimeException("字符串格式化长度未定义!");
		}
		byte[] buffer = new byte[length];
		byte[] bytes = obj.getBytes("GBK");
		
		int iLen = bytes.length;
		int j = 0 ;
		for (int i = 0; i < length; i++) {
			/** 左对齐 右补空格 **/
			if ("L".equals(format)) {
				if (i >= iLen && length > iLen)
					buffer[i] = 0x20;
				else
					buffer[i] = bytes[i];
			} else {
				if ( (length > iLen) && (i < (length - iLen)) )
					buffer[i] = 0x20;
				else
					buffer[i] = bytes[j++];
			}
		}
		return buffer;
		
	}	
	
	/**
	 * 字符根据传入编码，向字节转换方法（空格补位）
	 * @param obj 字符串
	 * @param length 需要格式化的长度
	 * @param format 格式
	 * @param charSet	编码
	 * @return
	 */
	public static byte[] formatStringToFixedLengthFileCode(String obj , Integer length, String format, String charSet) throws IOException{
		
		if( obj == null ){
			obj = "";
		}
		if(charSet == null){
			charSet = "GBK";
		}
		if(length == null ){
			throw new RuntimeException("字符串格式化长度未定义!");
		}
		byte[] buffer = new byte[length];
		byte[] bytes = obj.getBytes(charSet);
		
		int iLen = bytes.length;
		int j = 0 ;
		for (int i = 0; i < length; i++) {
			/** 左对齐 右补空格 **/
			if ("L".equals(format)) {
				if (i >= iLen && length > iLen)
					buffer[i] = 0x20;
				else
					buffer[i] = bytes[i];
			} else {
				if ( (length > iLen) && (i < (length - iLen)) )
					buffer[i] = 0x20;
				else
					buffer[i] = bytes[j++];
			}
		}
		return buffer;
		
	}

	
	/**
	 * 获取目标字符串起止字节数的内容（编码格式utf8）,end如果为-1表示截取到最后一位
	 * @param target 目标
	 * @param begin  开始点（包含）
	 * @param end    结束点（不包含）
	 * @param charSet
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getValByByteLength(String target,Integer begin,Integer end, String charSet) throws UnsupportedEncodingException{
		if(target == null )return "";
		if(charSet == null){
			charSet = "GBK";
		}
		if(begin == null || end == null)
			throw new RuntimeException("字符串格式化长度未定义!");
		byte[] bytes = target.getBytes(charSet);
		if(end == -1) end = bytes.length;
		int len = end - begin;		
		if(len < 0)
			throw new RuntimeException("起始位置大于了结束位置!");

		if(begin > bytes.length || end > bytes.length)
			throw new RuntimeException("解析数据长度不正确!");
	
		byte rebytes[] = new byte[len];
		
		System.arraycopy(bytes, begin, rebytes, 0, len);
		
		//return new String(bytes, begin, end, charSet);
		return new String( rebytes, charSet);
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String valueOfString(Object obj){

			if(obj == null )
				return "";
			else
				return String.valueOf(obj).trim();

	}
	
	/**
	 * type为‘0’表示该字符串需要转换为数字型，返回数字数字串,如果obj为空返回：‘0’
	 * @param obj
	 * @return
	 */
	public static String valueOfString(Object obj,String type){		
		if("0".equals(type)){
			if(isNull(valueOfString(obj)))
				return "0";
			else
				return String.valueOf(obj).trim();
		}else{
			return valueOfString(obj);
		}
		
	}
	
	/**
	 * 获得字符串的字节长度
	 * @param string
	 * @param encoding
	 * @return
	 */
	public static int getStrByteLen( String string, String encoding ) {
		if( string == null ) return 0;
		
		try {
			return string.getBytes( encoding ).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static byte[] getBytes( String separator , String charSet ) throws IOException{
		if(charSet == null) 
			charSet = "GBK"; 
		byte[] bytes = separator.getBytes(charSet);
		return bytes;
	}

	/**
	 * 格式化金额型字符串，保留两位有效数字
	 * @param strAmt
	 * @return
	 */
	public static String formatAmtField(String strAmt){
		if(isNull( strAmt)){
			 strAmt = "0";
		}
		return new BigDecimal(strAmt).setScale(2, BigDecimal.ROUND_DOWN).toString();
	}
	
	/****************************************以下为项目变更中歇业时间控制使用 start********************************************/
	/**
	 * 取值范围：年（0101-1231）、半年（0101-0630）、季（0101-0331）;
	 * @param ctrlDate
	 * @param ctrlCycle
	 * @return
	 */
	public static boolean validateYearSeasonCtrlDateField(String ctrlDate, String ctrlCycle){
		
		String[] arr = ctrlDate.split(",");
		if("5".equals(ctrlCycle)){

			return yearSeasonCtrlDateValidate(arr, 3);
		}else if("6".equals(ctrlCycle)){
		
			return yearSeasonCtrlDateValidate(arr, 6);
		}else if("7".equals(ctrlCycle)){
			return yearSeasonCtrlDateValidate(arr, 12);			
		}
		
		return true;
	}
	
	/**
	 * 旬、月，格式：(DD)，取值范围：旬（01-10）、月（01-31）
	 * @param ctrlDate
	 * @param ctrlCycle
	 * @return
	 */
	public static boolean validateMonthCtrlDateField(String ctrlDate, String ctrlCycle){
		String[] arr = ctrlDate.split(",");		
		return monthCtrlDateValidate(arr, ctrlCycle);
	}
	/**
	 * 周格式：（W），取值范围：（0-6）；
	 * @param ctrlDate
	 * @param ctrlCycle
	 * @return
	 */
	public static boolean validateWeekCtrlDateField(String ctrlDate, String ctrlCycle){
		String[] arr = ctrlDate.split(",");
		int max = 0;
		for(int i = 0 ; i < arr.length ; i ++){
			String str = arr[i];
			if(str.indexOf("-") > -1){
				String[] ctrls = str.split("-");
				if(ctrls.length == 2){
					String ctrl1 = ctrls[0];
					String ctrl2 = ctrls[1];
					if(!(Integer.parseInt(ctrl1) <= 6 && Integer.parseInt(ctrl2) <= 6)){
						return false;
					}					
					if(Integer.parseInt(ctrl1) > Integer.parseInt(ctrl2)){
						return false;
					}
					if(Integer.parseInt(ctrl1) < max){
						return false;
					}
					max = Integer.parseInt(ctrl2);
				}else{
					return false;
				}
			}else{
				if(!(Integer.parseInt(str) <= 6 )){
					return false;
				}	
			}
		}
		return true;
	
	}
	/**
	 * 控制周期为：0-日
	 * @param ctrlDate
	 * @param ctrlCycle
	 * @return
	 */
	public static boolean validateDayCtrlDateField(String ctrlDate, String ctrlCycle){
		String[] arr = ctrlDate.split(",");
		for(int i = 0 ; i < arr.length ; i ++){
			String str = arr[i];
			if(str.indexOf("-") > -1){
				String[] ctrls = str.split("-");
				if(ctrls.length == 2){
					if(!("0".equals(ctrls[0]) && "0".equals(ctrls[1]))){
						return false;
					}
				}else{
					return false;
				}				
			}else{
				if(!"0".equals(str)){
					return false;
				}
			}			
		}
		
		return true;
	}
	/**
	 * 无周期控制
	 * @param ctrlDate
	 * @return
	 */
	public static boolean validateNoCtrlCycleDateField(String ctrlDate){
		String[] arr = ctrlDate.split(",");
		int max = 0;
		for(int i = 0 ; i < arr.length ; i ++){
			String str = arr[i];
			if(str.indexOf("-") > -1){
				String[] ctrls = str.split("-");
				if(ctrls.length == 2){
					String ctrl1 = ctrls[0];
					String ctrl2 = ctrls[1];
					if(!(ctrl1.length() == 8 
							&& (Integer.parseInt(ctrl1.substring(4,6)) <= 12
									&& Integer.parseInt(ctrl1.substring(6)) <= 31))){
						return false;
					}
					if(!(ctrl2.length() == 8 
							&& (Integer.parseInt(ctrl2.substring(4,6)) <= 12 
									&& Integer.parseInt(ctrl2.substring(6)) <= 31))){
						return false;
					}
					if(Integer.parseInt(ctrl1) > Integer.parseInt(ctrl2)){
						return false;
					}
					if(Integer.parseInt(ctrl1) <= max){
						return false;
					}
					max = Integer.parseInt(ctrl2);
				}else{
					return false;
				}
			}else{
				if(!(str.length() == 8 
						&& (Integer.parseInt(str.substring(4,6)) <= 12 
								&& Integer.parseInt(str.substring(6)) <= 31))){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 项目歇业控制中按月控制时验证控制日期是否合理
	 * @param ctrlTime 验证的字符串
	 * @return
	 */
	public static boolean validateTimeFiled(String ctrlTime){
		String[] arr = ctrlTime.split(",");
		
		for(int i = 0 ; i < arr.length ; i ++){
			String str = arr[i];
			if(str.indexOf("-") > -1){
				String[] ctrls = str.split("-");
				if(ctrls.length == 2){
					String ctrl1 = ctrls[0];
					String ctrl2 = ctrls[1];
					if(!(ctrl1.length() == 4 
							&& (Integer.parseInt(ctrl1.substring(0,2)) <= 23
									&& Integer.parseInt(ctrl1.substring(2,4)) <= 59 ))){
						return false;
					}
					if(!(ctrl2.length() == 4 
							&& (Integer.parseInt(ctrl2.substring(0,2)) <= 23
									&& Integer.parseInt(ctrl2.substring(2,4)) <= 59  ))){
						return false;
					}
					if(Integer.parseInt(ctrl1) > Integer.parseInt(ctrl2)){
						return false;
					}
					
				}else{
					return false;
				}
			}else{
				if(!(str.length() == 4 
						&& (Integer.parseInt(str.substring(0,2)) <= 23
								&& Integer.parseInt(str.substring(2,4)) <= 59))){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 项目歇业控制中按月控制时验证控制日期是否合理
	 * @param arr 验证的字符数组
	 * @param cycle 控制周期
	 * @return
	 */
	public static boolean monthCtrlDateValidate(String[] arr, String cycle){
		int max = 0;
		int len = 0;
		if("3".equals(cycle)){
			len = 10;
		}else{
			len = 31;
		}
		for(int i = 0 ; i < arr.length ; i ++){
			String str = arr[i];
			if(str.indexOf("-") > -1){
				String[] ctrls = str.split("-");
				if(ctrls.length == 2){
					String ctrl1 = ctrls[0];
					String ctrl2 = ctrls[1];
					if(!(Integer.parseInt(ctrl1) <= len && Integer.parseInt(ctrl2) <= len)){
						return false;
					}					
					if(Integer.parseInt(ctrl1) > Integer.parseInt(ctrl2)){
						return false;
					}
					if(Integer.parseInt(ctrl1) <= max){
						return false;
					}
					max = Integer.parseInt(ctrl2);
				}else{
					return false;
				}
			}else{
				if(!(Integer.parseInt(str) <= len)){
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 项目歇业控制中年、季控制时验证控制日期是否合理
	 * @param arr 验证的字符数组
	 * @param len 最小值
	 * @return
	 */
	public static boolean yearSeasonCtrlDateValidate(String[] arr, int len){
		int max = 0;
		for(int i = 0 ; i < arr.length ; i ++){
			String str = arr[i];
			if(str.indexOf("-") > -1){
				String[] ctrls = str.split("-");
				if(ctrls.length == 2){
					String ctrl1 = ctrls[0];
					String ctrl2 = ctrls[1];
					if(!(ctrl1.length() == 4 
							&& (Integer.parseInt(ctrl1.substring(0,2)) <= len
									&& Integer.parseInt(ctrl1.substring(2)) <= 31))){
						return false;
					}
					if(!(ctrl2.length() == 4 
							&& (Integer.parseInt(ctrl2.substring(0,2)) <= len 
									&& Integer.parseInt(ctrl2.substring(2)) <= 31))){
						return false;
					}
					if(Integer.parseInt(ctrl1) > Integer.parseInt(ctrl2)){
						return false;
					}
					if(Integer.parseInt(ctrl1) <= max){
						return false;
					}
					max = Integer.parseInt(ctrl2);
				}else{
					return false;
				}
			}else{
				if(!(str.length() == 4 
						&& (Integer.parseInt(str.substring(0,2)) <= len 
								&& Integer.parseInt(str.substring(2)) <= 31))){
					return false;
				}
			}
		}
		return true;
	}
	/****************************************以下上为项目变更中歇业时间控制使用 end********************************************/
	
	/**
	 * 验证字符串是否为数字型字符串
	 * @param numberStr
	 * @return
	 */
	public static boolean numberValidate(String numberStr){
	
		if(isNull(numberStr)){
			return false;
		}
		numberStr = numberStr.trim();
		Pattern pat = Pattern.compile("^[0-9]+$");		
		Matcher ma = pat.matcher(numberStr);
		
		return ma.matches();
	}

	/**
	 * 验证字符串是否为金额型字符串(可以验证含小数点的金额字符串，小数点后必须包含有效数字，可验证格式为".d+"的金额)
	 * @param numberStr
	 * @return
	 */
	public static boolean amountValidate(String amountStr){
	
		if(isNull(amountStr)){
			return false;
		}
		amountStr = amountStr.trim();
		Pattern pat = Pattern.compile("^[-]{0,1}[0-9]+(\\.(\\d+))?|[-]{0,1}[\\.]{1}(\\d+)?$");		
		Matcher ma = pat.matcher(amountStr);
		
		return ma.matches();
	}
	/**
	 * 判断金额型字符串是否小于零,若金额字符串为空，返回false
	 * @param amt
	 * @return
	 */
	public static boolean amtIsBelowZero(String amt){
		
		if(isNull(amt)){
			return false;
		}
		if(new BigDecimal(amt).compareTo(new BigDecimal("0.0")) < 0)
			return true;
		
		return false;
		
	}
	
	/**
	 * 
	 * @param dateString  yyyyMMddHHmmss
	 * @return  yyyy/MM/dd HH:mm:ss
	 */
	public static String formatDate1(String dateString) {
		if(isNull(dateString)){
			return "";
		}
		SimpleDateFormat dateForamt = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			Date date = dateForamt.parse(dateString);
			
			SimpleDateFormat dateForamt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return dateForamt1.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return dateString;
		}
	}
	
	/**
	 * 
	 * @param dateString  yyyy/MM/dd HH:mm:ss
	 * @return  yyyyMMddHHmmss
	 */
	public static String formatDate(String dateString) {
		if(isNull(dateString)){
			return "";
		}
		SimpleDateFormat dateForamt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		try {
			Date date = dateForamt.parse(dateString);
			
			SimpleDateFormat dateForamt1 = new SimpleDateFormat("yyyyMMddHHmmss");
			return dateForamt1.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return dateString;
		}
	}
	
	/**
	 * 
	 * @param date
	 * @return  yyyyMMddHHmmss
	 */
	public static String formatDate(Date date) {
		if(date == null){
			date = new Date();
		}
		return DateUtil.formatDate("yyyyMMddHHmmss", date);
		
	}
	

	/**
	 * 
	 * @param date
	 * @return  yyyyMMddHHmmssSSS
	 */
	public static String formatDateMills(Date date) {
		if(date == null){
			date = new Date();
		}
		return DateUtil.formatDate("yyyyMMddHHmmssSSS", date);
		
	}
	/**
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String formatDate(String format, Date date) {
		if(date == null){
			date = new Date();
		}

		SimpleDateFormat sdformat = new SimpleDateFormat(format);
		String dateTime = sdformat.format(date);
		return dateTime;
		
	}
	
	public static String formatDate(String targetFormat, String sourceFormat, String dateStr) {
		if(dateStr == null){
			return "";
		}
		SimpleDateFormat dateForamt = new SimpleDateFormat(sourceFormat);
		try {
			Date date = dateForamt.parse(dateStr);
			
			SimpleDateFormat dateForamt1 = new SimpleDateFormat(targetFormat);
			return dateForamt1.format(date);
			
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	/**
	 * 判断字符串中是否包含中文字符
	 * 
	 * @param content
	 * @return
	 */
	public static boolean hasChineseCharacterValidate(String content){
		
		if(isNull(content)){
			return false;
		}
		content = content.trim();
		Pattern pat = Pattern.compile("[\\p{InCJK Unified Ideographs}&&\\P{Cn}]");
		Matcher ma = pat.matcher(content);
		while(ma.find()){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 为开始时间加小时数
	 * @param dateStart - yyyyMMddHHmmss
	 * @param hours
	 * @return
	 * @throws Exception
	 */
	public static String dateAddHours(String dateStart, long hours) throws Exception{	

		SimpleDateFormat dateForamt = new SimpleDateFormat("yyyyMMddHHmmss");		
		try {
			Date dateST = dateForamt.parse(dateStart);	
			long addHour = hours * 60*60*1000;
			long times = dateST.getTime() + addHour;
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(times);		
			
			return dateForamt.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 获取间隔小时
	 * @param dateStart  开始时间  -yyyyMMddHHmmss
	 * @param dateEnd	结束时间-yyyyMMddHHmmss
	 * @return
	 * @throws Exception 
	 */
	public static long dateBetweenHours(String dateStart, String dateEnd) throws Exception{	

		SimpleDateFormat dateForamt = new SimpleDateFormat("yyyyMMddHHmmss");
		
		try {
			Date dateST = dateForamt.parse(dateStart);			
			Date dateEN = dateForamt.parse(dateEnd);			
			long diff = dateEN.getTime() - dateST.getTime();
			if(diff < 0 ){
				throw new Exception("开始日期大于结束日期");
			}
			long diffHour = diff/(60*60*1000);
			
			return diffHour;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	/**
	 * 获取间隔天数
	 * @param dateStart  开始时间  -yyyyMMddHHmmss
	 * @param dateEnd	结束时间-yyyyMMddHHmmss
	 * @return
	 * @throws Exception
	 */
	public static long dateBetweenDay(String dateStart, String dateEnd) throws Exception{	

		SimpleDateFormat dateForamt = new SimpleDateFormat("yyyyMMddHHmmss");		
		try {
			Date dateST = dateForamt.parse(dateStart);			
			Date dateEN = dateForamt.parse(dateEnd);			
			long diff = dateEN.getTime() - dateST.getTime();
			if(diff < 0 ){
				throw new Exception("开始日期大于结束日期");
			}
			long diffDays = diff/(60*60*1000*24);
			
			return diffDays;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public static String dateBeforeDays(String dateStr, int days){
		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date today = format.parse(dateStr);	
	        Calendar c = Calendar.getInstance();
	        c.setTime(today);
	        c.add(Calendar.DATE, -days);
	        Date d = c.getTime();
	        return format.format(d);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String dateBeforeMonths(String dateStr, int months){
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date today = format.parse(dateStr);	
	        Calendar c = Calendar.getInstance();
			c.setTime(today);
	        c.add(Calendar.MONTH, -months);
	        Date m = c.getTime();
	        return format.format(m);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	//判断是否是需要下载的文件
	//CXQQ-查询请求，KZQQ-控制请求，CXHZ-查询反馈失败回执，KZHZ-控制反馈失败回执
	public static boolean hasGyFile(String fileName) {
		
		if(fileName.startsWith("CXQQ") || fileName.startsWith("KZQQ") || fileName.startsWith("CXHZ") || fileName.startsWith("KZHZ")) {
			return true;
		}
		return false;
	}
	
	public static String base64Encode(String str){
		try {
			if(AdpUtil.isNull(str)){
				return "";
			}
			return base64Encode(str, null);
//			return str;////测试用，上线需还原
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return str;
		}
	}
	/**
	 * base64加密
	 * @param str
	 * @param charSet
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Encode(String str, String charSet) throws UnsupportedEncodingException{
		try {
			if(isNull(charSet)){
				charSet = "UTF-8";
			}
			return Base64.encode(str.getBytes(charSet));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public static String base64Decode(String base64) throws UnsupportedEncodingException{
		try {
			if(isNull(base64)){
				return "";
			}
			return base64Decode(base64,null);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catchk
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * base64解密
	 * @param base64
	 * @param charSet
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String base64Decode(String base64,String charSet) throws UnsupportedEncodingException{
		try {
			if(isNull(charSet)){
				charSet = "UTF-8";
			}
			return new String (Base64.decode(base64), charSet);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catchk
			e.printStackTrace();
			throw e;
		}
	}
//
//	/**
//	 * des解密
//	 * @param str
//	 * @return
//	 * @throws Exception
//	 */
//	public static String DesDecode(String str) throws Exception{
//
//		com.core.des.DESEncryptDcrypt des = new com.core.des.DESEncryptDcrypt(CommonConstant.DES_KEY);
//		String pwd = des.decrypt(str);
//		if(notNull(pwd)){
//			pwd = pwd.substring(0, pwd.indexOf("|"));
//		}else{
//			throw new Exception("解密失败!");
//		}
//		return pwd;
//	}
//	/**
//	 * des加密
//	 * @param str
//	 * @param id
//	 * @param randOffset
//	 * @return
//	 * @throws Exception
//	 */
//	public static String DesEncode(String str, String id, int randOffset) throws Exception{
//
//		com.core.des.DESEncryptDcrypt des = new com.core.des.DESEncryptDcrypt(CommonConstant.DES_KEY);
//		return des.encrypt( String.format("%s|%s%d", str, id, randOffset ) );
//	}

	public static String base64Sunencode(String str) {
	    if (str != null) {
	    	byte[] bt = str.getBytes();
	    	return new sun.misc.BASE64Encoder().encode(bt);
	    }
	    return "";
	}
	
	
	/**
	 * 复制单个文件

	 * @return
	 * @throws AppException
	 */
    public static boolean copyFile( String srcFile, String destFile) throws AppException {  
    	
				 
  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
        	File sFile = new File(srcFile);  
        	File dFile = new File(destFile);
    		if(!dFile.getParentFile().exists()){
    			dFile.getParentFile().mkdirs();
    		}
    		
            in = new FileInputStream(sFile);  
            out = new FileOutputStream(dFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    /**
     * 将换行符替换为空字符串
     * @param source
     * @return
     */
    public static String replaceLine(String source){
    	return source.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
    }
  

	public static void main(String[] args) {
		
		try {
			
			System.out.println(dateBeforeMonths("20170616", 4));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *
	 * String转map
	 * @param str
	 * @return
	 */
	public static Map<String,String> getStringToMap(String str){
		//根据逗号截取字符串数组
		String[] str1 = str.split(",");
		//创建Map对象
		Map<String,String> map = new HashMap<>();
		//循环加入map集合
		for (int i = 0; i < str1.length; i++) {
			//根据":"截取字符串数组
			String[] str2 = str1[i].split(":");
			//str2[0]为KEY,str2[1]为值
			map.put(str2[0],str2[1]);
		}
		return map;
	}
	//删除特定字符
	public static String deleteCharString(String sourceString, char chElemData) {
		String deleteString = "";
		char[] Bytes = sourceString.toCharArray();
		int iSize = Bytes.length;
		for (int i = Bytes.length - 1; i >= 0; i--) {
			if (Bytes[i] == chElemData) {
				for (int j = i; j < iSize - 1; j++) {
					Bytes[j] = Bytes[j + 1];
				}
				iSize--;
			}
		}
		for (int i = 0; i < iSize; i++) {
			deleteString += Bytes[i];
		}
		return deleteString;
	}
	public static List<String> getLocalFileLists(String localfile) throws IOException{
		List<String> fileLists=new ArrayList<>();
		File localFile=new File(localfile);
		File[] files = localFile.listFiles();
		if(null == files){
			return fileLists;
		}
		for (File file:files) {
			if (file.isDirectory()){
				continue;
			}
			if(".".equals(file.getName()) || "..".equals(file.getName())){
				continue;
			}
			fileLists.add(file.getName());
		}
		return fileLists;
	}
}