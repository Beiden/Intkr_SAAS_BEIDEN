package com.intkr.saas.module.screen.admin.sign.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2017-12-3
 * @version 1.0
 */
public class SignIn {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (!RequestUtil.existParam(request, "loginRedirectUrl")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "参数异常：loginRedirectUrl必填!");
			return;
		}
		String loginRedirectUrl = RequestUtil.getParam(request, "loginRedirectUrl");
		request.setAttribute("loginRedirectUrl", loginRedirectUrl);
		request.setAttribute("fromUrl", request.getHeader("Referer"));
	}

}
