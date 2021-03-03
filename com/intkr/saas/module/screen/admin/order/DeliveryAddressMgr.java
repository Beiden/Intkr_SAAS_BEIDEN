package com.intkr.saas.module.screen.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.module.action.order.DeliveryAddressAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:08:12
 * @version 1.0
 */
public class DeliveryAddressMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryAddressManager deliveryAddressManager = IOC.get("DeliveryAddressManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DeliveryAddressBO query = DeliveryAddressAction.getParameter(request);
		query = deliveryAddressManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
