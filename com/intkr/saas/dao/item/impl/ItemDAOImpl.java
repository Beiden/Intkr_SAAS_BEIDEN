package com.intkr.saas.dao.item.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.item.ItemDAO;
import com.intkr.saas.domain.dbo.item.ItemDO;

/**
 * 
 * @author Beiden
 * @date 2011-10-31 下午7:50:34
 * @version 1.0
 */
@Repository("ItemDAO")
public class ItemDAOImpl extends BaseDAOImpl<ItemDO> implements ItemDAO {

	public String getNameSpace() {
		return "item";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
