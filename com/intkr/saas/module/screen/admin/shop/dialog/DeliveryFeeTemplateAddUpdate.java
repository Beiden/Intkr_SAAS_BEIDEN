package com.intkr.saas.module.screen.admin.shop.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.DeliveryFeeTemplateBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.shop.DeliveryFeeTemplateManager;
import com.intkr.saas.util.RequestUtil;
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
public class DeliveryFeeTemplateAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryFeeTemplateManager manager = IOC.get(DeliveryFeeTemplateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String deliveryFeeTemplateId = RequestUtil.getParam(request, "deliveryFeeTemplateId");
		DeliveryFeeTemplateBO deliveryFeeTemplate = manager.get(deliveryFeeTemplateId);
		request.setAttribute("deliveryFeeTemplate", deliveryFeeTemplate);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "运费模版新增/编辑");
	}

}
