package com.intkr.saas.util;

import java.util.Date;

import com.alibaba.citrus.service.pull.ToolFactory;
import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2011-7-15 上午11:51:24
 * @version 1.0
 */
public class FormatUtil extends BaseToolBox {

	public static String formatVolume(Double volume) {
		if (volume == null) {
			return "";
		}
		Double cm3 = 1000d;
		Double m3 = cm3 * 1000d;
		if (volume >= m3) {
			Double f = (Double) volume / m3;
			return String.format(f > 100 ? "%.0f m<sup>3</sup>" : "%.1f m<sup>3</sup>", f);
		} else if (volume >= cm3) {
			Double f = (Double) volume / cm3;
			return String.format(f > 100 ? "%.0f cm<sup>3</sup>" : "%.1f cm<sup>3</sup>", f);
		}
		return String.format(volume > 100 ? "%.0f mm<sup>3</sup>" : "%.1f mm<sup>3</sup>", volume);
	}

	public static String formatSearchNum(Long num) {
		if (num == null) {
			return "";
		}
		Double qian = 1000D;
		Double wan = qian * 10;
		Double baiWan = wan * 100;
		if (num >= baiWan) {
			Double f = num.doubleValue() / baiWan;
			return String.format(f > 100 ? "%.0f 百万" : "%.1f 百万", f);
		} else if (num >= wan) {
			Double f = (Double) num.doubleValue() / wan;
			return String.format(f > 100 ? "%.0f 万" : "%.1f 万", f);
		}
		return String.format("%.0f", num.doubleValue());
	}

	public static String formatTime(Date time) {
		if (time == null) {
			return "";
		}
		Long newTime = new Date().getTime() / 1000;
		Long descTime = time.getTime() / 1000;
		Long betweenTime = newTime - descTime;
		if (betweenTime < 60) {// 一分钟内
			return "刚刚";
		}
		if (betweenTime < 60 * 60) {// 一小时内
			long minute = betweenTime / 60;
			return minute + "分钟前";
		}
		if (betweenTime < 60 * 60 * 24) {// 一天内
			long minute = betweenTime / 60 / 60;
			return minute + "小时前";
		}
		if (betweenTime < 60 * 60 * 24 * 30) {// 30天内
			return DateUtil.format("MM-dd", time);
		}
		if (betweenTime < 60 * 60 * 24 * 365) {// 1年内
			return DateUtil.format("MM-dd", time);
		}
		return DateUtil.format("yyyy-MM", time);
	}

	public static String formatLength(Double length) {
		if (length == null) {
			return "";
		}
		length = length / 10;
		return String.format("%.1f", length);
	}

}
