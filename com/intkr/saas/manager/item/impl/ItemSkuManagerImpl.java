package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSkuDAO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.dbo.item.ItemSkuDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.item.ItemSkuPropertyManager;
import com.intkr.saas.manager.item.ItemSkuValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:42:29
 * @version 1.0
 */
@Repository("ItemSkuManager")
public class ItemSkuManagerImpl extends BaseManagerImpl<ItemSkuBO, ItemSkuDO> implements ItemSkuManager {

	@Resource
	private ItemSkuDAO shopSkuDAO;

	@Resource
	private ItemSkuValueManager itemSkuValueManager;

	@Resource
	private ItemSkuPropertyManager itemSkuPropertyManager;

	public BaseDAO<ItemSkuDO> getBaseDAO() {
		return shopSkuDAO;
	}

	public ItemBO fill(ItemBO item) {
		if (item == null) {
			return item;
		}
		ItemSkuBO query = new ItemSkuBO();
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		query.set_pageSize(1000);
		query.setItemId(item.getId());
		List<ItemSkuBO> list = select(query);
		item.setSkus(list);
		return item;
	}

	public ItemBO fillFull(ItemBO item) {
		if (item == null) {
			return item;
		}
		itemSkuPropertyManager.fillFull(item);
		ItemSkuBO query = new ItemSkuBO();
		query.set_pageSize(1000);
		query.setItemId(item.getId());
		List<ItemSkuBO> list = select(query);
		for (ItemSkuBO sku : list) {
			sku.setItem(item);
			itemSkuValueManager.fillFull(sku);
		}
		item.setSkus(list);
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

	public List<ItemSkuBO> getFullByItemId(Long itemId) {
		ItemSkuBO query = new ItemSkuBO();
		query.set_pageSize(1000);
		query.setItemId(itemId);
		List<ItemSkuBO> list = select(query);
		for (ItemSkuBO sku : list) {
			itemSkuValueManager.fillFull(sku);
		}
		return list;
	}

	public ItemSkuBO getFull(Long skuId) {
		ItemSkuBO sku = get(skuId);
		itemSkuValueManager.fillFull(sku);
		return sku;
	}

	public boolean updateInventory(Long skuId, Integer inventory) {
		return shopSkuDAO.updateInventory(skuId, inventory);
	}

	public boolean increaseInventory(Long skuId, Integer count) {
		return shopSkuDAO.increaseInventory(skuId, count);
	}

	public boolean decreaseInventory(Long skuId, Integer count) {
		return shopSkuDAO.decreaseInventory(skuId, count);
	}

}
