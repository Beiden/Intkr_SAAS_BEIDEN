package com.intkr.saas.module.screen.admin.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
import com.intkr.saas.module.action.item.ItemPropertyAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ItemPropertyMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemPropertyManager itemPropertyManager = IOC.get("ItemPropertyManager");

	private ItemPropertyValueManager itemPropertyValueManager = IOC.get("ItemPropertyValueManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemPropertyBO query = ItemPropertyAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = itemPropertyManager.selectAndCount(query);
		query.setDatas(itemPropertyValueManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
