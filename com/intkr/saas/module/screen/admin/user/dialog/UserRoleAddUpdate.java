package com.intkr.saas.module.screen.admin.user.dialog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.user.UserRoleBO;
import com.intkr.saas.manager.user.UserRoleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class UserRoleAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRoleManager manager = IOC.get(UserRoleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userRoleId = RequestUtil.getParam(request, "userRoleId");
		UserRoleBO userRole = manager.get(userRoleId);
		request.setAttribute("userRole", userRole);
		request.setAttribute("addId", manager.getId());
	}

}
