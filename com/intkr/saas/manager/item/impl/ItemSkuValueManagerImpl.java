package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSkuValueDAO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.item.ItemSkuValueBO;
import com.intkr.saas.domain.dbo.item.ItemSkuValueDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
import com.intkr.saas.manager.item.ItemSkuValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:42:39
 * @version 1.0
 */
@Repository("ItemSkuValueManager")
public class ItemSkuValueManagerImpl extends BaseManagerImpl<ItemSkuValueBO, ItemSkuValueDO> implements ItemSkuValueManager {

	@Resource
	private ItemSkuValueDAO itemSkuValueDAO;

	@Resource
	private ItemPropertyValueManager itemPropertyValueManager;

	@Resource
	private ItemPropertyManager itemPropertyManager;

	public BaseDAO<ItemSkuValueDO> getBaseDAO() {
		return itemSkuValueDAO;
	}

	public ItemSkuBO fill(ItemSkuBO item) {
		if (item == null) {
			return item;
		}
		ItemSkuValueBO query = new ItemSkuValueBO();
		query.set_pageSize(1000);
		query.setSkuId(item.getId());
		List<ItemSkuValueBO> list = select(query);
		for (ItemSkuValueBO spuValue : list) {
			spuValue.setPropertyValue(itemPropertyValueManager.get(spuValue.getValueId()));
		}
		item.setSkuValues(list);
		return item;
	}

	public ItemSkuBO fillFull(ItemSkuBO sku) {
		if (sku == null) {
			return sku;
		}
		ItemSkuValueBO query = new ItemSkuValueBO();
		query.set_pageSize(1000);
		query.setSkuId(sku.getId());
		List<ItemSkuValueBO> list = select(query);
		for (ItemSkuValueBO skuValue : list) {
			skuValue.setPropertyValue(itemPropertyValueManager.get(skuValue.getValueId()));
			skuValue.setSku(sku);
			if (skuValue.getPropertyValue() != null) {
				skuValue.setProperty(itemPropertyManager.get(skuValue.getPropertyValue().getPropertyId()));
			}
		}
		sku.setSkuValues(list);
		return sku;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemSkuBO) {
				fill((ItemSkuBO) obj);
			}
		}
		return list;
	}

}
