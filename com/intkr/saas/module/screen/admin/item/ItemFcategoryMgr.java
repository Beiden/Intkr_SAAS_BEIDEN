package com.intkr.saas.module.screen.admin.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.manager.item.ItemCategoryManager;
import com.intkr.saas.manager.item.ItemFcategoryManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 上午10:42:38
 * @version 1.0
 */
public class ItemFcategoryMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFcategoryManager fcategoryManager = IOC.get("ItemFcategoryManager");

	private ItemCategoryManager categoryManager = IOC.get("ItemCategoryManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long saasId = SessionClient.getSaasId(request);
		List<ItemFcategoryBO> fcategoryList = fcategoryManager.getFirstCategoryFull(saasId);
		List<ItemCategoryBO> categoryList = categoryManager.getFirstCategoryFull(saasId);
		fillCategory(fcategoryList, categoryList);
		request.setAttribute("list", fcategoryList);
	}

	// 遍历前台类型
	private void fillCategory(List<ItemFcategoryBO> fcategoryList, List<ItemCategoryBO> categoryList) {
		if (fcategoryList == null || fcategoryList.isEmpty()) {
			return;
		}
		for (ItemFcategoryBO fcategory : fcategoryList) {
			List<Long> ids = fcategory.getIbcIdsList();
			for (Long id : ids) {
				ItemCategoryBO category = find(id, categoryList);
				if (category != null) {
					fcategory.addIbc(category);
				}
			}
			fillCategory(fcategory.getChilds(), categoryList);
		}
	}

	// 在树中找到Category
	private ItemCategoryBO find(Long id, List<ItemCategoryBO> categoryList) {
		if (id == null) {
			return null;
		}
		if (categoryList == null || categoryList.isEmpty()) {
			return null;
		}
		for (ItemCategoryBO category : categoryList) {
			if (category.getId().equals(id)) {
				return category;
			}
			ItemCategoryBO find = find(id, category.getChilds());
			if (find != null) {
				return find;
			}
		}
		return null;
	}

}
