package com.hontek.comm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description 当前时间格式化处理类
 * @author zh.l.j
 * @version 1.0.0
 * @since 2013-9-9
 */
public class DateUtil {

	static final String FORMAT = "yyyy-MM-dd";
	
	static final String FORMAT2 = "yyyy-MM-dd HH:mm:ss";
	
	static final String FORMAT3 = "yyyyMMddHHmmss";
	
	static final String FORMAT4 = "HHmmss";
	
	/**
	 * 返回格式"yyyy-MM-dd"的当前时间字符串
	 * @return
	 */
	public static String formatDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 返回date对象
	 * @param dateString yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String dateString) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		return dateFormat.parse(dateString);
	}
	
	/**
	 * 返回格式"yyyy-MM-dd"的时间字符串
	 * @return
	 */
	public static String formatDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		return dateFormat.format(date);
	}
	
	/**
	 * 返回格式"yyyy-MM-dd HH:mm:ss"的当前时间字符串
	 * @return
	 */
	public static String formatDateTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT2);
		return dateFormat.format(new Date());
	}
	
	
	/**
	 * 返回格式"yyyy-MM-dd HH:mm:ss"的时间字符串
	 * @return
	 */
	public static String formatDateTime(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT2);
		return dateFormat.format(date);
	}
	
	
	public static String formatYYYMMDDHHMMSS(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT3);
		return dateFormat.format(new Date());
	}
	
	
	public static String formatHHMMSS(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT4);
		return dateFormat.format(new Date());
	}
	
	/**
	 * 返回当前时间+5位随机数
	 * @return
	 */
	public static String formatYYYMMDDHHMMSSAnd5Random(){
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT3);
		return dateFormat.format(new Date())+new Random().nextInt(99999);
	}
}
