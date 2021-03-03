package com.intkr.saas.module.action.user.auth;

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
 * 高级权限
 * 
 * @author Beiden
 * @date 2016-5-30 下午6:13:45
 * @version 1.0
 */
public class UpperRightAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String key = "upper-right";

	private UserManager userManager = IOC.get(UserManager.class);

	public static void doRemoveRight(HttpServletRequest request) {
		request.getSession().removeAttribute(key);
		request.setAttribute("result", true);
		request.setAttribute("msg", "操作成功！");
	}

	public void doHasRight(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object obj = request.getSession().getAttribute(key);
		if (new Boolean(true).equals(obj)) {
			request.setAttribute("data", true);
			request.setAttribute("msg", "拥有最高级权限！");
		} else {
			request.setAttribute("data", false);
			request.setAttribute("msg", "不拥有最高级权限！");
		}
		request.setAttribute("result", true);
	}

	/**
	 * 二次密码是否正确
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doIsSecurePasswordEquals(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String securePassword = RequestUtil.getParam(request, "securePassword");
		UserBO user = SessionClient.getLoginUser(request);
		UserBO userBO = userManager.get(user.getId());
		if (!userBO.isSecurePasswordEquals(securePassword)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "安全密码不正确");
		} else {
			request.getSession().setAttribute(key, true);
			request.setAttribute("result", true);
			request.setAttribute("msg", "安全密码正确");
		}
	}

	public void doGetBySecurePassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!CheckCodeEngine.check(request, "checkCode")) {
			request.setAttribute("msg", "验证码错误，请点击验证码刷新，重新输入！");
			request.setAttribute("result", false);
			return;
		}
		String securePassword = RequestUtil.getParam(request, "securePassword");
		UserBO user = SessionClient.getLoginUser(request);
		if (!user.isSecurePasswordEquals(securePassword)) {
			request.setAttribute("msg", "安全密码不正确！");
			request.setAttribute("result", false);
			return;
		}
		request.getSession().setAttribute(key, true);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

	public void doGetByEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().setAttribute(key, true);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

	public void doGetByPhone(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!RequestUtil.existParam(request, "phone")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写正确的手机号码！");
			return;
		}
		String phone = RequestUtil.getParam(request, "phone");
		String phoneCheckCode = RequestUtil.getParam(request, "phoneCheckCode");
		if (!SmsCheckCodeEngine.isCodeValidate(phoneCheckCode, phone)) {
			request.setAttribute("msg", "手机验证码错误！");
			request.setAttribute("result", false);
			return;
		}
		SmsCheckCodeEngine.removeCode(phoneCheckCode);
		UserBO user = userManager.getByPhone(SessionClient.getSaasId(request), phone);
		if (user == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "手机帐号不存在！");
			return;
		}
		request.getSession().setAttribute(key, true);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

	public void doGetByWeiXin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().setAttribute(key, true);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

	public void doGetBySecureQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().setAttribute(key, true);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

}
