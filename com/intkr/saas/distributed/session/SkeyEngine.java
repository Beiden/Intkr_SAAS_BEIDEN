package com.intkr.saas.distributed.session;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.AESUtil;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2011-4-23 下午3:11:00
 * @version 1.0
 */
public class SkeyEngine {

	protected static final Logger logger = LoggerFactory.getLogger(SkeyEngine.class);

	public static String encodeRules = "Intkr";

	public static String urlKey = "skey";

	private static String timePartern = "yyMMddHHmm";

	public static String getSkey(Long userId) {
		Date now = new Date();
		String dateString = DateUtil.format(timePartern, now);
		String skey = AESUtil.AESEncode(encodeRules, userId.toString() + "|" + dateString);
		return skey;
	}

	private static Long getUserId(String skey) {
		if (skey == null || "".equals(skey)) {
			return null;
		}
		try {
			String content = AESUtil.AESDncode(encodeRules, skey);
			String[] values = content.split("\\|");
			if (outOfTime(values[1])) {// skey超时
				return null;
			}
			return Long.valueOf(values[0]);
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean outOfTime(String timeString) {
		Date time = DateUtil.parse(timeString, timePartern);
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(Calendar.MINUTE, 1);
		Date lastTime = c.getTime();
		Date now = new Date();
		if (now.after(lastTime)) {
			return true;
		}
		return false;
	}

	public static boolean login(HttpServletRequest request, HttpServletResponse response) {
		if (!RequestUtil.existParam(request, urlKey)) {
			return false;
		}
		String skey = RequestUtil.getParam(request, urlKey);
		Long userId = SkeyEngine.getUserId(skey);
		if (userId == null || userId <= 0L) {
			return false;
		}
		return SessionClientDistImpl.login(request, response, userId);
	}

}
