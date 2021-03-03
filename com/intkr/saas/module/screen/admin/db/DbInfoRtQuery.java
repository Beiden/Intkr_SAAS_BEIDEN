package com.intkr.saas.module.screen.admin.db;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.DatabaseBO;
import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.manager.db.DatabaseManager;
import com.intkr.saas.manager.db.DatasourceManager;
import com.intkr.saas.module.screen.admin.system.appearance.menu.MenuMgr;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table database_tab
 * 
 * @author Beiden
 * @date 2020-04-02 19:03:12
 * @version 1.0
 */
public class DbInfoRtQuery {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatabaseManager databaseManager = IOC.get(DatabaseManager.class);

	private DatasourceManager datasourceManager = IOC.get(DatasourceManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long databaseId = RequestUtil.getParam(request, "databaseId", Long.class);
		List<String> tableList = databaseManager.getTableNamesRt(databaseId);
		DatabaseBO database = databaseManager.get(databaseId);
		List<DatasourceBO> datasourceList = databaseManager.selectDatasource(databaseId);
		DatasourceBO datasource = getDatasource(request);
		request.setAttribute("database", database);
		request.setAttribute("datasource", datasource);
		request.setAttribute("datasourceList", datasourceList);
		request.setAttribute("menuList", TableSqlMgr.getMenu(request));
		request.setAttribute("tableList", tableList);
		request.setAttribute("database", database);
		UserLogClient.log(request, "打开", "管理");
	}

	private DatasourceBO getDatasource(HttpServletRequest request) {
		if (RequestUtil.existParam(request, "datasourceId")) {
			Long datasourceId = RequestUtil.getParam(request, "datasourceId", Long.class);
			DatasourceBO datasource = datasourceManager.get(datasourceId);
			return datasource;
		}
		Long databaseId = RequestUtil.getParam(request, "databaseId", Long.class);
		DatasourceBO datasource = databaseManager.getDefaultDatasource(databaseId);
		return datasource;
	}

}
