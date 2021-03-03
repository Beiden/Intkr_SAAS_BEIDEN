package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemPropertyDAO;
import com.intkr.saas.domain.dbo.item.ItemPropertyDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:42:37
 * @version 1.0
 */
@Repository("ItemPropertyDAO")
public class ItemPropertyDAOImpl extends BaseDAOImpl<ItemPropertyDO> implements ItemPropertyDAO {

	public String getNameSpace() {
		return "itemProperty";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
