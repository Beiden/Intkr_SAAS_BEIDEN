package com.intkr.saas.dao.shopping.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shopping.ShopCollectDAO;
import com.intkr.saas.domain.dbo.shopping.ShopCollectDO;

/**
 * 店铺收藏
 * 
 * @table shop_collect
 * 
 * @author Beiden
 * @date 2020-11-14 17:59:25
 * @version 1.0
 */
@Repository("ShopCollectDAO")
public class ShopCollectDAOImpl extends BaseDAOImpl<ShopCollectDO> implements ShopCollectDAO {

	public String getNameSpace() {
		return "shopCollect";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
