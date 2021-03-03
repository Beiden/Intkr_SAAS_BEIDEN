package com.intkr.saas.module.screen.admin.shop.msg;


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
 * @date 2016-4-24 下午5:41:12
 * @version 1.0
 */
public class ChatMsgSend {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String toUserId = RequestUtil.getParam(request, "toUserId");
		UserBO user = userManager.get(toUserId);
		request.setAttribute("toUser", user);
	}

}
