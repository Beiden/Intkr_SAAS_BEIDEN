package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSkuValueDAO;
import com.intkr.saas.domain.dbo.item.ItemSkuValueDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:44:23
 * @version 1.0
 */
@Repository("ItemSkuValueDAO")
public class ItemSkuValueDAOImpl extends BaseDAOImpl<ItemSkuValueDO> implements ItemSkuValueDAO {

	public String getNameSpace() {
		return "itemSkuValue";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
