package com.intkr.saas.module.screen.session.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-26 下午4:19:14
 * @version 1.0
 */
public class IsPhoneCanUse {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");
		request.setAttribute("phone", phone);
		if (phone == null || "".equals(phone)) {
			request.setAttribute("result", false);
		}
		if (userManager.isPhoneCanUse(SessionClient.getSaasId(request), phone)) {
			request.setAttribute("result", true);
		} else {
			request.setAttribute("result", false);
		}
	}

}
