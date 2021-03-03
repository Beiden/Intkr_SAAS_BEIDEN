package com.intkr.saas.dao.shop.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.ShopActivityDAO;
import com.intkr.saas.domain.dbo.shop.ShopActivityDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:11:07
 * @version 1.0
 */
@Repository("ActivityDAO")
public class ShopActivityDAOImpl extends BaseDAOImpl<ShopActivityDO> implements ShopActivityDAO {

	public String getNameSpace() {
		return "shopActivity";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
