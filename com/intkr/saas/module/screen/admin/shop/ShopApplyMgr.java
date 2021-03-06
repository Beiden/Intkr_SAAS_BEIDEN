package com.intkr.saas.module.screen.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopApplyBO;
import com.intkr.saas.domain.type.shop.ShopStatus;
import com.intkr.saas.domain.type.shop.ShopType;
import com.intkr.saas.manager.shop.ShopApplyManager;
import com.intkr.saas.module.action.shop.ShopApplyAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 店铺申请
 * 
 * @table shop_apply
 * 
 * @author Beiden
 * @date 2020-11-10 22:09:23
 * @version 1.0
 */
public class ShopApplyMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopApplyManager manager = IOC.get(ShopApplyManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopApplyBO query = ShopApplyAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("ShopStatus", ShopStatus.Error);
		request.setAttribute("ShopType", ShopType.Error);
	}

}
