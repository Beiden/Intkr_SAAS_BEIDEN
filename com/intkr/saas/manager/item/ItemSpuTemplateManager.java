package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.dbo.item.ItemSpuTemplateDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:24:59
 * @version 1.0
 */
public interface ItemSpuTemplateManager extends BaseManager<ItemSpuTemplateBO, ItemSpuTemplateDO> {

	public List<?> fill(List<?> list);

	public ItemCategoryBO fill(ItemCategoryBO category);

	public ItemCategoryBO fillFull(ItemCategoryBO category);

}
