package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.domain.dbo.item.ItemFcategoryDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 15:12:16
 * @version 1.0
 */
public interface ItemFcategoryManager extends BaseManager<ItemFcategoryBO, ItemFcategoryDO> {

	public List<ItemFcategoryBO> getFirstCategoryFull(Long saasId);

	public <T> List<T> fill(List<T> list);

	public ItemFcategoryBO fillParent(ItemFcategoryBO itemCategory);

	public ItemFcategoryBO fillChild(ItemFcategoryBO itemCategory);

	public ItemFcategoryBO fillSbiling(ItemFcategoryBO itemCategory);

}
