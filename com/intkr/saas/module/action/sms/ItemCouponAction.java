package com.intkr.saas.module.action.sms;

import java.util.Date;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemCouponBO;
import com.intkr.saas.manager.sms.ItemCouponManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 优惠券
 * 
 * @table item_coupon
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:09
 * @version 1.0
 */
public class ItemCouponAction extends BaseAction<ItemCouponBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCouponManager itemCouponManager = IOC.get(ItemCouponManager.class);

	public ItemCouponBO getBO(HttpServletRequest request) {
		ItemCouponBO itemCouponBO = getParameter(request);
		return itemCouponBO;
	}

	public static ItemCouponBO getParameter(HttpServletRequest request) {
		ItemCouponBO itemCouponBO = new ItemCouponBO();
		itemCouponBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemCouponBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemCouponBO.setActivityId(RequestUtil.getParam(request, "activityId", Long.class));
		itemCouponBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemCouponBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		itemCouponBO.setReceiveMethod(RequestUtil.getParam(request, "receiveMethod", String.class));
		itemCouponBO.setReceiveTime(RequestUtil.getParam(request, "receiveTime", Date.class));
		itemCouponBO.setUseTime(RequestUtil.getParam(request, "useTime", Date.class));
		itemCouponBO.setOrderId(RequestUtil.getParam(request, "orderId", Long.class));
		itemCouponBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemCouponBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemCouponBO);
		return itemCouponBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemCouponManager;
	}

}
