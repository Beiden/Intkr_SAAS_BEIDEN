package com.intkr.saas.module.screen.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.type.shop.ActivityStatus;
import com.intkr.saas.domain.type.shop.ActivityType;
import com.intkr.saas.manager.shop.ShopActivityManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:22:39
 * @version 1.0
 */
public class ShopActivityMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopActivityManager activityManager = IOC.get(ShopActivityManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = request.getParameter("id");
			ShopActivityBO activityBO = activityManager.get(id);
			request.setAttribute("dto", activityBO);
		} else {
			request.setAttribute("addId", activityManager.getId());
		}
		request.setAttribute("ShopActivityStatus", ActivityStatus.Error);
		request.setAttribute("ShopActivityType", ActivityType.Error);
	}

}
