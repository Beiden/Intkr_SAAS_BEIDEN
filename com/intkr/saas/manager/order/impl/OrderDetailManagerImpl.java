package com.intkr.saas.manager.order.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.OrderDetailDAO;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.dbo.order.OrderDetailDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.OrderDetailManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午7:57:26
 * @version 1.0
 */
@Repository("OrderDetailManager")
public class OrderDetailManagerImpl extends BaseManagerImpl<OrderDetailBO, OrderDetailDO> implements OrderDetailManager {

	@Resource
	private OrderDetailDAO orderDetailDAO;

	public BaseDAO<OrderDetailDO> getBaseDAO() {
		return orderDetailDAO;
	}

	public OrderBO fills(OrderBO order) {
		if (order == null) {
			return order;
		}
		OrderDetailBO query = new OrderDetailBO();
		query.set_pageSize(1000);
		query.setOrderId(order.getId());
		List<OrderDetailBO> datas = select(query);
		order.setOrderDetails(datas);
		return order;
	}

	public <T> List<T> fills(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof OrderBO) {
				fills((OrderBO) bo);
			}
		}
		return list;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof OrderEvaluateBO) {
				fill((OrderEvaluateBO) bo);
			} else if (bo instanceof ItemCustServBO) {
				fill((ItemCustServBO) bo);
			}
		}
		return list;
	}

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate) {
		if (evaluate == null) {
			return evaluate;
		}
		evaluate.setOrderDetail(get(evaluate.getOrderDetailId()));
		return evaluate;
	}

	public ItemCustServBO fill(ItemCustServBO itemCustServ) {
		if (itemCustServ == null) {
			return itemCustServ;
		}
		itemCustServ.setOrderDetail(get(itemCustServ.getOrderDetailId()));
		return itemCustServ;
	}

	public OrderReturnedBO fill(OrderReturnedBO orderReturn) {
		if (orderReturn == null) {
			return orderReturn;
		}
		OrderDetailBO query = new OrderDetailBO();
		query.setOrderId(orderReturn.getOrderId());
		List<OrderDetailBO> orderDetails = select(query);
		orderReturn.getOrder().setOrderDetails(orderDetails);
		return orderReturn;
	}

}
