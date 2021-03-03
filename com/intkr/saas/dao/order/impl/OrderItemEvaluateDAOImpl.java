package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.OrderItemEvaluateDAO;
import com.intkr.saas.domain.dbo.order.OrderEvaluateDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午7:29:17
 * @version 1.0
 */
@Repository("OrderItemEvaluateDAO")
public class OrderItemEvaluateDAOImpl extends BaseDAOImpl<OrderEvaluateDO> implements OrderItemEvaluateDAO {

	public String getNameSpace() {
		return "orderItemEvaluate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
