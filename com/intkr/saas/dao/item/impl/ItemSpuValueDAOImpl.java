package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSpuValueDAO;
import com.intkr.saas.domain.dbo.item.ItemSpuValueDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:45:06
 * @version 1.0
 */
@Repository("ItemSpuValueDAO")
public class ItemSpuValueDAOImpl extends BaseDAOImpl<ItemSpuValueDO> implements ItemSpuValueDAO {

	public String getNameSpace() {
		return "itemSpuValue";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
