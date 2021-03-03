package com.intkr.saas.module.screen.admin.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemSkuValueBO;
import com.intkr.saas.manager.item.ItemSkuValueManager;
import com.intkr.saas.module.action.item.SkuValueAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ItemSkuValueMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuValueManager itemSkuValueManager = IOC.get("ItemSkuValueManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemSkuValueBO query = SkuValueAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = itemSkuValueManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
