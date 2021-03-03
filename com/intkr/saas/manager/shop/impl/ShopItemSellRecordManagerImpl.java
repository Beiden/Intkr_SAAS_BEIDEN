package com.intkr.saas.manager.shop.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shop.ItemSellRecordDAO;
import com.intkr.saas.domain.bo.shop.ItemSellRecordBO;
import com.intkr.saas.domain.dbo.shop.ItemSellRecordDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shop.ItemSellRecordManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午11:10:26
 * @version 1.0
 */
@Repository("ShopItemSellRecordManager")
public class ShopItemSellRecordManagerImpl extends BaseManagerImpl<ItemSellRecordBO, ItemSellRecordDO> implements ItemSellRecordManager {

	@Resource
	private ItemSellRecordDAO itemSellRecordDAO;

	public BaseDAO<ItemSellRecordDO> getBaseDAO() {
		return itemSellRecordDAO;
	}

}
