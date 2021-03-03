package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemFcategoryDAO;
import com.intkr.saas.domain.dbo.item.ItemFcategoryDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:40:03
 * @version 1.0
 */
@Repository("ItemFcategoryDAO")
public class ItemFcategoryDAOImpl extends BaseDAOImpl<ItemFcategoryDO> implements ItemFcategoryDAO {

	public String getNameSpace() {
		return "itemFcategory";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
