package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemSpuValueDAO;
import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.domain.bo.item.ItemSpuValueBO;
import com.intkr.saas.domain.dbo.item.ItemSpuValueDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
import com.intkr.saas.manager.item.ItemSpuValueManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:48
 * @version 1.0
 */
@Repository("ItemSpuValueManager")
public class ItemSpuValueManagerImpl extends BaseManagerImpl<ItemSpuValueBO, ItemSpuValueDO> implements ItemSpuValueManager {

	@Resource
	private ItemSpuValueDAO itemSpuValueDAO;

	@Resource
	private ItemPropertyValueManager itemPropertyValueManager;

	public BaseDAO<ItemSpuValueDO> getBaseDAO() {
		return itemSpuValueDAO;
	}

	public ItemSpuBO fill(ItemSpuBO item) {
		if (item == null) {
			return item;
		}
		ItemSpuValueBO query = new ItemSpuValueBO();
		query.set_pageSize(1000);
		query.setSpuId(item.getId());
		List<ItemSpuValueBO> list = select(query);
		for (ItemSpuValueBO spuValue : list) {
			spuValue.setPropertyValue(itemPropertyValueManager.get(spuValue.getValueId()));
		}
		item.setSpuValues(list);
		return item;
	}

	public ItemSpuBO fillFull(ItemSpuBO spu) {
		if (spu == null) {
			return spu;
		}
		ItemSpuValueBO query = new ItemSpuValueBO();
		query.set_pageSize(1000);
		query.setSpuId(spu.getId());
		List<ItemSpuValueBO> list = select(query);
		for (ItemSpuValueBO spuValue : list) {
			spuValue.setPropertyValue(itemPropertyValueManager.get(spuValue.getValueId()));
			spuValue.setSpu(spu);
		}
		spu.setSpuValues(list);
		return spu;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemSpuBO) {
				fill((ItemSpuBO) obj);
			}
		}
		return list;
	}

}
