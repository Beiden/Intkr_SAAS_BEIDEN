package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSkuPropertyValueDAO;
import com.intkr.saas.domain.dbo.item.ItemSkuPropertyValueDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:45:06
 * @version 1.0
 */
@Repository("ItemSkuPropertyValueDAO")
public class ItemSkuPropertyValueDAOImpl extends BaseDAOImpl<ItemSkuPropertyValueDO> implements ItemSkuPropertyValueDAO {

	public String getNameSpace() {
		return "itemSkuPropertyValue";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
