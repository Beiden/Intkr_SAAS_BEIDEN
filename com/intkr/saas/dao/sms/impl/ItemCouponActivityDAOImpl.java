package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemCouponActivityDAO;
import com.intkr.saas.domain.dbo.sms.ItemCouponActivityDO;

/**
 * 优惠券
 * 
 * @table item_coupon_activity
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:03
 * @version 1.0
 */
@Repository("ItemCouponActivityDAO")
public class ItemCouponActivityDAOImpl extends BaseDAOImpl<ItemCouponActivityDO> implements ItemCouponActivityDAO {

	public String getNameSpace() {
		return "itemCouponActivity";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
