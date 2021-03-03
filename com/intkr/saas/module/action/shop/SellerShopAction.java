package com.intkr.saas.module.action.shop;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-10 下午8:41:28
 * @version 1.0
 */
public class SellerShopAction extends BaseAction<ShopBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopManager shopManager = IOC.get("ShopManager");

	public ShopBO getBO(HttpServletRequest request) {
		ShopBO favoriteBO = ShopAction.getParameter(request);
		return favoriteBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopManager;
	}
}
