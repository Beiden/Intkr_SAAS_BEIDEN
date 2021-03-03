package com.intkr.saas.module.action.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;

/**
 * 
 * @author Beiden
 * @date 2011-4-19 下午12:46:30
 * @version 1.0
 */
public class SignOutAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void doSignOut(HttpServletRequest request, HttpServletResponse response) {
		if (!SessionClient.isLogin(request)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "您没有登录!");
		} else {
			SessionClient.logout(request, response);
			request.setAttribute("result", true);
			request.setAttribute("msg", "注销成功!");
		}
	}

}
