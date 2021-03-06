package com.intkr.saas.module.screen.admin.shopping.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shopping.ShopCollectBO;
import com.intkr.saas.manager.shopping.ShopCollectManager;
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
public class ShopCollectAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopCollectManager manager = IOC.get(ShopCollectManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopCollectId = RequestUtil.getParam(request, "shopCollectId");
		ShopCollectBO shopCollect = manager.get(shopCollectId);
		request.setAttribute("shopCollect", shopCollect);
		request.setAttribute("addId", manager.getId());
	}

}
