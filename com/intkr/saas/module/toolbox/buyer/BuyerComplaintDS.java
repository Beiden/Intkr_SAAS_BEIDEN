package com.intkr.saas.module.toolbox.buyer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ShopComplaintBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.order.OrderDetailManager;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.shop.ShopComplaintManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.shop.ShopComplaintAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class BuyerComplaintDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get("ItemManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderDetailManager orderDetailManager = IOC.get("OrderDetailManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private ShopComplaintManager shopComplaintManager = IOC.get("ShopComplaintManager");

	/**
	 * 查询列表
	 */
	public ShopComplaintBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ShopComplaintBO query = ShopComplaintAction.getParameter(request);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			shopComplaintManager.selectAndCount(query);
			shopManager.fill(query.getDatas());
			itemManager.fill(query.getDatas());
			orderManager.fill(query.getDatas());
			orderDetailManager.fill(query.getDatas());
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public ShopComplaintBO getById(Object complaintIdObject) {
		Long articleId = null;
		if (complaintIdObject instanceof String) {
			articleId = Long.valueOf((String) complaintIdObject);
		} else {
			articleId = (Long) complaintIdObject;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ShopComplaintBO item = shopComplaintManager.get(articleId);
		if (item == null) {
			return null;
		}
		return item;
	}

	public ShopComplaintBO getFull(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		} else {
			articleId = (Long) articleId;
		}
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ShopComplaintBO item = shopComplaintManager.get(articleId);
		return item;
	}

}
