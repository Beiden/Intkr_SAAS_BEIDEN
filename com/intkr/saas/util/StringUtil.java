package com.intkr.saas.util;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @author Beiden
 * @date 2011-11-4 上午10:48:26
 * @version 1.0
 */
public class StringUtil {

	/**
	 * 大写第一个字母
	 * 
	 * @param string
	 * @return
	 */
	public static String upperCaseFirstCharater(String string) {
		if (string == null || "".equals(string)) {
			return string;
		}
		String classNameFirst = (string.charAt(0) + "").toUpperCase();
		string = classNameFirst + string.substring(1);
		return string;
	}

	/**
	 * 小写第一个字母
	 * 
	 * @param string
	 * @return
	 */
	public static String lowerCaseFirstCharater(String string) {
		if (string == null || "".equals(string)) {
			return string;
		}
		String classNameFirst = (string.charAt(0) + "").toLowerCase();
		string = classNameFirst + string.substring(1);
		return string;
	}

	public static String substring(String string, Integer length) {
		if (string.length() <= length) {
			return string;
		}
		return string.substring(0, length);
	}

	public static String conver(String string, String oldCharSet, String newCharSet) {
		try {
			return new String(string.getBytes(oldCharSet), newCharSet);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean isNotEmpty(String value) {
		return value != null && !"".equals(value);
	}

	public static boolean isEmpty(String value) {
		return value == null || "".equals(value);
	}

	/**
	 * 转驼峰式命名（把_换成大写字母）
	 * 
	 * @param columnName
	 * @return
	 */
	public static String hump(String columnName) {
		if (columnName == null || "".equals(columnName)) {
			return columnName;
		}
		if (!columnName.contains("_")) {
			return columnName;
		}
		String pre = columnName.substring(0, columnName.indexOf("_"));
		String sub = columnName.substring(columnName.indexOf("_") + 2);
		String word = columnName.substring(columnName.indexOf("_") + 1, columnName.indexOf("_") + 2);
		return hump(pre + word.toUpperCase() + sub);
	}

}
