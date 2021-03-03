package com.intkr.saas.module.action.shop;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopApplyBO;
import com.intkr.saas.manager.shop.ShopApplyManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 店铺申请
 * 
 * @table shop_apply
 * 
 * @author Beiden
 * @date 2020-11-10 22:09:23
 * @version 1.0
 */
public class ShopApplyAction extends BaseAction<ShopApplyBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopApplyManager shopApplyManager = IOC.get(ShopApplyManager.class);

	public ShopApplyBO getBO(HttpServletRequest request) {
		ShopApplyBO shopApplyBO = getParameter(request);
		return shopApplyBO;
	}

	public static ShopApplyBO getParameter(HttpServletRequest request) {
		ShopApplyBO shopApplyBO = new ShopApplyBO();
		shopApplyBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopApplyBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopApplyBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopApplyBO.setShopType(RequestUtil.getParam(request, "shopType", String.class));
		shopApplyBO.setShopName(RequestUtil.getParam(request, "shopName", String.class));
		shopApplyBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		shopApplyBO.setNote(RequestUtil.getParam(request, "note", String.class));
		shopApplyBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, shopApplyBO);
		return shopApplyBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopApplyManager;
	}

}
