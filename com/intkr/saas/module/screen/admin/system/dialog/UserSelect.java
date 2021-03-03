package com.intkr.saas.module.screen.admin.system.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.user.UserAction;

/**
 * 
 * @author Beiden
 * @date 2011-11-22 下午3:56:04
 * @version 1.0
 */
public class UserSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserBO query = UserAction.getParameter(request);
		query = userManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}