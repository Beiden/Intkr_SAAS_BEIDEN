package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.ItemCustServDAO;
import com.intkr.saas.domain.dbo.order.ItemCustServDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 16:24:32
 * @version 1.0
 */
@Repository("ShopItemCustServDAO")
public class ItemCustServDAOImpl extends BaseDAOImpl<ItemCustServDO> implements ItemCustServDAO {

	public String getNameSpace() {
		return "itemCustServ";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
