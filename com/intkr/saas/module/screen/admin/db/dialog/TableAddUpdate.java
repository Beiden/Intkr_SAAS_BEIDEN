package com.intkr.saas.module.screen.admin.db.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.manager.db.TableManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
public class TableAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TableManager manager = IOC.get(TableManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String tableId = RequestUtil.getParam(request, "tableId");
		TableBO table = manager.get(tableId);
		request.setAttribute("table", table);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
