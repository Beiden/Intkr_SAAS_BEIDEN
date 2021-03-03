package com.intkr.saas.manager.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.ItemCustServDAO;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.dbo.order.ItemCustServDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.ItemCustServManager;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 16:24:32
 * @version 1.0
 */
@Repository("ShopItemCustServManager")
public class ItemCustServManagerImpl extends BaseManagerImpl<ItemCustServBO, ItemCustServDO> implements ItemCustServManager {

	@Resource
	private ItemCustServDAO shopItemCustServDAO;

	public BaseDAO<ItemCustServDO> getBaseDAO() {
		return shopItemCustServDAO;
	}

}
