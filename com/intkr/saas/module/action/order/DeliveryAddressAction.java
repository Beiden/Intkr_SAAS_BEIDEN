package com.intkr.saas.module.action.order;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:04:28
 * @version 1.0
 */
public class DeliveryAddressAction extends BaseAction<DeliveryAddressBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryAddressManager deliveryAddressManager = IOC.get("DeliveryAddressManager");

	public DeliveryAddressBO getBO(HttpServletRequest request) {
		DeliveryAddressBO deliveryAddressBO = getParameter(request);
		return deliveryAddressBO;
	}

	public static DeliveryAddressBO getParameter(HttpServletRequest request) {
		DeliveryAddressBO deliveryAddressBO = new DeliveryAddressBO();
		deliveryAddressBO.setId(RequestUtil.getParam(request, "id", Long.class));
		deliveryAddressBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		deliveryAddressBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		deliveryAddressBO.setProvince(RequestUtil.getParam(request, "province"));
		deliveryAddressBO.setCity(RequestUtil.getParam(request, "city"));
		deliveryAddressBO.setArea(RequestUtil.getParam(request, "area"));
		deliveryAddressBO.setDetail(RequestUtil.getParam(request, "detail"));
		deliveryAddressBO.setPostalCode(RequestUtil.getParam(request, "postalCode"));
		deliveryAddressBO.setConsignee(RequestUtil.getParam(request, "consignee"));
		deliveryAddressBO.setTel(RequestUtil.getParam(request, "tel"));
		deliveryAddressBO.setIsDefault(RequestUtil.getParam(request, "isDefault", Integer.class));
		PagerUtil.initPage(request, deliveryAddressBO);
		return deliveryAddressBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return deliveryAddressManager;
	}

}
