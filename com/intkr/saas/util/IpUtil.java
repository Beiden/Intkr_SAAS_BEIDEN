package com.intkr.saas.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午11:12:09
 * @version 1.0
 */
public class IpUtil {

	/**
	 * 
	 * proxy_set_header Host $host;
	 * 
	 * proxy_set_header X-Real-IP $remote_addr;
	 * 
	 * proxy_set_header REMOTE-HOST $remote_addr;
	 * 
	 * proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
			ip = request.getRemoteAddr();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	public static boolean hasIp(HttpServletRequest request) {
		return getIp(request) != null;
	}

}
