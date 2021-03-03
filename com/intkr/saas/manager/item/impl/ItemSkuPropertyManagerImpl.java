package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSkuPropertyDAO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyBO;
import com.intkr.saas.domain.dbo.item.ItemSkuPropertyDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.manager.item.ItemSkuPropertyManager;
import com.intkr.saas.manager.item.ItemSkuPropertyValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:40
 * @version 1.0
 */
@Repository("ItemSkuPropertyManager")
public class ItemSkuPropertyManagerImpl extends BaseManagerImpl<ItemSkuPropertyBO, ItemSkuPropertyDO> implements ItemSkuPropertyManager {

	@Resource
	private ItemSkuPropertyDAO itemSkuPropertyDAO;

	@Resource
	private ItemPropertyManager itemPropertyManager;

	@Resource
	private ItemSkuPropertyValueManager itemSkuPropertyValueManager;

	public BaseDAO<ItemSkuPropertyDO> getBaseDAO() {
		return itemSkuPropertyDAO;
	}

	public ItemBO fill(ItemBO item) {
		if (item == null) {
			return item;
		}
		ItemSkuPropertyBO query = new ItemSkuPropertyBO();
		query.set_pageSize(1000);
		query.setItemId(item.getId());
		List<ItemSkuPropertyBO> list = select(query);
		for (ItemSkuPropertyBO spu : list) {
			spu.setProperty(itemPropertyManager.get(spu.getPropertyId()));
		}
		item.setSkuPropertys(list);
		return item;
	}

	public ItemBO fillFull(ItemBO item) {
		if (item == null) {
			return item;
		}
		ItemSkuPropertyBO query = new ItemSkuPropertyBO();
		query.set_pageSize(1000);
		query.setItemId(item.getId());
		List<ItemSkuPropertyBO> list = select(query);
		for (ItemSkuPropertyBO spu : list) {
			spu.setProperty(itemPropertyManager.get(spu.getPropertyId()));
			spu.setItem(item);
			itemSkuPropertyValueManager.fillFull(spu);
		}
		item.setSkuPropertys(list);
		return item;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemBO) {
				fill((ItemBO) obj);
			}
		}
		return list;
	}

	public List<ItemSkuPropertyBO> getFullByItemId(Long itemId) {
		ItemSkuPropertyBO query = new ItemSkuPropertyBO();
		query.set_pageSize(1000);
		query.setItemId(itemId);
		List<ItemSkuPropertyBO> list = select(query);
		for (ItemSkuPropertyBO spu : list) {
			spu.setProperty(itemPropertyManager.get(spu.getPropertyId()));
			itemSkuPropertyValueManager.fillFull(spu);
		}
		return list;
	}

}
