package com.intkr.saas.util;

import java.text.DecimalFormat;

/**
 * 
 * @author Beiden
 * @date 2011-7-20 下午3:31:55
 * @version 1.0
 */
public class TableIndexUtil {

	private static DecimalFormat df = new DecimalFormat("0000");

	public static String getTableIndex(int offset, int tableCount) {
		int i = offset % tableCount;
		return df.format(i);
	}

	public static String getTableIndex(String offset, int tableCount) {
		try {
			if (offset.length() != 4) {
				throw new RuntimeException("table offset error!");
			}
			Number n = df.parse(offset);
			int i = n.intValue() % tableCount;
			return df.format(i);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
