package com.intkr.saas.module.screen.admin.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.log.SysLogBO;
import com.intkr.saas.manager.log.SysLogManager;
import com.intkr.saas.module.action.log.SysLogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:28:56
 * @version 1.0
 */
public class SysLogMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SysLogManager sysLogManager = IOC.get(SysLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SysLogBO query = SysLogAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = sysLogManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
