package com.intkr.saas.module.action.sms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.sms.ItemCouponActivityBO;
import com.intkr.saas.domain.bo.sms.ItemCouponBO;
import com.intkr.saas.domain.type.sms.CouponActivityStatus;
import com.intkr.saas.domain.type.sms.CouponStatus;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.sms.ItemCouponActivityManager;
import com.intkr.saas.manager.sms.ItemCouponManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
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
public class ItemCouponActivityAction extends BaseAction<ItemCouponActivityBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCouponActivityManager itemCouponActivityManager = IOC.get(ItemCouponActivityManager.class);

	private ItemCouponManager itemCouponManager = IOC.get(ItemCouponManager.class);

	public ItemCouponActivityBO getBO(HttpServletRequest request) {
		ItemCouponActivityBO itemCouponActivityBO = getParameter(request);
		return itemCouponActivityBO;
	}

	public static ItemCouponActivityBO getParameter(HttpServletRequest request) {
		ItemCouponActivityBO itemCouponActivityBO = new ItemCouponActivityBO();
		itemCouponActivityBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemCouponActivityBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemCouponActivityBO.setActivityId(RequestUtil.getParam(request, "activityId", Long.class));
		itemCouponActivityBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemCouponActivityBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemCouponActivityBO.setType(RequestUtil.getParam(request, "type", String.class));
		itemCouponActivityBO.setSendType(RequestUtil.getParam(request, "sendType", String.class));
		itemCouponActivityBO.setUseType(RequestUtil.getParam(request, "useType", String.class));
		itemCouponActivityBO.setUsePlatform(RequestUtil.getParam(request, "usePlatform", String.class));
		itemCouponActivityBO.setUseMoney(MoneyUtil.parse(RequestUtil.getParam(request, "useMoney")));
		itemCouponActivityBO.setMoney(MoneyUtil.parse(RequestUtil.getParam(request, "money")));
		itemCouponActivityBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemCouponActivityBO.setStartTime(RequestUtil.getParam(request, "startTime", Date.class));
		itemCouponActivityBO.setEndTime(RequestUtil.getParam(request, "endTime", Date.class));
		itemCouponActivityBO.setNote(RequestUtil.getParam(request, "note", String.class));
		itemCouponActivityBO.setCount(RequestUtil.getParam(request, "count", Integer.class));
		itemCouponActivityBO.setReceiveCount(RequestUtil.getParam(request, "receiveCount", Integer.class));
		itemCouponActivityBO.setUseCount(RequestUtil.getParam(request, "useCount", Integer.class));
		itemCouponActivityBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemCouponActivityBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		if (RequestUtil.existParam(request, "itemIds")) {
			String[] itemIds = request.getParameterValues("itemIds");
			itemCouponActivityBO.setFeature("itemIds", itemIds);
		}
		if (RequestUtil.existParam(request, "categoryIds")) {
			String[] categoryIds = request.getParameterValues("categoryIds");
			itemCouponActivityBO.setFeature("categoryIds", categoryIds);
		}
		PagerUtil.initPage(request, itemCouponActivityBO);
		return itemCouponActivityBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemCouponActivityManager;
	}

	public void doCreateCoupon(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Integer count = RequestUtil.getParam(request, "count", Integer.class, 100);
			Long activityId = RequestUtil.getParam(request, "activityId", Long.class);
			if (activityId == null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "参数异常！");
				return;
			}
			ItemCouponActivityBO activity = itemCouponActivityManager.get(activityId);
			if (activity == null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "活动不存在！");
				return;
			}
			if (!CouponActivityStatus.Wait.getCode().equals(activity.getStatus())) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "状态不是未开始！");
				return;
			}
			ItemCouponBO existCountQuery = new ItemCouponBO();
			existCountQuery.setSaasId(activity.getSaasId());
			existCountQuery.setActivityId(activity.getId());
			Long existCount = itemCouponManager.count(existCountQuery);
			Long lastCount = activity.getCount() - existCount;
			if (lastCount <= 0L) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "所有优惠券已生成，不能生成更多优惠券！");
				return;
			}
			for (int i = 0; i < (lastCount > count ? count : lastCount); i++) {
				ItemCouponBO coupon = new ItemCouponBO();
				coupon.setId(itemCouponManager.getId());
				coupon.setSaasId(activity.getSaasId());
				coupon.setShopId(activity.getShopId());
				coupon.setActivityId(activity.getId());
				coupon.setStatus(CouponStatus.WaitReceive.getCode());
				itemCouponManager.insert(coupon);
			}
			request.setAttribute("result", true);
			request.setAttribute("msg", "生成成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doReceive(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long activityId = RequestUtil.getParam(request, "activityId", Long.class);
			if (activityId == null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "参数异常！");
				return;
			}
			ItemCouponActivityBO activity = itemCouponActivityManager.get(activityId);
			if (activity == null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "活动不存在！");
				return;
			}
			if (!CouponActivityStatus.Opening.getCode().equals(activity.getStatus())) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "状态不是进行中！");
				return;
			}
			ItemCouponBO oneQuery = new ItemCouponBO();
			oneQuery.setSaasId(activity.getSaasId());
			oneQuery.setActivityId(activity.getId());
			oneQuery.setStatus(CouponStatus.WaitReceive.getCode());
			ItemCouponBO coupon = itemCouponManager.selectOne(oneQuery);
			if (coupon == null) {
				doCreateCoupon(request, response);
			}
			coupon = itemCouponManager.selectOne(oneQuery);
			if (coupon == null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "优惠券已被领完！");
				return;
			}
			coupon.setUserId(SessionClient.getLoginUserId(request));
			coupon.setReceiveTime(new Date());
			coupon.setReceiveMethod("");
			coupon.setStatus(CouponStatus.WaitUse.getCode());
			itemCouponManager.update(coupon);
			request.setAttribute("result", true);
			request.setAttribute("msg", "领取成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doGiveQway(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long couponId = RequestUtil.getParam(request, "couponId", Long.class);
			Long giveUserId = RequestUtil.getParam(request, "giveUserId", Long.class);
			ItemCouponBO coupon = itemCouponManager.get(couponId);
			if (coupon == null || coupon.getUserId() != SessionClient.getLoginUserId(request)) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "优惠券不存在！");
				return;
			}
			if (!CouponStatus.WaitUse.getCode().equals(coupon.getStatus())) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "优惠券状态异常！");
				return;
			}
			coupon.setUserId(giveUserId);
			coupon.setStatus(CouponStatus.WaitUse.getCode());
			itemCouponManager.update(coupon);
			request.setAttribute("result", true);
			request.setAttribute("msg", "赠送成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
