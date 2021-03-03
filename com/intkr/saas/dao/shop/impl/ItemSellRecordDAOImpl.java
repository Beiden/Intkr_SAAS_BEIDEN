package com.intkr.saas.dao.shop.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.ItemSellRecordDAO;
import com.intkr.saas.domain.dbo.shop.ItemSellRecordDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午11:09:36
 * @version 1.0
 */
@Repository("ShopItemSellRecordDAO")
public class ItemSellRecordDAOImpl extends BaseDAOImpl<ItemSellRecordDO> implements ItemSellRecordDAO {

	public String getNameSpace() {
		return "itemSellRecord";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
