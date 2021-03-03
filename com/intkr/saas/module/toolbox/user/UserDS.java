package com.intkr.saas.module.toolbox.user;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class UserDS extends BaseToolBox {

	private UserManager userManager = IOC.get("UserManager");

	public Long getYue(HttpServletRequest request) {
		UserBO user = userManager.get(SessionClient.getLoginUserId(request));
		return user.getMoney();
	}

	public UserBO getById(Object idObj) {
		Long id = null;
		if (idObj instanceof String) {
			id = Long.valueOf((String) idObj);
		} else {
			id = (Long) idObj;
		}
		return userManager.get(id);
	}

}
