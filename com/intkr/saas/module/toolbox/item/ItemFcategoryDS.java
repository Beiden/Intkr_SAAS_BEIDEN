package com.intkr.saas.module.toolbox.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.manager.item.ItemFcategoryManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class ItemFcategoryDS extends BaseToolBox {

	private ItemFcategoryManager manager = IOC.get("ItemFcategoryManager");

	private static List<ItemFcategoryBO> itemCategoryListCache;

	public List<ItemFcategoryBO> get(HttpServletRequest request) {
		return manager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
//		if (itemCategoryListCache == null) {
//			itemCategoryListCache = manager.getFirstCategoryFull();
//		}
//		return itemCategoryListCache;
	}

	public List<ItemFcategoryBO> refresh(HttpServletRequest request) {
		itemCategoryListCache = manager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		return itemCategoryListCache;
	}

}
