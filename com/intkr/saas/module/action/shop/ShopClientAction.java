package com.intkr.saas.module.action.shop;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopClientBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ShopClientManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
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
public class ShopClientAction extends BaseAction<ShopClientBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopClientManager shopClientManager = IOC.get(ShopClientManager.class);

	public ShopClientBO getBO(HttpServletRequest request) {
		ShopClientBO shopClientBO = getParameter(request);
		return shopClientBO;
	}

	public static ShopClientBO getParameter(HttpServletRequest request) {
		ShopClientBO shopClientBO = new ShopClientBO();
		shopClientBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopClientBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopClientBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopClientBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopClientBO.setType(RequestUtil.getParam(request, "type", String.class));
		shopClientBO.setTags(RequestUtil.getParam(request, "tags", String.class));
		shopClientBO.setNote(RequestUtil.getParam(request, "note", String.class));
		shopClientBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, shopClientBO);
		return shopClientBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopClientManager;
	}

}
