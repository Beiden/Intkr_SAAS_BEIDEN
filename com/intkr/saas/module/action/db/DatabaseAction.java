package com.intkr.saas.module.action.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.DatabaseBO;
import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.domain.bo.db.SqlResultBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.db.DatabaseManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.module.screen.admin.db.DbInfoRtMgr;
import com.intkr.saas.util.PagerUtil;
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
public class DatabaseAction extends BaseAction<DatabaseBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatabaseManager databaseManager = IOC.get(DatabaseManager.class);

	public DatabaseBO getBO(HttpServletRequest request) {
		DatabaseBO databaseBO = getParameter(request);
		return databaseBO;
	}

	public static DatabaseBO getParameter(HttpServletRequest request) {
		DatabaseBO databaseBO = new DatabaseBO();
		databaseBO.setId(RequestUtil.getParam(request, "id", Long.class));
		databaseBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		databaseBO.setDbName(RequestUtil.getParam(request, "dbName", String.class));
		databaseBO.setNote(RequestUtil.getParam(request, "note", String.class));
		databaseBO.setCharacterEncoding(RequestUtil.getParam(request, "characterEncoding", String.class));
		databaseBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		if (RequestUtil.existParam(request, "datasourceId")) {
			databaseBO.setQuery("datasourceId", RequestUtil.getParam(request, "datasourceId", Long.class));
		}
		if (RequestUtil.existParam(request, "dbNameLike")) {
			databaseBO.setQuery("dbNameLike", RequestUtil.getParam(request, "dbNameLike"));
			databaseBO.setQuery("dbNameLikeSQL", "%" + RequestUtil.getParam(request, "dbNameLike") + "%");
		}
		PagerUtil.initPage(request, databaseBO);
		return databaseBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return databaseManager;
	}

	public void doExecuteSql(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long databaseId = RequestUtil.getParam(request, "databaseId", Long.class);
		String sql = RequestUtil.getParam(request, "sql");
		DatabaseBO database = databaseManager.get(databaseId);
		DatasourceBO datasource = DbInfoRtMgr.getDatasource(request);
		SqlResultBO sqlResult = databaseManager.executeSql(datasource, database, sql);
		request.setAttribute("result", true);
		request.setAttribute("msg", "执行成功");
		request.setAttribute("database", database);
		request.setAttribute("datasource", datasource);
		request.setAttribute("data", sqlResult);
		UserLogClient.log(request, "执行", "SQL");
	}

}
