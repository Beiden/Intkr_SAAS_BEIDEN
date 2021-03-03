package com.intkr.saas.dao.shop.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.ShopDAO;
import com.intkr.saas.domain.dbo.shop.ShopDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:11:07
 * @version 1.0
 */
@Repository("ShopDAO")
public class ShopDAOImpl extends BaseDAOImpl<ShopDO> implements ShopDAO {

	public String getNameSpace() {
		return "shop";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
