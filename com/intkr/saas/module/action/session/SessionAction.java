package com.intkr.saas.module.action.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2018-11-22
 * @version 1.0
 */
public class SessionAction {

	public void doIsLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (SessionClient.isLogin(request)) {
			request.setAttribute("result", true);
			request.setAttribute("msg", "您已登录！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "您未登录！");
		}
	}

	public void doIsSeller(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (SessionClient.isLogin(request)) {
			UserBO user = SessionClient.getLoginUser(request);
			if ("admin".equals(user.getSaasRole())) {
				request.setAttribute("result", true);
				request.setAttribute("msg", "您是卖家！");
				return;
			}
			if (user.getShopId() != null) {
				request.setAttribute("result", true);
				request.setAttribute("msg", "您是卖家！");
				return;
			}
			if (user.getShop() != null) {
				request.setAttribute("result", true);
				request.setAttribute("msg", "您是卖家！");
				return;
			}
			request.setAttribute("result", false);
			request.setAttribute("msg", "您不是卖家！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "您未登录！");
		}
	}

}
