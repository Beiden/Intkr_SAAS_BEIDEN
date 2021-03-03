package com.intkr.saas.module.screen.admin.shop.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-10 上午8:35:57
 * @version 1.0
 */
public class ItemJson {

	private ItemManager itemManager = IOC.get("ItemManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemBO query = new ItemBO();
		Long itemId = RequestUtil.getParam(request, "itemId", Long.class);
		query.setId(itemId);
		List<ItemBO> list = itemManager.select(query);
		request.setAttribute("list", list);
	}

}
