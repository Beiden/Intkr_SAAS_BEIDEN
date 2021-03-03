package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemTagRelatedBO;
import com.intkr.saas.domain.dbo.item.ItemTagRelatedDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 商品标签
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:14
 * @version 1.0
 */
public interface ItemTagRelatedManager extends BaseManager<ItemTagRelatedBO, ItemTagRelatedDO> {

	public List<ItemTagRelatedBO> selectByItemId(Long itemId);

}
