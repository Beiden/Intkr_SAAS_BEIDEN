package com.intkr.saas.module.screen.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.facade.wx.WeiXinEngine;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 下午11:05:38
 * @version 1.0
 */
public class SignUpByWeiXinPCConfirmLoginResult {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long registerTractionId = (Long) request.getSession().getAttribute("registerTractionId");
		Long userId = SessionClient.getLoginUserId(request);
		WeiXinEngine.updateTractionValue(registerTractionId, userId + "");
	}

}
