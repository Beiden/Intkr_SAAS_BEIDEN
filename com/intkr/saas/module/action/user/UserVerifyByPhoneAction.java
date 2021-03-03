package com.intkr.saas.module.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.user.UserStatus;
import com.intkr.saas.engine.SmsCheckCodeEngine;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-11 下午4:10:52
 * @version 1.0
 */
public class UserVerifyByPhoneAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void doSendAccountVerifyPhoneMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phone = RequestUtil.getParam(request, "phone");
		if (userManager.isPhoneCanUse(SessionClient.getSaasId(request), phone)) {
			request.setAttribute("result", true);
			request.setAttribute("msg", "验证码已发送！");
			SmsCheckCodeEngine.sendCheckCode(request, response);
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "手机已注册！");
		}
	}

	public void doVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String phoneCheckCode = RequestUtil.getParam(request, "phoneCheckCode");
		String phone = RequestUtil.getParam(request, "phone");
		String phoneSession = SmsCheckCodeEngine.getPhone(phoneCheckCode);
		if (phoneSession == null || !phoneSession.equals(phone)) {
			request.setAttribute("msg", "手机验证码错误！");
			request.setAttribute("result", false);
			return;
		}
		UserBO user = userManager.get(SessionClient.getLoginUserId(request));
		user.setPhone(phone);
		user.setStatus(UserStatus.Normal.getCode());
		userManager.update(user);
		SessionClient.login(request, response, user.getId());
		request.setAttribute("msg", "验证成功！");
		request.setAttribute("result", true);
	}

}
