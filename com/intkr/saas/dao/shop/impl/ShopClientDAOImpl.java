package com.intkr.saas.dao.shop.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.ShopClientDAO;
import com.intkr.saas.domain.dbo.shop.ShopClientDO;

/**
 * 店铺客户
 * 
 * @table shop_client
 * 
 * @author Beiden
 * @date 2020-11-02 10:01:02
 * @version 1.0
 */
@Repository("ShopClientDAO")
public class ShopClientDAOImpl extends BaseDAOImpl<ShopClientDO> implements ShopClientDAO {

	public String getNameSpace() {
		return "shopClient";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
