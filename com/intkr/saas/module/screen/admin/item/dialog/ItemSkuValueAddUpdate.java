package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemSkuValueBO;
import com.intkr.saas.manager.item.ItemSkuValueManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemSkuValueAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuValueManager itemSkuValueManager = IOC.get("ItemSkuValueManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopSkuValueId = RequestUtil.getParam(request, "shopSkuValueId");
		ItemSkuValueBO shopSkuValue = itemSkuValueManager.get(shopSkuValueId);
		request.setAttribute("shopSkuValue", shopSkuValue);
		request.setAttribute("addId", itemSkuValueManager.getId());
	}

}
