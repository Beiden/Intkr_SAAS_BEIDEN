package com.intkr.saas.module.screen.admin.api.info.mgr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiBO;
import com.intkr.saas.domain.bo.opa.AppBO;
import com.intkr.saas.manager.opa.ApiManager;
import com.intkr.saas.manager.opa.AppManager;
import com.intkr.saas.module.action.opa.AppAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author Beiden
 * @date 2020-7-24 上午7:51:25
 * @version 1.0.0
 */
public class Index {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AppManager manager = IOC.get(AppManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		AppBO query = AppAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "asc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("app_list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

	public static void initMenu(HttpServletRequest request) {
		Long appId = RequestUtil.getParam(request, "app_id", Long.class);
		ApiManager apiManager = IOC.get(ApiManager.class);
		List<ApiBO> apiListReturn = apiManager.getMenuTree(appId);
		request.setAttribute("api_list", apiListReturn);
	}

}
