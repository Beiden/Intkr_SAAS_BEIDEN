package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.PaymentDAO;
import com.intkr.saas.domain.dbo.order.PaymentDO;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:26:15
 * @version 1.0
 */
@Repository("PaymentDAO")
public class PaymentDAOImpl extends BaseDAOImpl<PaymentDO> implements PaymentDAO {

	public String getNameSpace() {
		return "payment";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
