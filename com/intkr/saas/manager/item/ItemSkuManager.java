package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.dbo.item.ItemSkuDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:42:29
 * @version 1.0
 */
public interface ItemSkuManager extends BaseManager<ItemSkuBO, ItemSkuDO> {

	public ItemBO fill(ItemBO item);

	public ItemBO fillFull(ItemBO item);

	public List<?> fill(List<?> list);

	public List<ItemSkuBO> getFullByItemId(Long itemId);

	public ItemSkuBO getFull(Long skuId);

	public boolean updateInventory(Long skuId, Integer inventory);

	public boolean increaseInventory(Long skuId, Integer count);

	public boolean decreaseInventory(Long skuId, Integer count);

}
