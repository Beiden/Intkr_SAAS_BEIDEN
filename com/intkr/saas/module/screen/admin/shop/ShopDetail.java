package com.intkr.saas.module.screen.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.type.shop.ShopStatus;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-10 下午8:44:02
 * @version 1.0
 */
public class ShopDetail {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopManager shopManager = IOC.get("ShopManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			ShopBO favoriteBO = shopManager.get(id);
			request.setAttribute("dto", favoriteBO);
		} else {
			request.setAttribute("addId", shopManager.getId());
		}
		request.setAttribute("ShopStatus", ShopStatus.Error);
	}

}
