package com.intkr.saas.module.screen.admin.shop.json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-21 下午10:37:45
 * @version 1.0
 */
public class User {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userId = RequestUtil.getParam(request, "userId");
		UserBO user = userManager.get(userId);
		request.setAttribute("user", user);
	}

}
