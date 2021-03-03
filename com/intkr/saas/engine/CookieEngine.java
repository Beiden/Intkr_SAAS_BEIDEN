package com.intkr.saas.engine;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午11:13:59
 * @version 1.0
 */
public class CookieEngine {

	private static String rememberLoginUserId = "_l_u_i";// 用于记录自动登录的用户ID

	private static String cookieCodeKey = "_m_p_c_c";// 用cookie来标识客户端

	public static void setRememberLoginUserId(HttpServletResponse response, Long userId, Integer maxAge) {
		Cookie nameCookie = new Cookie(CookieEngine.rememberLoginUserId, userId + "");
		nameCookie.setMaxAge(maxAge);
		setCookie(response, nameCookie);
	}

	public static Long getRememberLoginUserId(HttpServletRequest request) {
		String value = getValue(request, rememberLoginUserId);
		if (value == null || "".equals(value)) {
			return null;
		}
		try {
			return Long.valueOf(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static void removeLoginUserIdKey(HttpServletRequest request, HttpServletResponse response) {
		remove(request, response, rememberLoginUserId);
	}

	public static boolean isExistCookieCode(HttpServletRequest request) {
		return isExist(request, CookieEngine.cookieCodeKey);
	}

	public static String getCookieCode(HttpServletRequest request) {
		return getValue(request, CookieEngine.cookieCodeKey);
	}

	public static String random() {
		Random random = new Random();
		return random.nextInt(9999) + "";
	}

	public static void setCookieCode(HttpServletRequest request, HttpServletResponse response) {
		String code = "40f6_" + random() + "_8ef6";
		Cookie nameCookie = new Cookie(CookieEngine.cookieCodeKey, code);
		nameCookie.setMaxAge(24 * 60 * 60);// 单位，秒,设置存一天
		setCookie(response, nameCookie);
	}

	public static boolean checkCookieCode(HttpServletRequest request, HttpServletResponse response) {
		String cookieCode = getValue(request, CookieEngine.cookieCodeKey);
		if (cookieCode == null) {
			return false;
		}
		if (!cookieCode.startsWith("40f6_")) {
			return false;
		}
		if (!cookieCode.endsWith("_8ef6")) {
			return false;
		}
		return true;
	}

	public static String getValue(HttpServletRequest request, String key) {
		if (key == null || "".equals(key)) {
			return null;
		}
		Cookie cookie = get(request, key);
		if (cookie == null) {
			return null;
		} else {
			return cookie.getValue();
		}
	}

	public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
		Cookie cookie = get(request, key);
		if (cookie != null) {
			cookie.setMaxAge(0);
			setCookie(response, cookie);
		}
	}

	public static Cookie get(HttpServletRequest request, String key) {
		if (key == null || "".equals(key)) {
			return null;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			if (null != cookies) {
				for (Cookie cookie : cookies) {
					if (key.equalsIgnoreCase(cookie.getName())) {
						return cookie;
					}
				}
			}
		}
		return null;
	}

	public static boolean isExist(HttpServletRequest request, String key) {
		return get(request, key) != null;
	}

	public static void setCookie(HttpServletResponse response, Cookie cookie) {
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
