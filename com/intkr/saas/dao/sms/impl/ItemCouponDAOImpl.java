package com.intkr.saas.dao.sms.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.sms.ItemCouponDAO;
import com.intkr.saas.domain.dbo.sms.ItemCouponDO;

/**
 * 优惠券
 * 
 * @table item_coupon
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:09
 * @version 1.0
 */
@Repository("ItemCouponDAO")
public class ItemCouponDAOImpl extends BaseDAOImpl<ItemCouponDO> implements ItemCouponDAO {

	public String getNameSpace() {
		return "itemCoupon";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
