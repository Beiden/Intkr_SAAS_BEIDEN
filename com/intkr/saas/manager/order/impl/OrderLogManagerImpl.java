package com.intkr.saas.manager.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.OrderLogDAO;
import com.intkr.saas.domain.bo.order.OrderLogBO;
import com.intkr.saas.domain.dbo.order.OrderLogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.OrderLogManager;

/**
 * 
 * @author Beiden
 * @date 2016-6-4 下午2:23:30
 * @version 1.0
 */
@Repository("OrderLogManager")
public class OrderLogManagerImpl extends BaseManagerImpl<OrderLogBO, OrderLogDO> implements OrderLogManager {

	@Resource
	private OrderLogDAO orderLogDAO;

	public BaseDAO<OrderLogDO> getBaseDAO() {
		return orderLogDAO;
	}

}
