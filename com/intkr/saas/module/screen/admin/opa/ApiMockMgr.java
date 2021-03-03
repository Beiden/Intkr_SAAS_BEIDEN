package com.intkr.saas.module.screen.admin.opa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiMockBO;
import com.intkr.saas.manager.opa.ApiMockManager;
import com.intkr.saas.module.action.opa.ApiMockAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_mock_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiMockMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiMockManager manager = IOC.get(ApiMockManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ApiMockBO query = ApiMockAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

}
