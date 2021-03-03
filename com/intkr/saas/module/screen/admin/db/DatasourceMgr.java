package com.intkr.saas.module.screen.admin.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.manager.db.DatasourceManager;
import com.intkr.saas.module.action.db.DatasourceAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table datasource_tab
 * 
 * @author Beiden
 * @date 2020-04-02 18:59:02
 * @version 1.0
 */
public class DatasourceMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatasourceManager manager = IOC.get(DatasourceManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DatasourceBO query = DatasourceAction.getParameter(request);
		query.setQuery("orderBy", "name");
		query.setQuery("order", "asc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

}
