package com.intkr.saas.module.action.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.engine.SmsCheckCodeEngine;
import com.intkr.saas.module.action.user.auth.UpperRightAction;

/**
 * 手机绑定
 * 
 * @author Beiden
 * @date 2016-5-30 上午11:22:42
 * @version 1.0
 */
public class PhoneAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!SessionClient.hasUpperRight(request)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请认证后再进行操作！");
			return;
		}

		UpperRightAction.doRemoveRight(request);
		request.setAttribute("msg", "修改成功！");
		request.setAttribute("result", true);
	}

	public void sendCheckCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SmsCheckCodeEngine.sendCheckCode(request, response);
	}

}
