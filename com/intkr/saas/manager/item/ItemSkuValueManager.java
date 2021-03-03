package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.item.ItemSkuValueBO;
import com.intkr.saas.domain.dbo.item.ItemSkuValueDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:42:39
 * @version 1.0
 */
public interface ItemSkuValueManager extends BaseManager<ItemSkuValueBO, ItemSkuValueDO> {

	public ItemSkuBO fill(ItemSkuBO sku);

	public ItemSkuBO fillFull(ItemSkuBO sku);

	public List<?> fill(List<?> list);

}
