package com.intkr.saas.module.screen.admin.db.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.DatabaseBO;
import com.intkr.saas.manager.db.DatabaseManager;
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
public class DatabaseAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatabaseManager manager = IOC.get(DatabaseManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String databaseId = RequestUtil.getParam(request, "databaseId");
		DatabaseBO database = manager.get(databaseId);
		request.setAttribute("database", database);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
