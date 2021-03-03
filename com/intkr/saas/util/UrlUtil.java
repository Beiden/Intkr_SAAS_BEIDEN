package com.intkr.saas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.toolbox.BaseToolBox;

/**
 * 
 * @author Beiden
 * @date 2011-5-25 上午8:14:02
 * @version 1.0
 */
public class UrlUtil extends BaseToolBox {

	protected static Logger logger = LoggerFactory.getLogger(UrlUtil.class);

	public static String addParam(String url, String key, String value) {
		try {
			if (url == null || "".equals(url)) {
				return "";
			}
			String lastParam = "";
			if (url.contains("?")) {
				lastParam = url.substring(url.lastIndexOf("?"));
				url = url.substring(0, url.lastIndexOf("?"));
			}
			String html = "";
			if (url.contains(".html")) {
				html = ".html";
			} else if (url.contains(".htm")) {
				html = ".htm";
			}
			if (url.contains("-param-") || url.contains("-p-")) {
				url = url.substring(0, url.lastIndexOf(html)) + "-" + key + "-" + value;
			} else {
				url = url.substring(0, url.lastIndexOf(html)) + "-p-" + key + "-" + value;
			}
			return url + html + lastParam;
		} catch (Exception e) {
			logger.error("url=" + url + ";key=" + key + ";value=" + value, e);
			return "";
		}
	}

}
