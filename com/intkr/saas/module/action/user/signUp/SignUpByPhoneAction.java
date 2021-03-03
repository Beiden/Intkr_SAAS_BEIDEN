package com.intkr.saas.module.action.user.signUp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.engine.CheckCodeEngine;
import com.intkr.saas.engine.SmsCheckCodeEngine;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-7-8 上午8:21:28
 * @version 1.0
 */
public class SignUpByPhoneAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void doSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!RequestUtil.existParam(request, "phone")) {
			request.setAttribute("msg", "请填写注册手机！");
			request.setAttribute("result", false);
			return;
		}
		if (!RequestUtil.existParam(request, "password")) {
			request.setAttribute("msg", "请填写密码！");
			request.setAttribute("result", false);
			return;
		}
		if (!RequestUtil.existParam(request, "phoneCheckCode")) {
			request.setAttribute("msg", "请填写手机验证码！");
			request.setAttribute("result", false);
			return;
		}
		String phone = RequestUtil.getParam(request, "phone");
		String password = RequestUtil.getParam(request, "password");
		String phoneCheckCode = RequestUtil.getParam(request, "phoneCheckCode");
		if (!SmsCheckCodeEngine.isCodeValidate(phoneCheckCode, phone)) {
			request.setAttribute("msg", "手机验证码错误！");
			request.setAttribute("result", false);
			return;
		}
		SmsCheckCodeEngine.removeCode(phoneCheckCode);
		if (!userManager.isAccountCanUse(SessionClient.getSaasId(request), phone)) {
			request.setAttribute("msg", "手机号码已经注册！");
			request.setAttribute("result", false);
			return;
		}
		if (!userManager.isPhoneCanUse(SessionClient.getSaasId(request), phone)) {
			request.setAttribute("msg", "手机号码已经被其它帐号使用了！");
			request.setAttribute("result", false);
			return;
		}
		Long saasId = SessionClient.getSaasId(request);
		UserBO user = userManager.register(saasId, phone, password);
		SessionClient.login(request, response, user.getId());
		request.setAttribute("msg", "注册成功");
		request.setAttribute("result", true);
	}

	public void doSendCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!CheckCodeEngine.check(request, "checkCode")) {
			request.setAttribute("msg", "验证码错误，请点击验证码刷新，重新输入！");
			request.setAttribute("result", false);
			return;
		}
		if (!userManager.isPhoneCanUse(SessionClient.getSaasId(request), RequestUtil.getParam(request, "phone"))) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "手机已注册！");
			return;
		}
		SmsCheckCodeEngine.sendCheckCode(request, response);
		request.setAttribute("result", true);
		request.setAttribute("msg", "验证码已发送！");
	}

}
