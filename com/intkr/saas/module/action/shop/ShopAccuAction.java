package com.intkr.saas.module.action.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopAccuBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.ShopAccuManager;
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
public class ShopAccuAction extends BaseAction<ShopAccuBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopAccuManager shopAccuManager = IOC.get("ShopAccuManager");

	public ShopAccuBO getBO(HttpServletRequest request) {
		ShopAccuBO shopAccuBO = getParameter(request);
		return shopAccuBO;
	}

	public static ShopAccuBO getParameter(HttpServletRequest request) {
		ShopAccuBO shopAccuBO = new ShopAccuBO();
		shopAccuBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopAccuBO.setType(RequestUtil.getParam(request, "type", String.class));
		shopAccuBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopAccuBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		shopAccuBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopAccuBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopAccuBO.setStatus(RequestUtil.getParam(request, "status"));
		shopAccuBO.setTitle(RequestUtil.getParam(request, "title"));
		shopAccuBO.setContent(RequestUtil.getParam(request, "content"));
		shopAccuBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopAccuBO);
		return shopAccuBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopAccuManager;
	}

	public void doAccu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ShopAccuBO bo = getBO(request);
			bo.setId(shopAccuManager.getId());
			long id = shopAccuManager.insert(bo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", bo.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "举报成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
