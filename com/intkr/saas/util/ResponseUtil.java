package com.intkr.saas.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.util.StringUtil;

/**
 * 
 * @author Beiden
 * @date 2017-1-26 上午10:31:24
 * @version 1.0
 */
public class ResponseUtil {

	protected static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

	public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String location) {
		request.setAttribute("_redirect", true);
		request.getSession().setAttribute("$_sendRedirect", location);
		try {
			response.sendRedirect(location);
		} catch (IOException e) {
			throw new RuntimeException("", e);
		}
	}

	public static String getSenRedirect(HttpServletRequest request) {
		String url = (String) request.getSession().getAttribute("$_sendRedirect");
		if (StringUtil.isBlank(url)) {
			url = request.getRequestURI();
		}
		return url;
	}

}
