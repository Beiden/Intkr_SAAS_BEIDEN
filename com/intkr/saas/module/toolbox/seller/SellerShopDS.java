package com.intkr.saas.module.toolbox.seller;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.shop.ShopAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 */
public class SellerShopDS extends BaseToolBox {

	private ShopManager shopManager = IOC.get("ShopManager");

	public ShopBO getMyShop(HttpServletRequest request) {
		try {
			ShopBO query = ShopAction.getParameter(request);
			query.setUserId(SessionClient.getLoginUserId(request));
			ShopBO shop = shopManager.selectOne(query);
			return shop;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

}
