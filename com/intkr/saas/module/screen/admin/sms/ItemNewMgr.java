package com.intkr.saas.module.screen.admin.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.sms.ItemNewAction;
import com.intkr.saas.domain.bo.sms.ItemNewBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemNewManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 新品推荐
 * 
 * @table item_new
 * 
 * @author Beiden
 * @date 2020-11-11 23:11:23
 * @version 1.0
 */
public class ItemNewMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemNewManager manager = IOC.get(ItemNewManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemNewBO query = ItemNewAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
