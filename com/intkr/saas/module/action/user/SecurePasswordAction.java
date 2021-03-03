package com.intkr.saas.module.action.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.user.auth.UpperRightAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 二次密码
 * 
 * @author Beiden
 * @date 2016-5-30 下午6:13:45
 * @version 1.0
 */
public class SecurePasswordAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = SessionClient.getLoginUserId(request);
		UserBO user = userManager.get(userId);
		if (!SessionClient.hasUpperRight(request) && user.getSecurePassword() != null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请认证后再进行操作！");
			return;
		}
		UpperRightAction.doRemoveRight(request);
		if (!RequestUtil.existParam(request, "securePassword")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "请填写安全密码");
			return;
		}

		user.setSecurePassword(RequestUtil.getParam(request, "securePassword"));
		user.encryptSecurePassword();
		userManager.update(user);
		SessionClient.login(request, response, user.getId());
		request.setAttribute("result", true);
		request.setAttribute("msg", "修改成功！");
	}

	public void doIsInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = SessionClient.getLoginUserId(request);
		boolean result = userManager.isSecurePasswordInit(userId);
		request.setAttribute("data", result);
		request.setAttribute("result", true);
		request.setAttribute("msg", "获取成功！");
	}

}
