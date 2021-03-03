package com.intkr.saas.module.action.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ShopActivityManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:13:29
 * @version 1.0
 */
public class ShopActivityAction extends BaseAction<ShopActivityBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopActivityManager activityManager = IOC.get(ShopActivityManager.class);

	/**
	 * 发起活动
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void launch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShopActivityBO bo = getParameter(request);
		activityManager.insert(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "发起活动成功!");
	}

	/**
	 * 更新活动
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ShopActivityBO bo = getParameter(request);
		activityManager.update(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新活动成功");
	}

	public void deleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String deleteId = request.getParameter("deleteId");
		deleteOne(request, deleteId);
	}

	private void deleteOne(HttpServletRequest request, String deleteId) {
		if (deleteId != null && !"".equals(deleteId)) {
			ShopActivityBO bo = activityManager.get(deleteId);
			if (bo == null || !bo.getUserId().equals(SessionClient.getLoginUserId(request))) {
				return;
			}
			activityManager.delete(deleteId);
			request.setAttribute("result", true);
			request.setAttribute("msg", "删除活动成功");
		}
	}

	public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (RequestUtil.existParam(request, "deleteIds")) {
			String deleteIds = request.getParameter("deleteIds");
			String[] ids = deleteIds.split(",");
			for (String deleteId : ids) {
				deleteOne(request, deleteId);
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "指删除成功");
	}

	public static ShopActivityBO getParameter(HttpServletRequest request) {
		ShopActivityBO activityBO = new ShopActivityBO();
		activityBO.setId(RequestUtil.getParam(request, "id", Long.class));
		activityBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		activityBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		activityBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		activityBO.setType(request.getParameter("type"));
		activityBO.setName(request.getParameter("name"));
		activityBO.setStatus(request.getParameter("status"));
		activityBO.setProvince(request.getParameter("province"));
		activityBO.setCity(request.getParameter("city"));
		activityBO.setArea(request.getParameter("area"));
		activityBO.setAddressDetail(request.getParameter("addressDetail"));
		activityBO.setCoverImgId(RequestUtil.getParam(request, "coverImgId", Long.class));
		activityBO.setContent(request.getParameter("content"));
		activityBO.setStartTime(DateUtil.parse(request.getParameter("startTime")));
		activityBO.setEndTime(DateUtil.parse(request.getParameter("endTime")));
		PagerUtil.initPage(request, activityBO);
		return activityBO;
	}

	public ShopActivityBO getBO(HttpServletRequest request) {
		ShopActivityBO activityBO = getParameter(request);
		return activityBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return activityManager;
	}
}
