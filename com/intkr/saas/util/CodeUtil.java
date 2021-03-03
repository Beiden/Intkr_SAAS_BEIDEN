package com.intkr.saas.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Beiden
 * @date 2016-6-26 下午12:35:33
 * @version 1.0
 */
public class CodeUtil {

	private static DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	public static String getCode() {
		return df.format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(getCode());
	}

}
