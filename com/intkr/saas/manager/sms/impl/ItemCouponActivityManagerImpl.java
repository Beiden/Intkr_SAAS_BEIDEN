package com.intkr.saas.manager.sms.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemCouponActivityDAO;
import com.intkr.saas.domain.bo.sms.ItemCouponActivityBO;
import com.intkr.saas.domain.dbo.sms.ItemCouponActivityDO;
import com.intkr.saas.manager.sms.ItemCouponActivityManager;

/**
 * 优惠券
 * 
 * @table item_coupon_activity
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:04
 * @version 1.0
 */
@Repository("ItemCouponActivityManager")
public class ItemCouponActivityManagerImpl extends BaseManagerImpl<ItemCouponActivityBO, ItemCouponActivityDO> implements ItemCouponActivityManager {

	@Resource
	private ItemCouponActivityDAO itemCouponActivityDAO;

	public BaseDAO<ItemCouponActivityDO> getBaseDAO() {
		return itemCouponActivityDAO;
	}

}
