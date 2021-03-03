package com.intkr.saas.module.screen.saas.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.saas.SaasConfigBO;
import com.intkr.saas.manager.saas.SaasConfigManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class SaasConfigAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasConfigManager manager = IOC.get(SaasConfigManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = RequestUtil.getParam(request, "saasConfigId");
		SaasConfigBO appConfig = manager.get(id);
		request.setAttribute("saasConfig", appConfig);
		request.setAttribute("addId", manager.getId());
	}

}
