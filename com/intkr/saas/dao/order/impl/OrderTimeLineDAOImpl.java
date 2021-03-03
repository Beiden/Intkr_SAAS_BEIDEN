package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.OrderTimeLineDAO;
import com.intkr.saas.domain.dbo.order.OrderTimeLineDO;

/**
 * 
 * @author Beiden
 * @date 2016-6-10 上午8:03:18
 * @version 1.0
 */
@Repository("OrderTimeLineDAO")
public class OrderTimeLineDAOImpl extends BaseDAOImpl<OrderTimeLineDO> implements OrderTimeLineDAO {

	public String getNameSpace() {
		return "orderTimeLine";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
