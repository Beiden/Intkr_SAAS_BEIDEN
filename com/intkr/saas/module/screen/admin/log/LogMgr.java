package com.intkr.saas.module.screen.admin.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.log.LogBO;
import com.intkr.saas.manager.log.LogManager;
import com.intkr.saas.module.action.log.LogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 上午9:19:22
 * @version 1.0
 */
public class LogMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LogManager logManager = IOC.get(LogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LogBO query = LogAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = logManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
