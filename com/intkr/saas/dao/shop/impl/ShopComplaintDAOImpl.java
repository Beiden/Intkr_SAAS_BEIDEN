package com.intkr.saas.dao.shop.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.ShopComplaintDAO;
import com.intkr.saas.domain.dbo.shop.ShopComplaintDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 18:09:37
 * @version 1.0
 */
@Repository("ShopComplaintDAO")
public class ShopComplaintDAOImpl extends BaseDAOImpl<ShopComplaintDO> implements ShopComplaintDAO {

	public String getNameSpace() {
		return "shopComplaint";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
