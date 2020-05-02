package com.yc.lib.util;

import java.sql.Date;

/**
 * 	数据转换工具类
 * @author 廖彦
 *
 */
public class DataHelper {

	/**
	 *	 字符串转 integer
	 * @param string
	 * @param defaultValue 默认值
	 * @return
	 */
	public static Integer asInteger(String string, Integer defaultValue) {
		try {
			return Integer.valueOf(string);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Long asLong(String string, Long defaultValue) {
		try {
			return Long.valueOf(string);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Date asDate(String string, Date defaultValue) {
		try {
			return Date.valueOf(string);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 	判断 对象是否为空值, 如果是字符串,则再判断是否为空字符串 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object value) {
		if(value == null) {
			return true;
		} else if (value instanceof String) {
			// 判断是否为空字符串
			String string = (String) value;
			string = string.trim();
			if(string.isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	public static String asString(Object value) {
		return value != null ? value.toString() : "";
	}

}
