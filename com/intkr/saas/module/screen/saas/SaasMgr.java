package com.intkr.saas.module.screen.saas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class SaasMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasClientManager manager = IOC.get(SaasClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SaasClientBO query = SaasAction.getParameter(request);
		if (!RequestUtil.existParam(request, "orderBy")) {
			query.setQuery("orderBy", "sort desc , id");
		}
		if (!RequestUtil.existParam(request, "order")) {
			query.setQuery("order", "desc");
		}
		manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
