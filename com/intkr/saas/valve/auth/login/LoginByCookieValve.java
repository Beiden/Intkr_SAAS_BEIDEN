package com.intkr.saas.valve.auth.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.valve.Valve;

/**
 * 前台自动登录功能
 * 
 * @author Beiden
 * @date 2011-10-3 上午11:12:35
 * @version 1.0
 */
public class LoginByCookieValve implements Valve {

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		SessionClient.loginByCookie(request, response);
		return true;
	}

}