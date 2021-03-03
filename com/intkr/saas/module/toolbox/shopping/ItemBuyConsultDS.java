package com.intkr.saas.module.toolbox.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shopping.ItemBuyConsultBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.shopping.ItemBuyConsultManager;
import com.intkr.saas.module.action.shopping.ItemBuyConsultAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:59:08
 * @version 1.0
 */
public class ItemBuyConsultDS extends BaseToolBox {

	private ItemBuyConsultManager itemBuyConsultManager = IOC.get("ItemBuyConsultManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	/**
	 * 查询列表
	 */
	public ItemBuyConsultBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ItemBuyConsultBO query = ItemBuyConsultAction.getParameter(request);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			itemBuyConsultManager.selectAndCount(query);
			itemManager.fill(query.getDatas());
			for (Object obj : query.getDatas()) {
				ItemBuyConsultBO footprint = (ItemBuyConsultBO) obj;
				ItemBO item = footprint.getItem();
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
		try {
			ItemBuyConsultBO query = new ItemBuyConsultBO();
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			Long count = itemBuyConsultManager.count(query);
			return count;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

}
