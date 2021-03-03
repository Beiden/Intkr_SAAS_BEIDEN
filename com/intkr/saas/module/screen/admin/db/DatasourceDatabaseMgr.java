package com.intkr.saas.module.screen.admin.db;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.manager.db.DatabaseManager;
import com.intkr.saas.manager.db.DatasourceDatabaseManager;
import com.intkr.saas.manager.db.DatasourceManager;
import com.intkr.saas.module.action.db.DatasourceDatabaseAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table datasource_database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:51:57
 * @version 1.0
 */
public class DatasourceDatabaseMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatabaseManager databaseManager = IOC.get(DatabaseManager.class);

	private DatasourceManager datasourceManager = IOC.get(DatasourceManager.class);

	private DatasourceDatabaseManager datasourceDatabaseManager = IOC.get(DatasourceDatabaseManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DatasourceDatabaseBO query = DatasourceDatabaseAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = datasourceDatabaseManager.selectAndCount(query);
		List<DatasourceDatabaseBO> list = query.getDatas();
		for (DatasourceDatabaseBO bo : list) {
			databaseManager.fill(bo);
			datasourceManager.fill(bo);
		}
		request.setAttribute("query", query);
		request.setAttribute("list", list);
		UserLogClient.log(request, "打开", "管理");
	}

}
