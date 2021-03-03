package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.dbo.item.ItemPropertyDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 15:55:37
 * @version 1.0
 */
public interface ItemPropertyManager extends BaseManager<ItemPropertyBO, ItemPropertyDO> {

	public List<?> fill(List<?> list);

	public ItemSpuTemplateBO fill(ItemSpuTemplateBO spuTemplate);

}
