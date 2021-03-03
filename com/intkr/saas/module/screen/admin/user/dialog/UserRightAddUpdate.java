package com.intkr.saas.module.screen.admin.user.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.UserRightBO;
import com.intkr.saas.manager.user.UserRightManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class UserRightAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRightManager manager = IOC.get(UserRightManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String userRightId = RequestUtil.getParam(request, "userRightId");
		UserRightBO userRight = manager.get(userRightId);
		request.setAttribute("userRight", userRight);
		request.setAttribute("addId", manager.getId());
	}

}
