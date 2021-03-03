package com.intkr.saas.manager.item.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.item.ItemBrandDAO;
import com.intkr.saas.domain.bo.item.ItemBrandBO;
import com.intkr.saas.domain.dbo.item.ItemBrandDO;
import com.intkr.saas.manager.item.ItemBrandManager;

/**
 * 品牌
 * 
 * @table item_brand
 * 
 * @author Beiden
 * @date 2020-11-11 22:33:08
 * @version 1.0
 */
@Repository("ItemBrandManager")
public class ItemBrandManagerImpl extends BaseManagerImpl<ItemBrandBO, ItemBrandDO> implements ItemBrandManager {

	@Resource
	private ItemBrandDAO itemBrandDAO;

	public BaseDAO<ItemBrandDO> getBaseDAO() {
		return itemBrandDAO;
	}

}
