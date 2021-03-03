package com.intkr.saas.module.screen.admin.opa.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.manager.opa.ApiManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class ApiAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiManager manager = IOC.get(ApiManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String apiId = RequestUtil.getParam(request, "apiId");
		ApiBO api = manager.get(apiId);
		request.setAttribute("api", api);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
