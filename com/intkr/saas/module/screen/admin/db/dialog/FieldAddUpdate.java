package com.intkr.saas.module.screen.admin.db.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.manager.db.FieldManager;
import com.intkr.saas.util.RequestUtil;
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
public class FieldAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FieldManager manager = IOC.get(FieldManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String fieldId = RequestUtil.getParam(request, "fieldId");
		FieldBO field = manager.get(fieldId);
		request.setAttribute("field", field);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
