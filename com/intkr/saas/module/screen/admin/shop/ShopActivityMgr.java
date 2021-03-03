package com.intkr.saas.module.screen.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.type.shop.ActivityStatus;
import com.intkr.saas.domain.type.shop.ActivityType;
import com.intkr.saas.manager.shop.ShopActivityManager;
import com.intkr.saas.module.action.shop.ShopActivityAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:21:31
 * @version 1.0
 */
public class ShopActivityMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopActivityManager activityManager = IOC.get(ShopActivityManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopActivityBO query = ShopActivityAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		activityManager.selectCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("ShopActivityStatus", ActivityStatus.Error);
		request.setAttribute("ShopActivityType", ActivityType.Error);
	}

}
