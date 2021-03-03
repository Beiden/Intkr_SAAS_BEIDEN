package com.intkr.saas.module.action.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopComplaintBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ShopComplaintManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class ShopComplaintAction extends BaseAction<ShopComplaintBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopComplaintManager shopComplaintManager = IOC.get("ShopComplaintManager");

	public ShopComplaintBO getBO(HttpServletRequest request) {
		ShopComplaintBO shopComplaintBO = getParameter(request);
		return shopComplaintBO;
	}

	public static ShopComplaintBO getParameter(HttpServletRequest request) {
		ShopComplaintBO shopComplaintBO = new ShopComplaintBO();
		shopComplaintBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopComplaintBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopComplaintBO.setType(RequestUtil.getParam(request, "type", String.class));
		shopComplaintBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopComplaintBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		shopComplaintBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopComplaintBO.setStatus(RequestUtil.getParam(request, "status"));
		shopComplaintBO.setTitle(RequestUtil.getParam(request, "title"));
		shopComplaintBO.setContent(RequestUtil.getParam(request, "content"));
		shopComplaintBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopComplaintBO);
		return shopComplaintBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopComplaintManager;
	}

	public void doComplaint(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ShopComplaintBO bo = getBO(request);
			bo.setId(shopComplaintManager.getId());
			long id = shopComplaintManager.insert(bo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", bo.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "投诉成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
