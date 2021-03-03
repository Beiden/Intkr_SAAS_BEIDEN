package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemCouponDAO;
import com.intkr.saas.domain.bo.sms.ItemCouponBO;
import com.intkr.saas.domain.dbo.sms.ItemCouponDO;
import com.intkr.saas.manager.sms.ItemCouponManager;

/**
 * 优惠券
 * 
 * @table item_coupon
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:09
 * @version 1.0
 */
@Repository("ItemCouponManager")
public class ItemCouponManagerImpl extends BaseManagerImpl<ItemCouponBO, ItemCouponDO> implements ItemCouponManager {

	@Resource
	private ItemCouponDAO itemCouponDAO;

	public BaseDAO<ItemCouponDO> getBaseDAO() {
		return itemCouponDAO;
	}

}
