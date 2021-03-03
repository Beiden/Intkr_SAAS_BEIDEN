package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemNewDAO;
import com.intkr.saas.domain.dbo.sms.ItemNewDO;

/**
 * 新品推荐
 * 
 * @table item_new
 * 
 * @author Beiden
 * @date 2020-11-11 23:11:23
 * @version 1.0
 */
@Repository("ItemNewDAO")
public class ItemNewDAOImpl extends BaseDAOImpl<ItemNewDO> implements ItemNewDAO {

	public String getNameSpace() {
		return "itemNew";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
