package com.intkr.saas.module.screen.admin.opa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiTestCaseBO;
import com.intkr.saas.manager.opa.ApiTestCaseManager;
import com.intkr.saas.module.action.opa.ApiTestCaseAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_test_case_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class ApiTestCaseMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiTestCaseManager manager = IOC.get(ApiTestCaseManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ApiTestCaseBO query = ApiTestCaseAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

}
