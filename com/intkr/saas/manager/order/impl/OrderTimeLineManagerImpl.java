package com.intkr.saas.manager.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.OrderTimeLineDAO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.dbo.order.OrderTimeLineDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.OrderTimeLineManager;

/**
 * 
 * @author Beiden
 * @date 2016-6-10 上午8:04:25
 * @version 1.0
 */
@Repository("OrderTimeLineManager")
public class OrderTimeLineManagerImpl extends BaseManagerImpl<OrderTimeLineBO, OrderTimeLineDO> implements OrderTimeLineManager {

	@Resource
	private OrderTimeLineDAO orderTimeLineLogDAO;

	public BaseDAO<OrderTimeLineDO> getBaseDAO() {
		return orderTimeLineLogDAO;
	}

}
