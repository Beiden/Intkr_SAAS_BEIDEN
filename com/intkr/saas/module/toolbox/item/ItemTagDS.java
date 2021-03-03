package com.intkr.saas.module.toolbox.item;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemTagBO;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemTagManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 */
public class ItemTagDS extends BaseToolBox {

	private ItemTagManager itemTagManager = IOC.get(ItemTagManager.class);

	private ItemManager itemManager = IOC.get(ItemManager.class);

	public List<ItemTagBO> fill(ItemBO item) {
		try {
			itemTagManager.fill(item);
			return item.getTags();
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

}
