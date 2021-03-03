package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemPropertyValueBO;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemPropertyValueAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemPropertyValueManager itemPropertyValueManager = IOC.get("ItemPropertyValueManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopPropertyValueId = RequestUtil.getParam(request, "shopPropertyValueId");
		ItemPropertyValueBO shopPropertyValue = itemPropertyValueManager.get(shopPropertyValueId);
		request.setAttribute("shopPropertyValue", shopPropertyValue);
		request.setAttribute("addId", itemPropertyValueManager.getId());
	}

}
