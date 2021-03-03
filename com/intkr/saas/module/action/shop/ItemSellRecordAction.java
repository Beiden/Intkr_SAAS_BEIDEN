package com.intkr.saas.module.action.shop;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ItemSellRecordBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ItemSellRecordManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午11:11:02
 * @version 1.0
 */
public class ItemSellRecordAction extends BaseAction<ItemSellRecordBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSellRecordManager itemSellRecordManager = IOC.get("ShopItemSellRecordManager");

	public ItemSellRecordBO getBO(HttpServletRequest request) {
		ItemSellRecordBO itemSellRecordBO = getParameter(request);
		return itemSellRecordBO;
	}

	public static ItemSellRecordBO getParameter(HttpServletRequest request) {
		ItemSellRecordBO itemSellRecordBO = new ItemSellRecordBO();
		itemSellRecordBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemSellRecordBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemSellRecordBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		itemSellRecordBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		itemSellRecordBO.setPrice(MoneyUtil.parse(RequestUtil.getParam(request, "price")));
		itemSellRecordBO.setCount(RequestUtil.getParam(request, "count", Integer.class));
		itemSellRecordBO.setBuyTime(DateUtil.parse(RequestUtil.getParam(request, "buyTime")));
		itemSellRecordBO.setOrderId(RequestUtil.getParam(request, "orderId", Long.class));
		itemSellRecordBO.setOrderDetailId(RequestUtil.getParam(request, "orderDetailId", Long.class));
		PagerUtil.initPage(request, itemSellRecordBO);
		return itemSellRecordBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSellRecordManager;
	}

}
