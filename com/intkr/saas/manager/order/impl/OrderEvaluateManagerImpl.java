package com.intkr.saas.manager.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.OrderItemEvaluateDAO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.dbo.order.OrderEvaluateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.OrderEvaluateManager;

/**
 * 
 * @author Beiden
 * @date 2016-5-20 下午7:30:16
 * @version 1.0
 */
@Repository("OrderItemEvaluateManager")
public class OrderEvaluateManagerImpl extends BaseManagerImpl<OrderEvaluateBO, OrderEvaluateDO> implements OrderEvaluateManager {

	@Resource
	private OrderItemEvaluateDAO orderItemEvaluateDAO;

	public BaseDAO<OrderEvaluateDO> getBaseDAO() {
		return orderItemEvaluateDAO;
	}

}
