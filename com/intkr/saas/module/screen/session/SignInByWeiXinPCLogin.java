package com.intkr.saas.module.screen.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.facade.wx.WeiXinEngine;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 下午11:05:38
 * @version 1.0
 */
public class SignInByWeiXinPCLogin {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long registerTractionId = RequestUtil.getParam(request, "registerTractionId", Long.class);
		String tranctionStatus = WeiXinEngine.getTractionValue(registerTractionId);
		if ("waiting".equals(tranctionStatus)) {
			request.setAttribute("result", false);
			request.setAttribute("code", "waiting");
		} else if (tranctionStatus == null) {
			request.setAttribute("result", false);
			request.setAttribute("code", "timeOut");
		} else {
			SessionClient.login(request, response, Long.valueOf(tranctionStatus));
			request.setAttribute("result", true);
		}
	}

}
