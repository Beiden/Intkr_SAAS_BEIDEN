package com.intkr.saas.module.toolbox.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shopping.ItemFootprintBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.shopping.ItemFootprintManager;
import com.intkr.saas.module.action.shopping.ItemFootprintAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class ItemFootprintDS extends BaseToolBox {

	private ItemFootprintManager footprintManager = IOC.get("ShopFootprintManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ItemFootprintBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ItemFootprintBO query = ItemFootprintAction.getParameter(request);
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			footprintManager.selectAndCount(query);
			itemManager.fill(query.getDatas());
			for (Object obj : query.getDatas()) {
				ItemFootprintBO footprint = (ItemFootprintBO) obj;
				ItemBO item = footprint.getItem();
				imgManager.fill(item);
			}
			if (query.get_count() > query.get_pageSize() * 3) {
				query.set_count(query.get_pageSize() * 3);
			}
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

}
