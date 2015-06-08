package com.yellowcong.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author 狂飙のyellowcong
 *
 * 2015年6月1日
 */
public class DateUtil {
	public static String DEFAULT_DATE_FORMAT="";
	public static String DEFAULT_TIME_FORMAT="";
	static{
		DEFAULT_DATE_FORMAT=PropUtil.get("sys.commons.dateFormat");
		DEFAULT_TIME_FORMAT=PropUtil.get("sys.commons.timeFormat");
	}
	/**
	 * 格式化日期类型
	 * @param d
	 * @return
	 */
	public static String formatDate(Date d){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return simpleDateFormat.format(d);
	}
	/**
	 * 格式化日期时间
	 * @param d
	 * @return
	 */
	public static String formatDateTime(Date d){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		return simpleDateFormat.format(d);
	}
	/**
	 * 得到日期对象
	 * @param str
	 * @return
	 */
	public static Date getDate(String str){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	/**
	 * 得到日期时间对象
	 * @param str
	 * @return
	 */
	public static Date getTime(String str){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	/**
	 * 根据格式化信息得到日期对象
	 * @param str
	 * @param parse
	 * @return
	 */
	public static Date getDate(String str,String parse){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(parse);
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}
}
