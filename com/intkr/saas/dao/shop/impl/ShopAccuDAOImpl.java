package com.intkr.saas.dao.shop.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.ShopAccuDAO;
import com.intkr.saas.domain.dbo.shop.ShopAccuDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 18:09:23
 * @version 1.0
 */
@Repository("ShopAccuDAO")
public class ShopAccuDAOImpl extends BaseDAOImpl<ShopAccuDO> implements ShopAccuDAO {

	public String getNameSpace() {
		return "shopAccu";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
