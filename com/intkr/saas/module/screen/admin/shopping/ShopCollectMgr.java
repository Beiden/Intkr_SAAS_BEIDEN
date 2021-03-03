package com.intkr.saas.module.screen.admin.shopping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.shopping.ShopCollectAction;
import com.intkr.saas.domain.bo.shopping.ShopCollectBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.shopping.ShopCollectManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class ShopCollectMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopCollectManager manager = IOC.get(ShopCollectManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopCollectBO query = ShopCollectAction.getParameter(request);
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = manager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
