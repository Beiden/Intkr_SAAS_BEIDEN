package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemSkuPropertyBO;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyValueBO;
import com.intkr.saas.domain.dbo.item.ItemSkuPropertyValueDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:48
 * @version 1.0
 */
public interface ItemSkuPropertyValueManager extends BaseManager<ItemSkuPropertyValueBO, ItemSkuPropertyValueDO> {

	public ItemSkuPropertyBO fill(ItemSkuPropertyBO spu);

	public ItemSkuPropertyBO fillFull(ItemSkuPropertyBO spu);

	public List<?> fill(List<?> list);

}
