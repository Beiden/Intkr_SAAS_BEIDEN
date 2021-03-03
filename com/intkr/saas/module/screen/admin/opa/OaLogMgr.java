package com.intkr.saas.module.screen.admin.opa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.opa.OaLogBO;
import com.intkr.saas.manager.opa.OaLogManager;
import com.intkr.saas.module.action.opa.OaLogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 接口日志
 * 
 * @table oa_log
 * 
 * @author Beiden
 * @date 2020-11-04 20:43:19
 * @version 1.0
 */
public class OaLogMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OaLogManager manager = IOC.get(OaLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		OaLogBO query = OaLogAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
