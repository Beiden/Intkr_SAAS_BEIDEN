package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemPropertyValueDAO;
import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.domain.bo.item.ItemPropertyValueBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateValueBO;
import com.intkr.saas.domain.dbo.item.ItemPropertyValueDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 15:55:49
 * @version 1.0
 */
@Repository("ItemPropertyValueManager")
public class ItemPropertyValueManagerImpl extends BaseManagerImpl<ItemPropertyValueBO, ItemPropertyValueDO> implements ItemPropertyValueManager {

	@Resource
	private ItemPropertyValueDAO shopPropertyValueDAO;

	public BaseDAO<ItemPropertyValueDO> getBaseDAO() {
		return shopPropertyValueDAO;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemPropertyBO) {
				fill((ItemPropertyBO) obj);
			} else if (obj instanceof ItemSpuTemplateValueBO) {
				fill((ItemSpuTemplateValueBO) obj);
			}
		}
		return list;
	}

	public ItemPropertyBO fill(ItemPropertyBO property) {
		if (property == null || !IdEngine.isValidate(property.getId())) {
			return null;
		}
		ItemPropertyValueBO query = new ItemPropertyValueBO();
		query.setPropertyId(property.getId());
		query.set_pageSize(1000);
		List<ItemPropertyValueBO> list = select(query);
		property.setValues(list);
		return property;
	}

	public ItemSpuTemplateValueBO fill(ItemSpuTemplateValueBO spuTemplateValue) {
		if (spuTemplateValue == null || !IdEngine.isValidate(spuTemplateValue.getValueId())) {
			return null;
		}
		spuTemplateValue.setPropertyValue(get(spuTemplateValue.getValueId()));
		return spuTemplateValue;
	}

}
