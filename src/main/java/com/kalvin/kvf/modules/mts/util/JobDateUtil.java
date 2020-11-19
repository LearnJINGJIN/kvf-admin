package com.kalvin.kvf.modules.mts.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 import java.text.ParseException;

/**
 * Description:交易日期处理
 *
 * @Auther: jingjin
 * @Date: 2020-10-19 9:28
 * @Modified by:
 */
public class JobDateUtil {
    /**
     * 根据传入的日期串进行转换，如果是"T"就取当天的日期，如果有表达式（T+1、T-1）则“+”或“-”相应的天数
     * @param job_date
     * @return
     * @throws Exception
     */
    public static String getTaskJobDate( String job_date ) throws Exception {
        String jobDate = null;
        Date today = new Date();
        DateFormat f = new SimpleDateFormat("yyyyMMdd");
        if( job_date == null || job_date.length() < 1 ){

            jobDate = f.format(today);

        } else {
            job_date = job_date.trim().toUpperCase();
            if(job_date.equals("T")){
                jobDate = f.format(today);
            }
            else if(job_date.startsWith("T")){
                String tranDate = jobDate = f.format(today);
                try {
                    jobDate = getAfterDay(tranDate, "yyyyMMdd", Integer.valueOf(job_date.substring(1)));
                }
                catch( NumberFormatException e ) {
                    jobDate = job_date;
                }

            }
            else jobDate = job_date;
        }

        return jobDate;
    }
    /**
     * 得到T日 后几天或前几天，
     * @param dateStr	日期
     * @param format	日期格式化：yyyyMMdd，或yyyy-MM-dd
     * @param day		天数（正数-后几天，负数-前几天）
     * @return
     */
    public static String getAfterDay( String dateStr, String format, int day ) {

        DateFormat f = new SimpleDateFormat(format);
        Date date=null;
        try {
            if(null != dateStr && !"".equals(dateStr)){
                date = f.parse(dateStr);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                c.add(Calendar.DATE, day);
                return ( f.format( c.getTime() ));
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
