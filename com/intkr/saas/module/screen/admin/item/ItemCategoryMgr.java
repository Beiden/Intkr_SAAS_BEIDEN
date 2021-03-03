package com.intkr.saas.module.screen.admin.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.manager.item.ItemCategoryManager;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
import com.intkr.saas.manager.item.ItemSpuTemplateManager;
import com.intkr.saas.manager.item.ItemSpuTemplateValueManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class ItemCategoryMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCategoryManager itemCategoryManager = IOC.get("ItemCategoryManager");

	private ItemSpuTemplateManager itemSpuTemplateManager = IOC.get("ItemSpuTemplateManager");

	private ItemSpuTemplateValueManager itemSpuTemplateValueManager = IOC.get("ItemSpuTemplateValueManager");

	private ItemPropertyManager itemPropertyManager = IOC.get("ItemPropertyManager");

	private ItemPropertyValueManager itemPropertyValueManager = IOC.get("ItemPropertyValueManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<ItemCategoryBO> list = itemCategoryManager.getFirstCategoryFull(SessionClient.getSaasId(request));
		for (ItemCategoryBO firstCategory : list) {
			if (firstCategory.getLevel() == null || firstCategory.getLevel() != 1) {
				firstCategory.setLevel(1);
				itemCategoryManager.update(firstCategory);
			}
			if (firstCategory.getChilds() != null) {
				for (ItemCategoryBO secondCategory : firstCategory.getChilds()) {
					if (secondCategory.getLevel() == null || secondCategory.getLevel() != 2) {
						secondCategory.setLevel(2);
						itemCategoryManager.update(secondCategory);
					}
					if (secondCategory.getChilds() != null) {
						for (ItemCategoryBO thirdCategory : secondCategory.getChilds()) {
							if (thirdCategory.getLevel() == null || thirdCategory.getLevel() != 3) {
								thirdCategory.setLevel(3);
								itemCategoryManager.update(thirdCategory);
							}
						}
					}
				}
			}
		}
		fillProperty(list);
		request.setAttribute("list", list);
	}

	private void fillProperty(List<ItemCategoryBO> list) {
		itemSpuTemplateManager.fill(list);
		for (ItemCategoryBO bo : list) {
			if (bo.getSpuTemplateList() == null) {
				continue;
			}
			itemSpuTemplateValueManager.fill(bo.getSpuTemplateList());
			itemPropertyManager.fill(bo.getSpuTemplateList());
			for (ItemSpuTemplateBO spuTemplate : bo.getSpuTemplateList()) {
				itemPropertyValueManager.fill(spuTemplate.getSpuTemplateValueList());
			}
		}
		for (ItemCategoryBO bo : list) {
			if (bo.getChilds() != null && !bo.getChilds().isEmpty()) {
				fillProperty(bo.getChilds());
			}
		}
	}

}
