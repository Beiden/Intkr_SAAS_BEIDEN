package com.intkr.saas.util;

import java.text.DecimalFormat;

/**
 * 
 * @author Beiden
 * @date 2011-5-25 下午6:26:47
 * @version 1.0
 */
public class VolumeUtil {

	private static DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.000");

	private static DecimalFormat df2 = new DecimalFormat("#0.000");

	public static String format(Long data) {
		if (data == null) {
			return "";
		}
		Double price = data / 1000D;
		String string = df.format(price);
		return string;
	}

	public static String format2(Long data) {
		if (data == null) {
			return "";
		}
		Double price = data / 1000D;
		String string = df2.format(price);
		return string;
	}

	public static Long parse(String money) {
		try {
			Number n = df.parse(money);
			Double d = n.doubleValue();
			d = d * 1000L;
			return d.longValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Long toLong(Double d) {
		d = d * 1000;
		return d.longValue();
	}

}
