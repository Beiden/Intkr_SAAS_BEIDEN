package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.domain.bo.item.ItemSpuValueBO;
import com.intkr.saas.domain.dbo.item.ItemSpuValueDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:48
 * @version 1.0
 */
public interface ItemSpuValueManager extends BaseManager<ItemSpuValueBO, ItemSpuValueDO> {

	public ItemSpuBO fill(ItemSpuBO spu);

	public ItemSpuBO fillFull(ItemSpuBO spu);

	public List<?> fill(List<?> list);

}
