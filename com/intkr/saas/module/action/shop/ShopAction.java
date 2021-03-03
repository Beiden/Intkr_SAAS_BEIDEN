package com.intkr.saas.module.action.shop;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-22 上午10:15:32
 * @version 1.0
 */
public class ShopAction extends BaseAction<ShopBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopManager shopManager = IOC.get("ShopManager");

	public ShopBO getBO(HttpServletRequest request) {
		ShopBO shopBO = getParameter(request);
		return shopBO;
	}

	public static ShopBO getParameter(HttpServletRequest request) {
		ShopBO shopBO = new ShopBO();
		shopBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopBO.setName(RequestUtil.getParam(request, "name"));
		shopBO.setStatus(RequestUtil.getParam(request, "status"));
		shopBO.setFeature(RequestUtil.getParam(request, "feature"));
		shopBO.setComprehensiveGrade(RequestUtil.getParam(request, "comprehensiveGrade", Double.class));
		shopBO.setDescribeMatchGrade(RequestUtil.getParam(request, "describeMatchGrade", Double.class));
		shopBO.setServeAttitudeGrade(RequestUtil.getParam(request, "serveAttitudeGrade", Double.class));
		shopBO.setDeliverySpeedGrade(RequestUtil.getParam(request, "deliverySpeedGrade", Double.class));
		shopBO.setOpenTime(DateUtil.parse(RequestUtil.getParam(request, "openTime")));
		PagerUtil.initPage(request, shopBO);
		return shopBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopManager;
	}

}
