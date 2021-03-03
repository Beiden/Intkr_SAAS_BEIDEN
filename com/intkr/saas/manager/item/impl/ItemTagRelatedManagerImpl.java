package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemTagRelatedDAO;
import com.intkr.saas.domain.bo.item.ItemTagRelatedBO;
import com.intkr.saas.domain.dbo.item.ItemTagRelatedDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemTagRelatedManager;

/**
 * 商品标签
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:14
 * @version 1.0
 */
@Repository("ItemTagRelatedManager")
public class ItemTagRelatedManagerImpl extends BaseManagerImpl<ItemTagRelatedBO, ItemTagRelatedDO> implements ItemTagRelatedManager {

	@Resource
	private ItemTagRelatedDAO itemTagRelatedDAO;

	public BaseDAO<ItemTagRelatedDO> getBaseDAO() {
		return itemTagRelatedDAO;
	}

	public List<ItemTagRelatedBO> selectByItemId(Long itemId) {
		if (itemId == null || itemId == 0l) {
			return new ArrayList<ItemTagRelatedBO>();
		}
		ItemTagRelatedBO query = new ItemTagRelatedBO();
		query.setRelatedId(itemId);
		query.set_pageSize(1000);
		return select(query);
	}
}
