package com.intkr.saas.module.screen.admin.shop.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.shop.ShopAccuBO;
import com.intkr.saas.manager.shop.ShopAccuManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ShopAccuAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopAccuManager manager = IOC.get("ShopAccuManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopAccuId = RequestUtil.getParam(request, "shopAccuId");
		ShopAccuBO shopAccu = manager.get(shopAccuId);
		request.setAttribute("shopAccu", shopAccu);
		request.setAttribute("addId", manager.getId());
	}

}
