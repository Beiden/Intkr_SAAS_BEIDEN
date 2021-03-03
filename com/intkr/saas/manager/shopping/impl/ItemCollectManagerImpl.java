package com.intkr.saas.manager.shopping.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shopping.ItemCollectDAO;
import com.intkr.saas.domain.bo.shopping.ItemCollectBO;
import com.intkr.saas.domain.dbo.shopping.ItemCollectDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shopping.ItemCollectManager;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 16:13:19
 * @version 1.0
 */
@Repository("ItemCollectManager")
public class ItemCollectManagerImpl extends BaseManagerImpl<ItemCollectBO, ItemCollectDO> implements ItemCollectManager {

	@Resource
	private ItemCollectDAO shopItemCollectDAO;

	public BaseDAO<ItemCollectDO> getBaseDAO() {
		return shopItemCollectDAO;
	}

}
