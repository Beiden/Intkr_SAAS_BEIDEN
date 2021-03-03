package com.intkr.saas.manager.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateValueBO;
import com.intkr.saas.domain.dbo.item.ItemSpuTemplateValueDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 16:25:07
 * @version 1.0
 */
public interface ItemSpuTemplateValueManager extends BaseManager<ItemSpuTemplateValueBO, ItemSpuTemplateValueDO> {

	public List<?> fill(List<?> list);

	public ItemSpuTemplateBO fill(ItemSpuTemplateBO category);

	public ItemSpuTemplateBO fillFull(ItemSpuTemplateBO category);

}
