package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemSpuDAO;
import com.intkr.saas.domain.dbo.item.ItemSpuDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:44:35
 * @version 1.0
 */
@Repository("ItemSpuDAO")
public class ItemSpuDAOImpl extends BaseDAOImpl<ItemSpuDO> implements ItemSpuDAO {

	public String getNameSpace() {
		return "itemSpu";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
