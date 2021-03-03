package com.intkr.saas.manager.order;

import java.util.List;

import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.dbo.order.DeliveryAddressDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:02:43
 * @version 1.0
 */
public interface DeliveryAddressManager extends BaseManager<DeliveryAddressBO, DeliveryAddressDO> {

	public List<DeliveryAddressBO> selectByUserId(Long userId);

	public DeliveryAddressBO getDefault(Long userId);

	public OrderBO fill(OrderBO order);

}
