package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemTagBO;
import com.intkr.saas.domain.dbo.item.ItemTagDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 商品标签
 * 
 * @table item_tag
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:32
 * @version 1.0
 */
public interface ItemTagManager extends BaseManager<ItemTagBO, ItemTagDO> {

	public List<?> fill(List<?> list);

	public List<?> fills(List<?> list);

	public ItemBO fill(ItemBO item);

	public ItemBO fills(ItemBO item);

	public ItemTagBO getByName(Long saasId, String name);

}
