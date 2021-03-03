package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSpuTemplateDAO;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.dbo.item.ItemSpuTemplateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.manager.item.ItemSpuTemplateManager;
import com.intkr.saas.manager.item.ItemSpuTemplateValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:59
 * @version 1.0
 */
@Repository("ItemSpuTemplateManager")
public class ItemSpuTemplateManagerImpl extends BaseManagerImpl<ItemSpuTemplateBO, ItemSpuTemplateDO> implements ItemSpuTemplateManager {

	@Resource
	private ItemSpuTemplateDAO itemSpuTemplateDAO;

	@Resource
	private ItemPropertyManager itemPropertyManager;

	@Resource
	private ItemSpuTemplateValueManager itemSpuTemplateValueManager;

	public BaseDAO<ItemSpuTemplateDO> getBaseDAO() {
		return itemSpuTemplateDAO;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemCategoryBO) {
				fill((ItemCategoryBO) obj);
			}
		}
		return list;
	}

	public ItemCategoryBO fill(ItemCategoryBO category) {
		if (category == null || !IdEngine.isValidate(category.getId())) {
			return null;
		}
		ItemSpuTemplateBO query = new ItemSpuTemplateBO();
		query.setCategoryId(category.getId());
		query.set_pageSize(1000);
		List<ItemSpuTemplateBO> list = select(query);
		category.setSpuTemplateList(list);
		return category;
	}

	public ItemCategoryBO fillFull(ItemCategoryBO category) {
		if (category == null || !IdEngine.isValidate(category.getId())) {
			return null;
		}
		ItemSpuTemplateBO query = new ItemSpuTemplateBO();
		query.setCategoryId(category.getId());
		query.set_pageSize(1000);
		List<ItemSpuTemplateBO> list = select(query);
		for (ItemSpuTemplateBO bo : list) {
			itemPropertyManager.fill(bo);
			itemSpuTemplateValueManager.fillFull(bo);
		}
		category.setSpuTemplateList(list);
		return category;
	}

}
