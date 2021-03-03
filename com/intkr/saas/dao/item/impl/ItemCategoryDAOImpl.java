package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemCategoryDAO;
import com.intkr.saas.domain.dbo.item.ItemCategoryDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:35:28
 * @version 1.0
 */
@Repository("ItemCategoryDAO")
public class ItemCategoryDAOImpl extends BaseDAOImpl<ItemCategoryDO> implements ItemCategoryDAO {

	public String getNameSpace() {
		return "itemCategory";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
