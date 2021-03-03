package com.intkr.saas.manager.shop.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shop.ShopDAO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.order.ItemCustServBO;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderEvaluateBO;
import com.intkr.saas.domain.bo.order.OrderReturnedBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.shopping.ShopCollectBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.dbo.shop.ShopDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shop.ShopManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-22 上午10:15:00
 * @version 1.0
 */
@Repository("ShopManager")
public class ShopManagerImpl extends BaseManagerImpl<ShopBO, ShopDO> implements ShopManager {

	@Resource
	private ShopDAO shopDAO;

	public BaseDAO<ShopDO> getBaseDAO() {
		return shopDAO;
	}

	public OrderEvaluateBO fill(OrderEvaluateBO evaluate) {
		if (evaluate != null && IdEngine.isValidate(evaluate.getShopId())) {
			evaluate.setShop(get(evaluate.getShopId()));
		}
		return evaluate;
	}

	public ItemCustServBO fill(ItemCustServBO itemCustServ) {
		if (itemCustServ != null && IdEngine.isValidate(itemCustServ.getShopId())) {
			itemCustServ.setShop(get(itemCustServ.getShopId()));
		}
		return itemCustServ;
	}

	public OrderReturnedBO fill(OrderReturnedBO orderReturned) {
		if (orderReturned != null && IdEngine.isValidate(orderReturned.getShopId())) {
			orderReturned.setShop(get(orderReturned.getShopId()));
		}
		return orderReturned;
	}

	public ShopCollectBO fill(ShopCollectBO shopCollect) {
		if (shopCollect != null && IdEngine.isValidate(shopCollect.getShopId())) {
			shopCollect.setShop(get(shopCollect.getShopId()));
		}
		return shopCollect;
	}

	public OrderBO fill(OrderBO order) {
		if (order != null && IdEngine.isValidate(order.getShopId())) {
			order.setShop(get(order.getShopId()));
		}
		return order;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemBO) {
				fill((ItemBO) obj);
			} else if (obj instanceof OrderBO) {
				fill((OrderBO) obj);
			} else if (obj instanceof OrderEvaluateBO) {
				fill((OrderEvaluateBO) obj);
			} else if (obj instanceof ItemCustServBO) {
				fill((ItemCustServBO) obj);
			} else if (obj instanceof OrderReturnedBO) {
				fill((OrderReturnedBO) obj);
			} else if (obj instanceof ShopCollectBO) {
				fill((ShopCollectBO) obj);
			}
		}
		return list;
	}

	public ShopBO getByUserId(Long userId) {
		ShopBO query = new ShopBO();
		query.setUserId(userId);
		ShopBO dto = selectOne(query);
		return dto;
	}

	public ItemBO fill(ItemBO item) {
		if (item != null && IdEngine.isValidate(item.getShopId())) {
			item.setShop(get(item.getShopId()));
		}
		return item;
	}

	public UserBO fill(UserBO user) {
		if (user == null) {
			return user;
		}
		user.setShop(getByUserId(user.getId()));
		return user;
	}

}
