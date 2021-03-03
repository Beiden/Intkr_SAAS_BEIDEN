package com.intkr.saas.module.screen.admin.shop.json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.manager.item.ItemFcategoryManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-22 上午12:26:52
 * @version 1.0
 */
public class ItemFcategory {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFcategoryManager itemCategoryManager = IOC.get("ItemFcategoryManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String categoryId = RequestUtil.getParam(request, "categoryId");
		ItemFcategoryBO category = itemCategoryManager.get(categoryId);
		request.setAttribute("category", category);
	}

}
