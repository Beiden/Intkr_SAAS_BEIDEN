package com.intkr.saas.module.screen.admin.shop.json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-10 上午8:35:34
 * @version 1.0
 */
public class Item {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemManager itemManager = IOC.get("ItemManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemId = RequestUtil.getParam(request, "itemId");
		ItemBO item = itemManager.get(itemId);
		request.setAttribute("item", item);
	}

}
