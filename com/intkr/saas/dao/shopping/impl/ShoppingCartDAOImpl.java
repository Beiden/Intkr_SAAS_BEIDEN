package com.intkr.saas.dao.shopping.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shopping.ShoppingCartDAO;
import com.intkr.saas.domain.dbo.shopping.ShoppingCartDO;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:26:15
 * @version 1.0
 */
@Repository("ShopCartDAO")
public class ShoppingCartDAOImpl extends BaseDAOImpl<ShoppingCartDO> implements ShoppingCartDAO {

	public String getNameSpace() {
		return "shopCart";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
