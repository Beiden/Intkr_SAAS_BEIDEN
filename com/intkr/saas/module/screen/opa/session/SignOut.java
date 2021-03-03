package com.intkr.saas.module.screen.opa.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.session.SignOutAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 注册
 * 
 * @author Beiden
 * @date 2011-6-5 上午8:10:20
 * @version 1.0
 */
public class SignOut {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SignOutAction action = IOC.get(SignOutAction.class);
		action.doSignOut(request, response);
	}

}
