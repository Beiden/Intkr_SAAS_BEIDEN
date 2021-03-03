package com.intkr.saas.dao.item.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemLogDAO;
import com.intkr.saas.domain.dbo.item.ItemLogDO;

/**
 * 
 * @author Beiden
 * @date 2016-6-4 下午2:43:27
 * @version 1.0
 */
@Repository("ItemLogDAO")
public class ItemLogDAOImpl extends BaseDAOImpl<ItemLogDO> implements ItemLogDAO {

	public String getNameSpace() {
		return "itemLog";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
