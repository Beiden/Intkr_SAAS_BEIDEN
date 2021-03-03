package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.OrderDetailDAO;
import com.intkr.saas.domain.dbo.order.OrderDetailDO;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午7:56:32
 * @version 1.0
 */
@Repository("OrderDetailDAO")
public class OrderDetailDAOImpl extends BaseDAOImpl<OrderDetailDO> implements OrderDetailDAO {

	public String getNameSpace() {
		return "orderDetail";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
