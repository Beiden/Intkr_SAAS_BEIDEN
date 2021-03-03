package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemPropertyValueDAO;
import com.intkr.saas.domain.dbo.item.ItemPropertyValueDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:42:47
 * @version 1.0
 */
@Repository("ItemPropertyValueDAO")
public class ItemPropertyValueDAOImpl extends BaseDAOImpl<ItemPropertyValueDO> implements ItemPropertyValueDAO {

	public String getNameSpace() {
		return "itemPropertyValue";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
