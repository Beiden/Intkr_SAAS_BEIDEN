package com.intkr.saas.dao.item;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.item.ItemSkuDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:43:52
 * @version 1.0
 */
public interface ItemSkuDAO extends BaseDAO<ItemSkuDO> {

	public boolean updateInventory(Long skuId, Integer inventory);

	public boolean increaseInventory(Long skuId, Integer count);

	public boolean decreaseInventory(Long skuId, Integer count);

}
