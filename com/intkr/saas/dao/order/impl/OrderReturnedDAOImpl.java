package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.OrderReturnedDAO;
import com.intkr.saas.domain.dbo.order.OrderReturnedDO;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 16:24:10
 * @version 1.0
 */
@Repository("ShopOrderReturnedDAO")
public class OrderReturnedDAOImpl extends BaseDAOImpl<OrderReturnedDO> implements OrderReturnedDAO {

	public String getNameSpace() {
		return "orderReturned";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
