package com.intkr.saas.module.toolbox.order;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:59:08
 * @version 1.0
 */
public class DeliveryAddressDS extends BaseToolBox {

	private DeliveryAddressManager deliveryAddressManager = IOC.get("DeliveryAddressManager");

	public DeliveryAddressBO select(HttpServletRequest request) {
		try {
			DeliveryAddressBO query = new DeliveryAddressBO();
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			deliveryAddressManager.selectAndCount(query);
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public DeliveryAddressBO getById(Object idOBj) {
		Long id = null;
		if (idOBj instanceof String) {
			id = Long.valueOf((String) idOBj);
		} else if (idOBj instanceof Long) {
			id = (Long) idOBj;
		}
		DeliveryAddressBO deliveryAddressBO = deliveryAddressManager.get(id);
		return deliveryAddressBO;
	}

}
