package com.intkr.saas.module.screen.saas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.manager.saas.SaasDomainManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.module.action.saas.SaasDomainAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class SaasDomainMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasDomainManager manager = IOC.get(SaasDomainManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SaasDomainBO query = SaasDomainAction.getParameter(request);
		manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		SaasAction.fillSaas(request);
	}

}
