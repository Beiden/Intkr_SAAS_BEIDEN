package com.intkr.saas.module.toolbox.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shopping.ItemCollectBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.shopping.ItemCollectManager;
import com.intkr.saas.module.action.shopping.ItemCollectAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:59:08
 * @version 1.0
 */
public class ItemCollectDS extends BaseToolBox {

	private ItemCollectManager itemCollectManager = IOC.get("ItemCollectManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	/**
	 * 查询列表
	 */
	public ItemCollectBO select(HttpServletRequest request, HttpServletResponse response) {
		if (!SessionClient.isLogin(request)) {
			return null;
		}
		try {
			ItemCollectBO query = ItemCollectAction.getParameter(request);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			itemCollectManager.selectAndCount(query);
			itemManager.fill(query.getDatas());
			for (Object obj : query.getDatas()) {
				ItemCollectBO itemCollect = (ItemCollectBO) obj;
				ItemBO item = itemCollect.getItem();
				imgManager.fill(item);
			}
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
			ItemCollectBO query = new ItemCollectBO();
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			Long count = itemCollectManager.count(query);
			return count;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

}
