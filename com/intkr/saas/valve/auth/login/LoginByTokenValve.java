package com.intkr.saas.valve.auth.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.valve.Valve;

/**
 * 
 * @author Beiden
 * @date 2017-12-3
 * @version 1.0
 */
public class LoginByTokenValve implements Valve {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			SessionClientDistImpl.loginByToken(request, response);
		} catch (Exception e) {
			logger.error("SSO单点登录失败！", e);
			throw new RuntimeException("SSO单点登录失败！", e);
		}
		return true;
	}

}