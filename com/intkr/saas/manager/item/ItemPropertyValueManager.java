package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.domain.bo.item.ItemPropertyValueBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateValueBO;
import com.intkr.saas.domain.dbo.item.ItemPropertyValueDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 15:55:49
 * @version 1.0
 */
public interface ItemPropertyValueManager extends BaseManager<ItemPropertyValueBO, ItemPropertyValueDO> {

	public List<?> fill(List<?> list);

	public ItemPropertyBO fill(ItemPropertyBO property);

	public ItemSpuTemplateValueBO fill(ItemSpuTemplateValueBO spuTemplateValue);

}
