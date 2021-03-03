package com.intkr.saas.module.screen.admin.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.manager.item.ItemFcategoryManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 上午10:43:04
 * @version 1.0
 */
public class ItemFcategoryMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFcategoryManager itemCategoryManager = IOC.get("ItemFcategoryManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			ItemFcategoryBO categoryBO = itemCategoryManager.get(id);
			request.setAttribute("dto", categoryBO);
		} else {
			request.setAttribute("addId", itemCategoryManager.getId());
		}

		List<ItemFcategoryBO> categoryList = itemCategoryManager.getFirstCategoryFull(SessionClientDistImpl.getSaas(request).getId());
		request.setAttribute("categoryList", categoryList);

		request.setAttribute("parentId", RequestUtil.getParam(request, "parentId"));
	}
}
