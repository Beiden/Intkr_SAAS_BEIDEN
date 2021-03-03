package com.intkr.saas.valve.auth.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.distributed.session.SkeyEngine;
import com.intkr.saas.valve.Valve;

/**
 * 
 * @author Beiden
 * @date 2017-12-3
 * @version 1.0
 */
public class LoginBySkeyValve implements Valve {

	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		SkeyEngine.login(request, response);
		return true;
	}

}