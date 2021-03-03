package com.intkr.saas.util;

import java.text.DecimalFormat;

import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2011-4-25 下午1:38:32
 * @version 1.0
 */
public class MoneyUtil extends BaseToolBox {

	public static String formatMao(Long data) {
		if (data == null) {
			return "0.00";
		}
		Double price = data / 100D;
		DecimalFormat df4 = new DecimalFormat("#,###,###,###,###,##0.0");
		String string = df4.format(price);
		return string;
	}

	public static String formatYuan(Long data) {
		if (data == null) {
			return "0.00";
		}
		Double price = data / 100D;
		DecimalFormat df3 = new DecimalFormat("#,###,###,###,###,##0");
		String string = df3.format(price);
		return string;
	}

	public static String format(Long data) {
		if (data == null) {
			return "0.00";
		}
		Double price = data / 100D;
		DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.00");
		String string = df.format(price);
		return string;
	}

	public static String format(Integer data) {
		if (data == null) {
			return "0.00";
		}
		Double price = data / 100D;
		DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.00");
		String string = df.format(price);
		return string;
	}

	public static String format2(Integer data) {
		if (data == null) {
			return "0.00";
		}
		Double price = data / 100D;
		DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.00");
		String string = df.format(price);
		return string;
	}

	public static String format2(Long data) {
		if (data == null) {
			return "";
		}
		Double price = data / 100D;
		DecimalFormat df2 = new DecimalFormat("#0.00");
		String string = df2.format(price);
		return string;
	}

	public static String format(double data) {
		DecimalFormat df2 = new DecimalFormat("#0.00");
		String string = df2.format(data);
		return string;
	}

	public static Long parse(String money) {
		if (money == null || "".equals(money)) {
			return null;
		}
		try {
			DecimalFormat df = new DecimalFormat("#,###,###,###,###,##0.00");
			Number n = df.parse(money);
			Double d = n.doubleValue();
			d = d * 100L;
			return Math.round(d);
		} catch (Exception e) {
			throw new RuntimeException("money=" + money, e);
		}
	}

	public static Long toLong(Double d) {
		d = d * 100;
		return Math.round(d);
	}

	public static void main(String[] args) {
		System.out.println(parse("289.00"));
	}

}
