package com.intkr.saas.module.screen.admin.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.log.SignLogBO;
import com.intkr.saas.manager.log.SignLogManager;
import com.intkr.saas.module.action.log.SignLogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 上午9:19:22
 * @version 1.0
 */
public class SignLogMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SignLogManager signLogManager = IOC.get(SignLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SignLogBO query = SignLogAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = signLogManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
