package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemNewDAO;
import com.intkr.saas.domain.bo.sms.ItemNewBO;
import com.intkr.saas.domain.dbo.sms.ItemNewDO;
import com.intkr.saas.manager.sms.ItemNewManager;

/**
 * 新品推荐
 * 
 * @table item_new
 * 
 * @author Beiden
 * @date 2020-11-11 23:11:23
 * @version 1.0
 */
@Repository("ItemNewManager")
public class ItemNewManagerImpl extends BaseManagerImpl<ItemNewBO, ItemNewDO> implements ItemNewManager {

	@Resource
	private ItemNewDAO itemNewDAO;

	public BaseDAO<ItemNewDO> getBaseDAO() {
		return itemNewDAO;
	}

}
