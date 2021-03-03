package com.intkr.saas.module.screen.admin.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.log.UserLogBO;
import com.intkr.saas.manager.log.UserLogManager;
import com.intkr.saas.module.action.log.UserLogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-19 上午10:13:00
 * @version 1.0
 */
public class UserLog {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserLogManager userLogManager = IOC.get(UserLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		UserLogBO query = UserLogAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = userLogManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("addId", userLogManager.getId());
	}

}
