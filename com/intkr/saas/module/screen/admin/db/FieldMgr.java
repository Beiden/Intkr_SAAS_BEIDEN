package com.intkr.saas.module.screen.admin.db;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.manager.db.FieldManager;
import com.intkr.saas.module.action.db.FieldAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table field_tab
 * 
 * @author Beiden
 * @date 2020-04-02 21:01:47
 * @version 1.0
 */
public class FieldMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FieldManager manager = IOC.get(FieldManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		FieldBO query = FieldAction.getParameter(request);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		UserLogClient.log(request, "打开", "管理");
	}

}
