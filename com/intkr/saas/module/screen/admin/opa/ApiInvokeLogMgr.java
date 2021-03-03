package com.intkr.saas.module.screen.admin.opa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiInvokeLogBO;
import com.intkr.saas.manager.opa.ApiInvokeLogManager;
import com.intkr.saas.module.action.opa.ApiInvokeLogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_invoke_log_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiInvokeLogMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiInvokeLogManager manager = IOC.get(ApiInvokeLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ApiInvokeLogBO query = ApiInvokeLogAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

}
