package com.intkr.saas.dao.shopping.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shopping.ItemFootprintDAO;
import com.intkr.saas.domain.dbo.shopping.ItemFootprintDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-02 15:36:08
 * @version 1.0
 */
@Repository("ShopFootprintDAO")
public class ItemFootprintDAOImpl extends BaseDAOImpl<ItemFootprintDO> implements ItemFootprintDAO {

	public String getNameSpace() {
		return "itemFootprint";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
