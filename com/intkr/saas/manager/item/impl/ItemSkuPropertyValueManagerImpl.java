package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSkuPropertyValueDAO;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyBO;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyValueBO;
import com.intkr.saas.domain.dbo.item.ItemSkuPropertyValueDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
import com.intkr.saas.manager.item.ItemSkuPropertyValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:48
 * @version 1.0
 */
@Repository("ItemSkuPropertyValueManager")
public class ItemSkuPropertyValueManagerImpl extends BaseManagerImpl<ItemSkuPropertyValueBO, ItemSkuPropertyValueDO> implements ItemSkuPropertyValueManager {

	@Resource
	private ItemSkuPropertyValueDAO itemSkuPropertyValueDAO;

	@Resource
	private ItemPropertyValueManager itemPropertyValueManager;

	public BaseDAO<ItemSkuPropertyValueDO> getBaseDAO() {
		return itemSkuPropertyValueDAO;
	}

	public ItemSkuPropertyBO fill(ItemSkuPropertyBO item) {
		if (item == null) {
			return item;
		}
		ItemSkuPropertyValueBO query = new ItemSkuPropertyValueBO();
		query.set_pageSize(1000);
		query.setSkuPropertyId(item.getId());
		List<ItemSkuPropertyValueBO> list = select(query);
		for (ItemSkuPropertyValueBO spuValue : list) {
			spuValue.setPropertyValue(itemPropertyValueManager.get(spuValue.getValueId()));
		}
		item.setSkuPropertyValues(list);
		return item;
	}

	public ItemSkuPropertyBO fillFull(ItemSkuPropertyBO spu) {
		if (spu == null) {
			return spu;
		}
		ItemSkuPropertyValueBO query = new ItemSkuPropertyValueBO();
		query.set_pageSize(1000);
		query.setSkuPropertyId(spu.getId());
		List<ItemSkuPropertyValueBO> list = select(query);
		for (ItemSkuPropertyValueBO spuValue : list) {
			spuValue.setPropertyValue(itemPropertyValueManager.get(spuValue.getValueId()));
			spuValue.setSkuProperty(spu);
		}
		spu.setSkuPropertyValues(list);
		return spu;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemSkuPropertyBO) {
				fill((ItemSkuPropertyBO) obj);
			}
		}
		return list;
	}

}
