package com.intkr.saas.manager.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.DeliveryAddressDAO;
import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.dbo.order.DeliveryAddressDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.DeliveryAddressManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-27 下午5:03:37
 * @version 1.0
 */
@Repository("DeliveryAddressManager")
public class DeliveryAddressManagerImpl extends BaseManagerImpl<DeliveryAddressBO, DeliveryAddressDO> implements DeliveryAddressManager {

	@Resource
	private DeliveryAddressDAO deliveryAddressDAO;

	public BaseDAO<DeliveryAddressDO> getBaseDAO() {
		return deliveryAddressDAO;
	}

	public List<DeliveryAddressBO> selectByUserId(Long userId) {
		DeliveryAddressBO query = new DeliveryAddressBO();
		query.setUserId(userId);
		List<DeliveryAddressBO> list = select(query);
		return list;
	}

	public DeliveryAddressBO getDefault(Long userId) {
		if (!IdEngine.isValidate(userId)) {
			return null;
		}
		DeliveryAddressBO query = new DeliveryAddressBO();
		query.setUserId(userId);
		query.setIsDefault(1);
		return selectOne(query);
	}

	public OrderBO fill(OrderBO order) {
		if (order == null || order.getDeliAddrId() == null || "".equals(order.getDeliAddrId())) {
			return order;
		}
		DeliveryAddressBO deliveryAddress = get(order.getDeliAddrId());
		order.setDeliveryAddress(deliveryAddress);
		return order;
	}

}
