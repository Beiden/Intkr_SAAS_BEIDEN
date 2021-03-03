package com.intkr.saas.module.screen.admin.opa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.opa.OaClientAction;
import com.intkr.saas.domain.bo.opa.OaClientBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.opa.OaClientManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 客户端
 * 
 * @table oa_client
 * 
 * @author Beiden
 * @date 2020-11-04 20:10:22
 * @version 1.0
 */
public class OaClientMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OaClientManager manager = IOC.get(OaClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		OaClientBO query = OaClientAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
