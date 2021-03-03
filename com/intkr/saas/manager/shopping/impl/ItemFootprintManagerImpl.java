package com.intkr.saas.manager.shopping.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shopping.ItemFootprintDAO;
import com.intkr.saas.domain.bo.shopping.ItemFootprintBO;
import com.intkr.saas.domain.dbo.shopping.ItemFootprintDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shopping.ItemFootprintManager;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 15:36:08
 * @version 1.0
 */
@Repository("ShopFootprintManager")
public class ItemFootprintManagerImpl extends BaseManagerImpl<ItemFootprintBO, ItemFootprintDO> implements ItemFootprintManager {

	@Resource
	private ItemFootprintDAO shopFootprintDAO;

	public BaseDAO<ItemFootprintDO> getBaseDAO() {
		return shopFootprintDAO;
	}

}
