package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemTagDAO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemTagBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.dbo.item.ItemTagDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemTagManager;

/**
 * 商品标签
 * 
 * @table item_tag
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:32
 * @version 1.0
 */
@Repository("ItemTagManager")
public class ItemTagManagerImpl extends BaseManagerImpl<ItemTagBO, ItemTagDO> implements ItemTagManager {

	@Resource
	private ItemTagDAO itemTagDAO;

	public BaseDAO<ItemTagDO> getBaseDAO() {
		return itemTagDAO;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemBO) {
				fill((ItemBO) obj);
			}
		}
		return list;
	}

	public List<?> fills(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemBO) {
				fills((ItemBO) obj);
			}
		}
		return list;
	}

	public ItemBO fill(ItemBO item) {
		if (item == null //
				|| item.getTagIds() == null //
				|| "".equals(item.getTagIds()) //
				|| ";".equals(item.getTagIds())) {
			return item;
		}
		String[] tagIds = item.getTagIds().split(";");
		List<ItemTagBO> tags = new ArrayList<ItemTagBO>();
		for (String tagId : tagIds) {
			ItemTagBO bo = get(tagId);
			if (bo != null) {
				tags.add(bo);
				break;
			}
		}
		item.setTags(tags);
		return item;
	}

	public ItemBO fills(ItemBO item) {
		if (item == null || item.getTagIds() == null || "".equals(item.getTagIds())) {
			return item;
		}
		String[] tagIds = item.getTagIds().split(";");
		List<ItemTagBO> tags = new ArrayList<ItemTagBO>();
		for (String tagId : tagIds) {
			ItemTagBO bo = get(tagId);
			if (bo != null) {
				tags.add(bo);
			}
		}
		item.setTags(tags);
		return item;
	}

	public ItemTagBO getByName(Long saasId, String name) {
		if (name == null || "".equals(name)) {
			return null;
		}
		ItemTagBO query = new ItemTagBO();
		query.setSaasId(saasId);
		query.setName(name);
		return selectOne(query);
	}

}
