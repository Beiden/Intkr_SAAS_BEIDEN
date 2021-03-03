package com.intkr.saas.module.screen.admin.opa.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.OaClientBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.opa.OaClientManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 客户端
 * 
 * @table oa_client
 * 
 * @author Beiden
 * @date 2020-11-04 20:10:23
 * @version 1.0
 */
public class OaClientAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OaClientManager manager = IOC.get(OaClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String oaClientId = RequestUtil.getParam(request, "oaClientId");
		OaClientBO oaClient = manager.get(oaClientId);
		request.setAttribute("oaClient", oaClient);
		request.setAttribute("addId", manager.getId());
	}

}
