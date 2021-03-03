package com.intkr.saas.dao.shop.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.ShopCommentDAO;
import com.intkr.saas.domain.dbo.shop.ShopCommentDO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 上午11:22:21
 * @version 1.0
 */
@Repository("ShopCommentDAO")
public class ShopCommentDAOImpl extends BaseDAOImpl<ShopCommentDO> implements ShopCommentDAO {

	public String getNameSpace() {
		return "shopComment";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
