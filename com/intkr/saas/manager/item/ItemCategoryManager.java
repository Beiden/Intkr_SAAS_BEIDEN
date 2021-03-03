package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.domain.bo.sms.ItemCouponActivityBO;
import com.intkr.saas.domain.dbo.item.ItemCategoryDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 18:10:41
 * @version 1.0
 */
public interface ItemCategoryManager extends BaseManager<ItemCategoryBO, ItemCategoryDO> {

	public List<ItemCategoryBO> getFirstCategoryFull(Long saasId);

	public <T> List<T> fill(List<T> list);

	public ItemBO fill(ItemBO article);
	
	public ItemCouponActivityBO fill(ItemCouponActivityBO activity);

	public ItemFcategoryBO fill(ItemFcategoryBO category);

}
