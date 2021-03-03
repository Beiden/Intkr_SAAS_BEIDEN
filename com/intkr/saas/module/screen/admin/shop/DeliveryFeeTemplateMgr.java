package com.intkr.saas.module.screen.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.shop.DeliveryFeeTemplateBO;
import com.intkr.saas.domain.type.shop.ShippingDeliveryType;
import com.intkr.saas.domain.type.shop.ShippingFreeShipping;
import com.intkr.saas.domain.type.shop.ShippingValuateType;
import com.intkr.saas.manager.shop.DeliveryFeeTemplateManager;
import com.intkr.saas.module.action.shop.DeliveryFeeTemplateAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 运费模版
 * 
 * @table delivery_fee_template
 * 
 * @author Beiden
 * @date 2021-01-18 18:32:16
 * @version 1.0
 */
public class DeliveryFeeTemplateMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryFeeTemplateManager manager = IOC.get(DeliveryFeeTemplateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DeliveryFeeTemplateBO query = DeliveryFeeTemplateAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("ShippingDeliveryType", ShippingDeliveryType.Error);
		request.setAttribute("ShippingValuateType", ShippingValuateType.Error);
		request.setAttribute("ShippingFreeShipping", ShippingFreeShipping.Error);
		UserLogClient.log(request, "打开", "运费模版管理");
	}

}
