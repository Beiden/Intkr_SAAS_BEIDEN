package com.intkr.saas.module.screen.admin.api.info.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiExtBO;
import com.intkr.saas.manager.opa.ApiExtManager;
import com.intkr.saas.module.action.opa.ApiExtAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author Beiden
 * @date 2020-7-24 上午7:51:25
 * @version 1.0.0
 */
public class Search {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiExtManager manager = IOC.get(ApiExtManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ApiExtBO query = ApiExtAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

}
