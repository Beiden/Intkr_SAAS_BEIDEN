package com.intkr.saas.module.action.db;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.db.DatasourceDatabaseManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
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
public class DatasourceDatabaseAction extends BaseAction<DatasourceDatabaseBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatasourceDatabaseManager datasourceDatabaseManager = IOC.get(DatasourceDatabaseManager.class);

	public DatasourceDatabaseBO getBO(HttpServletRequest request) {
		DatasourceDatabaseBO datasourceDatabaseBO = getParameter(request);
		return datasourceDatabaseBO;
	}

	public static DatasourceDatabaseBO getParameter(HttpServletRequest request) {
		DatasourceDatabaseBO datasourceDatabaseBO = new DatasourceDatabaseBO();
		datasourceDatabaseBO.setId(RequestUtil.getParam(request, "id", Long.class));
		datasourceDatabaseBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		datasourceDatabaseBO.setDatasourceId(RequestUtil.getParam(request, "datasourceId", Long.class));
		datasourceDatabaseBO.setDatabaseId(RequestUtil.getParam(request, "databaseId", Long.class));
		datasourceDatabaseBO.setIsDefault(RequestUtil.getParam(request, "isDefault", String.class));
		datasourceDatabaseBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, datasourceDatabaseBO);
		return datasourceDatabaseBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return datasourceDatabaseManager;
	}

}
