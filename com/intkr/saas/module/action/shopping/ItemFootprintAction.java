package com.intkr.saas.module.action.shopping;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shopping.ItemFootprintBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shopping.ItemFootprintManager;
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
public class ItemFootprintAction extends BaseAction<ItemFootprintBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFootprintManager shopFootprintManager = IOC.get("ShopFootprintManager");

	public ItemFootprintBO getBO(HttpServletRequest request) {
		ItemFootprintBO shopFootprintBO = getParameter(request);
		return shopFootprintBO;
	}

	public static ItemFootprintBO getParameter(HttpServletRequest request) {
		ItemFootprintBO shopFootprintBO = new ItemFootprintBO();
		shopFootprintBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopFootprintBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopFootprintBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopFootprintBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		PagerUtil.initPage(request, shopFootprintBO);
		return shopFootprintBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopFootprintManager;
	}

	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!SessionClient.isLogin(request)) {

			return;
		}
		Long itemId = RequestUtil.getParam(request, "itemId", Long.class);
		Date startTime = null;
		Date endTime = new Date();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		startTime = c.getTime();
		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		endTime = c.getTime();
		ItemFootprintBO query = new ItemFootprintBO();
		query.setUserId(SessionClient.getLoginUserId(request));
		query.setItemId(itemId);
		query.setQuery("startTime", startTime);
		query.setQuery("endTime", endTime);
		if (shopFootprintManager.count(query) == 0) {
			ItemFootprintBO footprint = new ItemFootprintBO();
			footprint.setId(shopFootprintManager.getId());
			footprint.setSaasId(SessionClient.getSaasId(request));
			footprint.setUserId(SessionClient.getLoginUserId(request));
			footprint.setItemId(itemId);
			shopFootprintManager.insert(footprint);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "操作成功");
	}

}
