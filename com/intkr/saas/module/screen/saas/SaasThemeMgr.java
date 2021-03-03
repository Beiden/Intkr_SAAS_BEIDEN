package com.intkr.saas.module.screen.saas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.manager.saas.SaasThemeManager;
import com.intkr.saas.module.action.saas.SaasThemeAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class SaasThemeMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasThemeManager manager = IOC.get(SaasThemeManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SaasThemeBO query = SaasThemeAction.getParameter(request);
		if (!RequestUtil.existParam(request, "orderBy")) {
			query.setQuery("orderBy", "sort");
		}
		if (!RequestUtil.existParam(request, "order")) {
			query.setQuery("order", "desc");
		}
		manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
