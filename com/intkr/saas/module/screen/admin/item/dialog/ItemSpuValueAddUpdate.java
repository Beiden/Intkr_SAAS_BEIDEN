package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemSpuValueBO;
import com.intkr.saas.manager.item.ItemSpuValueManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemSpuValueAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuValueManager itemSpuValueManager = IOC.get("ItemSpuValueManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopSpuValueId = RequestUtil.getParam(request, "shopSpuValueId");
		ItemSpuValueBO shopSpuValue = itemSpuValueManager.get(shopSpuValueId);
		request.setAttribute("shopSpuValue", shopSpuValue);
		request.setAttribute("addId", itemSpuValueManager.getId());
	}

}
