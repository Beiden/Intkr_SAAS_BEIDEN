package com.intkr.saas.module.toolbox.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shopping.ShopCollectBO;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.shopping.ShopCollectManager;
import com.intkr.saas.module.action.shopping.ShopCollectAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:59:08
 * @version 1.0
 */
public class ShopCollectDS extends BaseToolBox {

	private ShopCollectManager shopCollectManager = IOC.get("ShopCollectManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	/**
	 * 查询列表
	 */
	public ShopCollectBO select(HttpServletRequest request, HttpServletResponse response) {
		if (!SessionClient.isLogin(request)) {
			return null;
		}
		try {
			ShopCollectBO query = ShopCollectAction.getParameter(request);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			shopCollectManager.selectAndCount(query);
			shopManager.fill(query.getDatas());
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 查询列表
	 */
	public Long count(HttpServletRequest request, HttpServletResponse response) {
		if (!SessionClient.isLogin(request)) {
			return null;
		}
		try {
			ShopCollectBO query = new ShopCollectBO();
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			Long count = shopCollectManager.count(query);
			return count;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

}
