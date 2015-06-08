package com.yellowcong.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * 配置文件工具类  这个工具类 比我自己写的差了很多
 * @author 狂飙のyellowcong
 *
 * 2015年6月1日
 */
public class PropUtil {
	/**
	 * 根据key获取配置的值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		try {
			Properties prop=PropertiesLoaderUtils.loadAllProperties("sysconfig.properties");
			return prop.getProperty(key,"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
