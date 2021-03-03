package com.intkr.saas.module.screen.admin.shop.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopClientBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.shop.ShopClientManager;
import com.intkr.saas.util.RequestUtil;
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
public class ShopClientAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopClientManager manager = IOC.get(ShopClientManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopClientId = RequestUtil.getParam(request, "shopClientId");
		ShopClientBO shopClient = manager.get(shopClientId);
		request.setAttribute("shopClient", shopClient);
		request.setAttribute("addId", manager.getId());
	}

}
