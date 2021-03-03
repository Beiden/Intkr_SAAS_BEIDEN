package com.intkr.saas.module.screen.admin.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemCouponActivityBO;
import com.intkr.saas.domain.type.sms.CouponActivityStatus;
import com.intkr.saas.domain.type.sms.CouponSendType;
import com.intkr.saas.domain.type.sms.CouponType;
import com.intkr.saas.domain.type.sms.CouponUsePlatform;
import com.intkr.saas.domain.type.sms.CouponUseType;
import com.intkr.saas.manager.item.ItemCategoryManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.sms.ItemCouponActivityManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 优惠券
 * 
 * @table item_coupon_activity
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:04
 * @version 1.0
 */
public class ItemCouponActivityAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCouponActivityManager manager = IOC.get(ItemCouponActivityManager.class);
	
	private ItemManager itemManager = IOC.get(ItemManager.class);
	
	private ItemCategoryManager itemCategoryManager = IOC.get(ItemCategoryManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemCouponActivityId = RequestUtil.getParam(request, "activityId");
		ItemCouponActivityBO itemCouponActivity = manager.get(itemCouponActivityId);
		itemManager.fill(itemCouponActivity);
		itemCategoryManager.fill(itemCouponActivity);
		
		request.setAttribute("itemCouponActivity", itemCouponActivity);
		if (itemCouponActivity == null) {
			request.setAttribute("addId", manager.getId());
		}

		request.setAttribute("CouponActivityStatus", CouponActivityStatus.Error);
		request.setAttribute("CouponType", CouponType.Error);
		request.setAttribute("CouponSendType", CouponSendType.Error);
		request.setAttribute("CouponUseType", CouponUseType.Error);
		request.setAttribute("CouponUsePlatform", CouponUsePlatform.Error);
	}

}
