package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemCategoryDAO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.domain.bo.sms.ItemCouponActivityBO;
import com.intkr.saas.domain.dbo.item.ItemCategoryDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemCategoryManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 18:10:41
 * @version 1.0
 */
@Repository("ItemCategoryManager")
public class ItemCategoryManagerImpl extends BaseManagerImpl<ItemCategoryBO, ItemCategoryDO> implements ItemCategoryManager {

	@Resource
	private ItemCategoryDAO itemCategoryDAO;

	public BaseDAO<ItemCategoryDO> getBaseDAO() {
		return itemCategoryDAO;
	}

	public List<ItemCategoryBO> getFirstCategoryFull(Long saasId) {
		ItemCategoryBO query = new ItemCategoryBO();
		query.setSaasId(saasId);
		query.set_pageSize(10000);
		List<ItemCategoryBO> categorys = select(query);
		List<ItemCategoryBO> firstCategorys = new ArrayList<ItemCategoryBO>();
		Map<Long, ItemCategoryBO> map = new HashMap<Long, ItemCategoryBO>();
		for (ItemCategoryBO bo : categorys) {
			map.put(bo.getId(), bo);
		}
		for (ItemCategoryBO bo : categorys) {
			if (bo.getPid() != null && map.containsKey(bo.getPid())) {
				ItemCategoryBO parent = map.get(bo.getPid());
				parent.addChild(bo);
				bo.setParent(parent);
			}
		}
		for (ItemCategoryBO bo : categorys) {
			if (bo.getParent() == null) {
				firstCategorys.add(bo);
			}
		}
		return firstCategorys;
	}

	public ItemBO fill(ItemBO item) {
		if (item == null) {
			return item;
		}
		if (item.getFirstCategoryId() != null) {
			item.setFirstCategory(get(item.getFirstCategoryId()));
		}
		if (item.getSecondCategoryId() != null) {
			item.setSecondCategory(get(item.getSecondCategoryId()));
		}
		if (item.getThirdCategoryId() != null) {
			item.setThirdCategory(get(item.getThirdCategoryId()));
		}
		return item;
	}

	public ItemCouponActivityBO fill(ItemCouponActivityBO activity) {
		if (activity == null) {
			return activity;
		}
		List<String> categoryIds = (List<String>) activity.getFeature("categoryIds");
		if (categoryIds == null || categoryIds.isEmpty()) {
			return activity;
		}
		{
			ItemCategoryBO query = new ItemCategoryBO();
			query.setQuery("ids", categoryIds);
			query.set_pageSize(categoryIds.size());
			activity.setCategorys(select(query));
		}
		{
			List<Long> parentIds = new ArrayList<Long>();
			for (ItemCategoryBO category : activity.getCategorys()) {
				if (IdEngine.isValidate(category.getPid())) {
					parentIds.add(category.getPid());
				}
			}
			if (parentIds.isEmpty()) {
				return activity;
			}
			ItemCategoryBO query = new ItemCategoryBO();
			query.setQuery("ids", parentIds);
			query.set_pageSize(parentIds.size());
			List<ItemCategoryBO> parentList = select(query);
			for (ItemCategoryBO parent : parentList) {
				for (ItemCategoryBO category : activity.getCategorys()) {
					if (parent.getId().equals(category.getPid())) {
						parent.addChild(category);
						category.setParent(parent);
					}
				}
			}
			{
				List<Long> pparentIds = new ArrayList<Long>();
				for (ItemCategoryBO category : parentList) {
					if (IdEngine.isValidate(category.getPid())) {
						pparentIds.add(category.getPid());
					}
				}
				if (pparentIds.isEmpty()) {
					return activity;
				}
				ItemCategoryBO pparentIdsQuery = new ItemCategoryBO();
				pparentIdsQuery.setQuery("ids", pparentIds);
				pparentIdsQuery.set_pageSize(pparentIds.size());
				List<ItemCategoryBO> pparentList = select(pparentIdsQuery);
				for (ItemCategoryBO pparent : pparentList) {
					for (ItemCategoryBO parent : parentList) {
						if (pparent.getId().equals(parent.getPid())) {
							pparent.addChild(parent);
							parent.setParent(pparent);
						}
					}
				}
			}
		}
		return activity;
	}

	public ItemFcategoryBO fill(ItemFcategoryBO itemCategory) {
		if (itemCategory == null) {
			return itemCategory;
		}
		if (itemCategory.getChilds() != null) {
			for (ItemFcategoryBO ic : itemCategory.getChilds()) {
				fill(ic);
			}
		}
		if (itemCategory.getIbcIds() == null || "".equals(itemCategory.getIbcIds())) {
			return itemCategory;
		}
		String[] ids = itemCategory.getIbcIds().split(";");
		itemCategory.setIbcs(new ArrayList<ItemCategoryBO>());
		for (String id : ids) {
			ItemCategoryBO category = get(id);
			if (category != null) {
				itemCategory.getIbcs().add(category);
				fillParent(category);
			}
		}
		return itemCategory;
	}

	public ItemCategoryBO fillParent(ItemCategoryBO category) {
		if (category == null || category.getPid() == null || category.getPid() <= 0) {
			return category;
		}
		ItemCategoryBO parent = get(category.getPid());
		if (parent == null) {
			return category;
		}
		parent.addChild(category);
		category.setParent(parent);
		return fillParent(parent);
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemBO) {
				fill((ItemBO) obj);
			} else if (obj instanceof ItemFcategoryBO) {
				fill((ItemFcategoryBO) obj);
			} else if (obj instanceof ItemCouponActivityBO) {
				fill((ItemCouponActivityBO) obj);
			}
		}
		return list;
	}

}
