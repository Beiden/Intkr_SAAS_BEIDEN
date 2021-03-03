package com.intkr.saas.manager.order;

import java.util.List;

import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderDetailBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.dbo.order.OrderDetailDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午7:57:02
 * @version 1.0
 */
public interface OrderDetailManager extends BaseManager<OrderDetailBO, OrderDetailDO> {

	public OrderReturnedBO fill(OrderReturnedBO orderReturn);

	public ItemCustServBO fill(ItemCustServBO itemCustServ);

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate);

	public OrderBO fills(OrderBO order);

	public <T> List<T> fills(List<T> list);

	public <T> List<T> fill(List<T> list);

}
