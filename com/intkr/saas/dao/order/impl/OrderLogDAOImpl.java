package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.OrderLogDAO;
import com.intkr.saas.domain.dbo.order.OrderLogDO;

/**
 * 
 * @author Beiden
 * @date 2016-6-4 下午2:22:38
 * @version 1.0
 */
@Repository("OrderLogDAO")
public class OrderLogDAOImpl extends BaseDAOImpl<OrderLogDO> implements OrderLogDAO {

	public String getNameSpace() {
		return "orderLog";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
