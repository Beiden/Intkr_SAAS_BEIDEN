package com.intkr.saas.module.screen.admin.shop.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-8 下午3:57:48
 * @version 1.0
 */
public class UserJson {

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserBO query = new UserBO();
		Long userId = RequestUtil.getParam(request, "userId", Long.class);
		query.setId(userId);
		List<UserBO> list = userManager.select(query);
		request.setAttribute("list", list);
	}

}
