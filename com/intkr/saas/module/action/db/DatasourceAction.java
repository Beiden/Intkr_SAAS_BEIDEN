package com.intkr.saas.module.action.db;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.db.DatasourceBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.db.DatasourceManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
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
public class DatasourceAction extends BaseAction<DatasourceBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatasourceManager datasourceManager = IOC.get(DatasourceManager.class);

	public DatasourceBO getBO(HttpServletRequest request) {
		DatasourceBO datasourceBO = getParameter(request);
		return datasourceBO;
	}

	public static DatasourceBO getParameter(HttpServletRequest request) {
		DatasourceBO datasourceBO = new DatasourceBO();
		datasourceBO.setId(RequestUtil.getParam(request, "id", Long.class));
		datasourceBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		datasourceBO.setName(RequestUtil.getParam(request, "name", String.class));
		datasourceBO.setHost(RequestUtil.getParam(request, "host", String.class));
		datasourceBO.setPort(RequestUtil.getParam(request, "port", String.class));
		datasourceBO.setUser(RequestUtil.getParam(request, "user", String.class));
		datasourceBO.setPwd(RequestUtil.getParam(request, "pwd", String.class));
		datasourceBO.setCharacterEncoding(RequestUtil.getParam(request, "characterEncoding", String.class));
		datasourceBO.setDbName(RequestUtil.getParam(request, "dbName", String.class));
		datasourceBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		if (RequestUtil.existParam(request, "keywordLike")) {
			String keywordLike = RequestUtil.getParam(request, "keywordLike");
			String keywordLikeSQL = "%" + RequestUtil.getParam(request, "keywordLike") + "%";
			datasourceBO.setQuery("keywordLike", keywordLike);
			datasourceBO.setQuery("keywordLikeSQL", keywordLikeSQL);
		}
		if (RequestUtil.existParam(request, "databaseId")) {
			datasourceBO.setQuery("databaseId", RequestUtil.getParam(request, "databaseId", Long.class));
		}
		PagerUtil.initPage(request, datasourceBO);
		return datasourceBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return datasourceManager;
	}

}
