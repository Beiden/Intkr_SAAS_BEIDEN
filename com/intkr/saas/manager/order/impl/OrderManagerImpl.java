package com.intkr.saas.manager.order.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.OrderDAO;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.dbo.order.OrderDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.util.map.TwoMap;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:46:55
 * @version 1.0
 */
@Repository("OrderManager")
public class OrderManagerImpl extends BaseManagerImpl<OrderBO, OrderDO> implements OrderManager {

	@Resource
	private OrderDAO orderDAO;

	public BaseDAO<OrderDO> getBaseDAO() {
		return orderDAO;
	}

	public OrderReturnedBO fill(OrderReturnedBO orderReturned) {
		if (orderReturned == null) {
			return orderReturned;
		}
		orderReturned.setOrder(get(orderReturned.getOrderId()));
		return orderReturned;
	}

	public ItemCustServBO fill(ItemCustServBO itemCustServ) {
		if (itemCustServ == null) {
			return itemCustServ;
		}
		itemCustServ.setOrder(get(itemCustServ.getOrderId()));
		return itemCustServ;
	}

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate) {
		if (evaluate == null) {
			return evaluate;
		}
		evaluate.setOrder(get(evaluate.getOrderId()));
		return evaluate;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof OrderEvaluateBO) {
				fill((OrderEvaluateBO) obj);
			} else if (obj instanceof ItemCustServBO) {
				fill((ItemCustServBO) obj);
			}
		}
		return list;
	}

	public List<TwoMap<String, Integer>> buyerCountByStatus(Long userId) {
		return orderDAO.buyerCountByStatus(userId);
	}

}
