package com.intkr.saas.dao.order.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.order.DeliveryAddressDAO;
import com.intkr.saas.domain.dbo.order.DeliveryAddressDO;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:01:53
 * @version 1.0
 */
@Repository("DeliveryAddressDAO")
public class DeliveryAddressDAOImpl extends BaseDAOImpl<DeliveryAddressDO> implements DeliveryAddressDAO {

	public String getNameSpace() {
		return "deliveryAddress";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
