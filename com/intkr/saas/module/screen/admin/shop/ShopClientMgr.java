package com.intkr.saas.module.screen.admin.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.shop.ShopClientAction;
import com.intkr.saas.domain.bo.shop.ShopClientBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.shop.ShopClientManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.util.claz.IOC;

/**
 * 店铺客户
 * 
 * @table shop_client
 * 
 * @author Beiden
 * @date 2020-11-02 10:01:02
 * @version 1.0
 */
public class ShopClientMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopClientManager manager = IOC.get(ShopClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopClientBO query = ShopClientAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
