package com.intkr.saas.manager.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.OrderReturnedDAO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.dbo.order.OrderReturnedDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.OrderReturnedManager;

/**
 * 
 * @author Beiden
 * @date 2017-09-07 16:24:10
 * @version 1.0
 */
@Repository("ShopOrderReturnedManager")
public class OrderReturnedManagerImpl extends BaseManagerImpl<OrderReturnedBO, OrderReturnedDO> implements OrderReturnedManager {

	@Resource
	private OrderReturnedDAO shopOrderReturnedDAO;

	public BaseDAO<OrderReturnedDO> getBaseDAO() {
		return shopOrderReturnedDAO;
	}

}
