package com.intkr.saas.module.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.engine.CheckCodeEngine;
import com.intkr.saas.engine.SmsCheckCodeEngine;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.user.auth.UpperRightAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-28 下午3:14:50
 * @version 1.0
 */
public class FindPasswordByPhoneAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	private UpperRightAction upperRightAction = IOC.get(UpperRightAction.class);

	public void doSendCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!CheckCodeEngine.check(request, "checkCode")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "验证码错误，请点击验证码刷新，重新输入！");
			return;
		}
		request.getSession().removeAttribute("checkCode");
		String phone = RequestUtil.getParam(request, "phone");
		if (phone == null || "".equals(phone) || phone.length() != 11) {
			request.setAttribute("msg", "请输入正确的手机！");
			request.setAttribute("result", false);
			return;
		}
		if (userManager.isPhoneCanUse(SessionClient.getSaasId(request), phone)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "手机帐号不存在！");
			return;
		}
		SmsCheckCodeEngine.sendCheckCode(request, response);
		request.setAttribute("result", true);
		request.setAttribute("msg", "验证码已发送！");
	}

	public void doVerifyPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		upperRightAction.doGetByPhone(request, response);
		if ((Boolean) request.getAttribute("result")) {
			String phone = RequestUtil.getParam(request, "phone");
			UserBO user = userManager.getByPhone(SessionClient.getSaasId(request), phone);
			SessionClient.login(request, response, user.getId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "验证成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请进行身份验证后再操作！");
		}
	}

	public void doResetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!SessionClient.hasUpperRight(request)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请认证后再进行操作！");
			return;
		}
		UpperRightAction.doRemoveRight(request);
		String password = RequestUtil.getParam(request, "password");
		UserBO userBO = SessionClient.getLoginUser(request);
		userBO.setPassword(password);
		userBO.encryptPassword();
		userManager.update(userBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新重置成功");
	}

}
