package com.intkr.saas.module.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shopping.ShopCollectBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shopping.ShopCollectManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 店铺收藏
 * 
 * @table shop_collect
 * 
 * @author Beiden
 * @date 2020-11-14 17:59:25
 * @version 1.0
 */
public class ShopCollectAction extends BaseAction<ShopCollectBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopCollectManager shopCollectManager = IOC.get(ShopCollectManager.class);

	public ShopCollectBO getBO(HttpServletRequest request) {
		ShopCollectBO shopCollectBO = getParameter(request);
		return shopCollectBO;
	}

	public static ShopCollectBO getParameter(HttpServletRequest request) {
		ShopCollectBO shopCollectBO = new ShopCollectBO();
		shopCollectBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopCollectBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopCollectBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopCollectBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopCollectBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, shopCollectBO);
		return shopCollectBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopCollectManager;
	}

	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long shopId = RequestUtil.getParam(request, "shopId", Long.class);
		ShopCollectBO query = new ShopCollectBO();
		query.setUserId(SessionClient.getLoginUserId(request));
		query.setShopId(shopId);
		if (shopCollectManager.count(query) == 0) {
			ShopCollectBO footprint = new ShopCollectBO();
			footprint.setId(shopCollectManager.getId());
			footprint.setSaasId(SessionClient.getSaasId(request));
			footprint.setUserId(SessionClient.getLoginUserId(request));
			footprint.setShopId(shopId);
			shopCollectManager.insert(footprint);
			request.setAttribute("result", true);
			request.setAttribute("msg", "收藏成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "你已经收藏过了！");
		}
	}

	public void doRemove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		ShopCollectBO itemCollect = shopCollectManager.get(id);
		if (itemCollect != null) {
			shopCollectManager.delete(itemCollect.getId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "删除成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常！");
		}
	}

}
