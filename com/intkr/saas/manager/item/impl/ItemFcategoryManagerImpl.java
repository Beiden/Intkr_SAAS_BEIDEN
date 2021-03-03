package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemFcategoryDAO;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.domain.dbo.item.ItemFcategoryDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemFcategoryManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 15:12:16
 * @version 1.0
 */
@Repository("ItemFcategoryManager")
public class ItemFcategoryManagerImpl extends BaseManagerImpl<ItemFcategoryBO, ItemFcategoryDO> implements ItemFcategoryManager {

	@Resource
	private ItemFcategoryDAO shopItemCategoryDAO;

	public BaseDAO<ItemFcategoryDO> getBaseDAO() {
		return shopItemCategoryDAO;
	}

	public List<ItemFcategoryBO> getFirstCategoryFull(Long saasId) {
		ItemFcategoryBO query = new ItemFcategoryBO();
		query.setSaasId(saasId);
		query.set_pageSize(100000);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "asc");
		List<ItemFcategoryBO> categorys = select(query);
		Map<Long, ItemFcategoryBO> map = new HashMap<Long, ItemFcategoryBO>();
		for (ItemFcategoryBO bo : categorys) {
			map.put(bo.getId(), bo);
		}
		for (ItemFcategoryBO bo : categorys) {
			if (bo.getPid() != null && map.containsKey(bo.getPid())) {
				ItemFcategoryBO parent = map.get(bo.getPid());
				parent.addChild(bo);
				bo.setParent(parent);
			}
		}
		List<ItemFcategoryBO> firstCategorys = new ArrayList<ItemFcategoryBO>();
		for (ItemFcategoryBO bo : categorys) {
			if (bo.getParent() == null) {
				firstCategorys.add(bo);
			}
		}
		return firstCategorys;
	}

	public ItemFcategoryBO fillParent(ItemFcategoryBO itemCategory) {
		if (itemCategory == null || itemCategory.getPid() == null) {
			return itemCategory;
		}
		itemCategory.setParent(get(itemCategory.getPid()));
		fillParent(itemCategory.getParent());
		return itemCategory;
	}

	public ItemFcategoryBO fillChild(ItemFcategoryBO itemCategory) {
		if (itemCategory == null || itemCategory.getPid() == null) {
			return itemCategory;
		}
		ItemFcategoryBO query = new ItemFcategoryBO();
		query.set_pageSize(10000);
		query.setPid(itemCategory.getId());
		itemCategory.setChilds(select(query));
		fillParent(itemCategory.getParent());
		return itemCategory;
	}

	public ItemFcategoryBO fillSbiling(ItemFcategoryBO itemCategory) {
		if (itemCategory == null || itemCategory.getPid() == null) {
			return itemCategory;
		}
		if (itemCategory.getPid() == null) {
			return itemCategory;
		}
		ItemFcategoryBO query = new ItemFcategoryBO();
		query.set_pageSize(10000);
		query.setPid(itemCategory.getPid());
		itemCategory.setSbilings(select(query));
		fillParent(itemCategory.getParent());
		return itemCategory;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (Object obj : list) {

		}
		return list;
	}

}
