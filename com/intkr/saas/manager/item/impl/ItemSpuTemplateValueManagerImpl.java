package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSpuTemplateValueDAO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateValueBO;
import com.intkr.saas.domain.dbo.item.ItemSpuTemplateValueDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
import com.intkr.saas.manager.item.ItemSpuTemplateValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:25:07
 * @version 1.0
 */
@Repository("ItemSpuTemplateValueManager")
public class ItemSpuTemplateValueManagerImpl extends BaseManagerImpl<ItemSpuTemplateValueBO, ItemSpuTemplateValueDO> implements ItemSpuTemplateValueManager {

	@Resource
	private ItemSpuTemplateValueDAO itemSpuTemplateValueDAO;

	@Resource
	private ItemPropertyValueManager itemPropertyValueManager;

	public BaseDAO<ItemSpuTemplateValueDO> getBaseDAO() {
		return itemSpuTemplateValueDAO;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemSpuTemplateBO) {
				fill((ItemSpuTemplateBO) obj);
			}
		}
		return list;
	}

	public ItemSpuTemplateBO fill(ItemSpuTemplateBO category) {
		if (category == null || !IdEngine.isValidate(category.getId())) {
			return null;
		}
		ItemSpuTemplateValueBO query = new ItemSpuTemplateValueBO();
		query.setSpuTemplateId(category.getId());
		query.set_pageSize(1000);
		List<ItemSpuTemplateValueBO> list = select(query);
		category.setSpuTemplateValueList(list);
		return category;
	}

	public ItemSpuTemplateBO fillFull(ItemSpuTemplateBO category) {
		if (category == null || !IdEngine.isValidate(category.getId())) {
			return null;
		}
		ItemSpuTemplateValueBO query = new ItemSpuTemplateValueBO();
		query.setSpuTemplateId(category.getId());
		query.set_pageSize(1000);
		List<ItemSpuTemplateValueBO> list = select(query);
		for (ItemSpuTemplateValueBO bo : list) {
			itemPropertyValueManager.fill(bo);
		}
		category.setSpuTemplateValueList(list);
		return category;
	}

}
