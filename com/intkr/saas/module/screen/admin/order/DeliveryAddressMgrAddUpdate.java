package com.intkr.saas.module.screen.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2015-4-17 下午11:41:26
 * @version 1.0
 */
public class DeliveryAddressMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryAddressManager deliveryAddressManager = IOC.get("DeliveryAddressManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			DeliveryAddressBO deliveryAddressBO = deliveryAddressManager.get(id);
			request.setAttribute("dto", deliveryAddressBO);
		} else {
			request.setAttribute("addId", deliveryAddressManager.getId());
		}
	}

}
