package com.intkr.saas.dao.shopping.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shopping.ItemCollectDAO;
import com.intkr.saas.domain.dbo.shopping.ItemCollectDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 16:13:19
 * @version 1.0
 */
@Repository("ShopItemCollectDAO")
public class ItemCollectDAOImpl extends BaseDAOImpl<ItemCollectDO> implements ItemCollectDAO {

	public String getNameSpace() {
		return "itemCollect";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
