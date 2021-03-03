package com.intkr.saas.util;

import java.text.DecimalFormat;

/**
 * 
 * @author Beiden
 * @date 2011-7-15 上午9:33:52
 * @version 1.0
 */
public class DoubleUtil {

	private static DecimalFormat df1 = new DecimalFormat("##.0");

	private static DecimalFormat df2 = new DecimalFormat("##.00");

	public static String format(Double d, Integer docNum) {
		if (d == null) {
			return "";
		}
		if (docNum == null) {
			return df2.format(d);
		} else if (docNum == 1) {
			return df1.format(d);
		}
		return df2.format(d);
	}
	
	public static void main(String[] args) {
		Double d = 13031.3667002519d;
		System.out.println(format(d, null));
	}

}
