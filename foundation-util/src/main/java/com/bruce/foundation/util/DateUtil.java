package com.bruce.foundation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static long TIME_UNIT_SECOND = 1000*1;
	public static long TIME_UNIT_MINUTE = TIME_UNIT_SECOND*60;
	public static long TIME_UNIT_HOUR = TIME_UNIT_MINUTE*60;
	public static long TIME_UNIT_DAY = TIME_UNIT_HOUR*24;
	
	public static final SimpleDateFormat DATE_FORMAT_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_FORMAT_YMDHMS_COMPACT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static final SimpleDateFormat DATE_FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat DATE_FORMAT_YMD_COMPACT = new SimpleDateFormat("yyyyMMdd");
	
	
	/**
	 * 返回格式 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String date2YMD(Date date){
		if(date!=null){
			return DATE_FORMAT_YMD.format(date);
		}
		return null;
	}
	
	/**
	 * 返回格式 yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String date2CompactYMD(Date date){
		if(date!=null){
			return DATE_FORMAT_YMD_COMPACT.format(date);
		}
		return null;
	}
	
	/**
	 * 返回格式为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String date2YMDHMS(Date date){
		if(date!=null){
			return DATE_FORMAT_YMDHMS.format(date);
		}
		return null;
	}
	
	/**
	 * 返回格式为yyyyMMddHHmmss
	 * @param date
	 * @return
	 */
	public static String date2CompactYMDHMS(Date date){
		if(date!=null){
			return DATE_FORMAT_YMDHMS_COMPACT.format(date);
		}
		return null;
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try{
			return sdf.format(date);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 格式化现在时间
	 * @param formatter
	 * @return
	 */
	public static String formatNow(String formatter){
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		try{
			return sdf.format(new Date());
		}catch(Exception e){
			return null;
		}
	}
	
	
	public static Date calcDatetime(Date date, int days){
		if(date!=null){
			long originTime = date.getTime();
			long period  =(days*TIME_UNIT_DAY);
			long result = originTime+period;
			
			System.out.println(originTime);
			System.out.println(period);
			System.out.println(result);
			
			return new Date(result);
		}
		return null;
	}
	
	/**
	 * 获取linux时间
	 * @param date
	 * @return
	 */
	public static long getUnixTime(Date date) {
		if( null == date ) {
			return 0;
		}
		return date.getTime()/1000;
	}
	
	
	
	public static Date addDay(Date date, int days) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static Date addMinute(Date date, int minute) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}

	public static Date addSecond(Date date, int second) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}

	public static Date addSecOnNowTime(Long second) {
		return addSecond(new Date(), second.intValue());
	}
	
	
	public static int calcUnsignalDayInterval(String first, String second,
			String format) {
		Date f = convertTimeByFormat2(first, format);
		Date s = convertTimeByFormat2(second, format);
		long inter = Math.abs(f.getTime() - s.getTime());
		return new Long(inter / (1000 * 60 * 60 * 24)).intValue();
	}

	public static int calcUnsignalDayInterval(Date first, Date second) {
		long inter = Math.abs(first.getTime() - second.getTime());
		return new Long(inter / (1000 * 60 * 60 * 24)).intValue();
	}

	public static int calcDayInterval(Date first, Date second) {
		long inter = first.getTime() - second.getTime();
		return new Long(inter / (1000 * 60 * 60 * 24)).intValue();
	}
	
	
	public static Date convertTimeByFormat2(String timeString, String timeFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
		Date resultDate = null;
		try {
			resultDate = formatter.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultDate;
	}

	public static Date parseTimeByFormat(String timeString, String timeFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
		Date resultDate = formatter.parse(timeString);
		return resultDate;
	}

	
//	public static void main(String[] args) {
//		System.out.println(calcDate(new Date(), 7));
//	}
}
