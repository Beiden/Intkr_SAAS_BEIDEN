package com.intkr.saas.module.screen.saas;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class SaasIndex {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
		SaasClientBO saas = saasClientManager.getFull(saasId);
		request.setAttribute("saas", saas);
	}

}
