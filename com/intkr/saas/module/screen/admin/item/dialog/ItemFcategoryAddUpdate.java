package com.intkr.saas.module.screen.admin.item.dialog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.manager.item.ItemFcategoryManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemFcategoryAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFcategoryManager itemCategoryManager = IOC.get("ItemFcategoryManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<ItemFcategoryBO> categoryList = itemCategoryManager.getFirstCategoryFull(SessionClientDistImpl.getSaas(request).getId());
		request.setAttribute("categoryList", categoryList);

		String itemCategoryId = RequestUtil.getParam(request, "itemCategoryId");
		ItemFcategoryBO shopItemCategory = itemCategoryManager.get(itemCategoryId);
		request.setAttribute("shopItemCategory", shopItemCategory);
		request.setAttribute("addId", itemCategoryManager.getId());
	}

}
