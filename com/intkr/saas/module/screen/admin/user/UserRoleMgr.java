package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.UserRoleBO;
import com.intkr.saas.manager.user.UserRoleManager;
import com.intkr.saas.module.action.user.auth.UserRoleAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class UserRoleMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserRoleManager manager = IOC.get(UserRoleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserRoleBO query = UserRoleAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
