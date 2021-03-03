package com.intkr.saas.module.screen.admin.sign.impl;

/**
 * 
 * @author Beiden
 * @date 2018-9-27
 * @version 1.0
 */
public class SignUtil {

	public static String getDomain(String redirectUri) {
		if (redirectUri == null) {
			return null;
		}
		String domain = "";
		if (redirectUri.contains("//")) {
			domain = redirectUri.substring(redirectUri.indexOf("//") + 2);
		}
		if (redirectUri.contains("/")) {
			domain = domain.substring(0, domain.indexOf("/"));
		}
		return domain;
	}

}
