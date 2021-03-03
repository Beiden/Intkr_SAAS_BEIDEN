package com.intkr.saas.module.toolbox.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.type.item.ItemStatus;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.shop.ShopAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class ShopDS extends BaseToolBox {

	private ShopManager shopManager = IOC.get("ShopManager");

	public ShopBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ShopBO query = ShopAction.getParameter(request);
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			query.setStatus(ItemStatus.Approve.getCode());
			shopManager.selectAndCount(query);
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public ShopBO getById(Object shopIdObject) {
		Long articleId = null;
		if (shopIdObject instanceof String) {
			articleId = Long.valueOf((String) shopIdObject);
		} else {
			articleId = (Long) shopIdObject;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ShopBO shop = shopManager.get(articleId);
		if (shop == null) {
			return null;
		}
		return shop;
	}

}
