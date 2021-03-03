package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemPopularDAO;
import com.intkr.saas.domain.dbo.sms.ItemPopularDO;

/**
 * 人气商品
 * 
 * @table item_popular
 * 
 * @author Beiden
 * @date 2020-11-11 23:10:57
 * @version 1.0
 */
@Repository("ItemPopularDAO")
public class ItemPopularDAOImpl extends BaseDAOImpl<ItemPopularDO> implements ItemPopularDAO {

	public String getNameSpace() {
		return "itemPopular";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
