package com.intkr.saas.module.screen.admin.db.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.DatasourceDatabaseBO;
import com.intkr.saas.manager.db.DatasourceDatabaseManager;
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
public class DatasourceDatabaseAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatasourceDatabaseManager manager = IOC.get(DatasourceDatabaseManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String datasourceDatabaseId = RequestUtil.getParam(request, "datasourceDatabaseId");
		DatasourceDatabaseBO datasourceDatabase = manager.get(datasourceDatabaseId);
		request.setAttribute("datasourceDatabase", datasourceDatabase);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
