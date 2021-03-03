package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.module.action.user.UserAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:46:51
 * @version 1.0
 */
public class UserMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected UserManager userManager = IOC.get(UserManager.class);

	protected RoleManager roleManager = IOC.get(RoleManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserBO query = UserAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = userManager.selectAndCount(query);
		query.setDatas(roleManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
