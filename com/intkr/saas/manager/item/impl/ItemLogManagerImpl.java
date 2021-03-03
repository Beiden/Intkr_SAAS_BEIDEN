package com.intkr.saas.manager.item.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemLogDAO;
import com.intkr.saas.domain.bo.item.ItemLogBO;
import com.intkr.saas.domain.dbo.item.ItemLogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemLogManager;

/**
 * 
 * @author Beiden
 * @date 2016-6-4 下午2:44:22
 * @version 1.0
 */
@Repository("ItemLogManager")
public class ItemLogManagerImpl extends BaseManagerImpl<ItemLogBO, ItemLogDO> implements ItemLogManager {

	@Resource
	private ItemLogDAO itemLogDAO;

	public BaseDAO<ItemLogDO> getBaseDAO() {
		return itemLogDAO;
	}

}
