package com.intkr.saas.manager.order;

import java.util.List;

import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.dbo.order.OrderDO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.util.map.TwoMap;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:46:33
 * @version 1.0
 */
public interface OrderManager extends BaseManager<OrderBO, OrderDO> {

	public OrderReturnedBO fill(OrderReturnedBO orderReturned);

	public ItemCustServBO fill(ItemCustServBO itemCustServ);

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate);

	public List<?> fill(List<?> list);
	
	public List<TwoMap<String, Integer>> buyerCountByStatus(Long userId);

}
