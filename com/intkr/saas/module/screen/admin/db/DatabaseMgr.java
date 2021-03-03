package com.intkr.saas.module.screen.admin.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.DatabaseBO;
import com.intkr.saas.manager.db.DatabaseManager;
import com.intkr.saas.module.action.db.DatabaseAction;
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
public class DatabaseMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DatabaseManager manager = IOC.get(DatabaseManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DatabaseBO query = DatabaseAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

}
