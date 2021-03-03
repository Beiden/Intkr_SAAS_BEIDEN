package com.intkr.saas.module.screen.admin.sms.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemCouponBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.sms.ItemCouponManager;
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
public class ItemCouponAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCouponManager manager = IOC.get(ItemCouponManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemCouponId = RequestUtil.getParam(request, "itemCouponId");
		ItemCouponBO itemCoupon = manager.get(itemCouponId);
		request.setAttribute("itemCoupon", itemCoupon);
		request.setAttribute("addId", manager.getId());
	}

}
