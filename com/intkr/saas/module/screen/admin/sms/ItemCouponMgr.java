package com.intkr.saas.module.screen.admin.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemCouponActivityBO;
import com.intkr.saas.domain.bo.sms.ItemCouponBO;
import com.intkr.saas.domain.type.sms.CouponActivityStatus;
import com.intkr.saas.domain.type.sms.CouponSendType;
import com.intkr.saas.domain.type.sms.CouponStatus;
import com.intkr.saas.domain.type.sms.CouponType;
import com.intkr.saas.domain.type.sms.CouponUsePlatform;
import com.intkr.saas.domain.type.sms.CouponUseType;
import com.intkr.saas.manager.sms.ItemCouponActivityManager;
import com.intkr.saas.manager.sms.ItemCouponManager;
import com.intkr.saas.module.action.sms.ItemCouponAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 优惠券
 * 
 * @table item_coupon
 * 
 * @author Beiden
 * @date 2020-11-11 21:30:09
 * @version 1.0
 */
public class ItemCouponMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCouponActivityManager itemCouponActivityManager = IOC.get(ItemCouponActivityManager.class);

	private ItemCouponManager manager = IOC.get(ItemCouponManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		Long activityId = RequestUtil.getParam(request, "activityId", Long.class);
		ItemCouponActivityBO activity = itemCouponActivityManager.get(activityId);
		ItemCouponBO query = ItemCouponAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("activity", activity);
		request.setAttribute("list", query.getDatas());

		request.setAttribute("CouponStatus", CouponStatus.Error);
		request.setAttribute("CouponActivityStatus", CouponActivityStatus.Error);
		request.setAttribute("CouponType", CouponType.Error);
		request.setAttribute("CouponSendType", CouponSendType.Error);
		request.setAttribute("CouponUseType", CouponUseType.Error);
		request.setAttribute("CouponUsePlatform", CouponUsePlatform.Error);
	}

}
