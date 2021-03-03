package com.intkr.saas.module.screen.admin.user.dialog;

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
 * @date 2011-11-22 下午3:56:04
 * @version 1.0
 */
public class MyDeliveryAddressAddOrUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryAddressManager deliveryAddressManager = IOC.get("DeliveryAddressManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		DeliveryAddressBO deliveryAddressBO = deliveryAddressManager.get(id);
		if (deliveryAddressBO != null) {
			request.setAttribute("deliveryAddressBO", deliveryAddressBO);
		} else {
			request.setAttribute("addId", deliveryAddressManager.getId());
		}
	}

}