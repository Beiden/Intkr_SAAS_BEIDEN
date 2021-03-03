package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSpuDAO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.domain.dbo.item.ItemSpuDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.manager.item.ItemSpuManager;
import com.intkr.saas.manager.item.ItemSpuValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:40
 * @version 1.0
 */
@Repository("ItemSpuManager")
public class ItemSpuManagerImpl extends BaseManagerImpl<ItemSpuBO, ItemSpuDO> implements ItemSpuManager {

	@Resource
	private ItemSpuDAO itemSpuDAO;

	@Resource
	private ItemPropertyManager itemPropertyManager;

	@Resource
	private ItemSpuValueManager itemSpuValueManager;

	public BaseDAO<ItemSpuDO> getBaseDAO() {
		return itemSpuDAO;
	}

	public ItemBO fill(ItemBO item) {
		if (item == null) {
			return item;
		}
		ItemSpuBO query = new ItemSpuBO();
		query.set_pageSize(1000);
		query.setItemId(item.getId());
		List<ItemSpuBO> list = select(query);
		for (ItemSpuBO spu : list) {
			spu.setProperty(itemPropertyManager.get(spu.getPropertyId()));
		}
		item.setSpus(list);
		return item;
	}

	public ItemBO fillFull(ItemBO item) {
		if (item == null) {
			return item;
		}
		ItemSpuBO query = new ItemSpuBO();
		query.set_pageSize(1000);
		query.setItemId(item.getId());
		List<ItemSpuBO> list = select(query);
		for (ItemSpuBO spu : list) {
			spu.setProperty(itemPropertyManager.get(spu.getPropertyId()));
			spu.setItem(item);
			itemSpuValueManager.fillFull(spu);
		}
		item.setSpus(list);
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

	public List<ItemSpuBO> getFullByItemId(Long itemId) {
		ItemSpuBO query = new ItemSpuBO();
		query.set_pageSize(1000);
		query.setItemId(itemId);
		List<ItemSpuBO> list = select(query);
		for (ItemSpuBO spu : list) {
			spu.setProperty(itemPropertyManager.get(spu.getPropertyId()));
			itemSpuValueManager.fillFull(spu);
		}
		return list;
	}

}
