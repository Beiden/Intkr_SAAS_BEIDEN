package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.domain.dbo.item.ItemSpuDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:40
 * @version 1.0
 */
public interface ItemSpuManager extends BaseManager<ItemSpuBO, ItemSpuDO> {

	public ItemBO fill(ItemBO item);

	public ItemBO fillFull(ItemBO item);

	public List<ItemSpuBO> getFullByItemId(Long itemId);

	public List<?> fill(List<?> list);

}
