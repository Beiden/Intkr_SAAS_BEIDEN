package com.intkr.saas.module.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shopping.ItemCollectBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shopping.ItemCollectManager;
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
public class ItemCollectAction extends BaseAction<ItemCollectBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCollectManager itemCollectManager = IOC.get("ItemCollectManager");

	public ItemCollectBO getBO(HttpServletRequest request) {
		ItemCollectBO shopItemCollectBO = getParameter(request);
		return shopItemCollectBO;
	}

	public static ItemCollectBO getParameter(HttpServletRequest request) {
		ItemCollectBO shopItemCollectBO = new ItemCollectBO();
		shopItemCollectBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopItemCollectBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopItemCollectBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopItemCollectBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		PagerUtil.initPage(request, shopItemCollectBO);
		return shopItemCollectBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemCollectManager;
	}

	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long itemId = RequestUtil.getParam(request, "itemId", Long.class);
		ItemCollectBO query = new ItemCollectBO();
		query.setUserId(SessionClient.getLoginUserId(request));
		query.setItemId(itemId);
		if (itemCollectManager.count(query) == 0) {
			ItemCollectBO footprint = new ItemCollectBO();
			footprint.setId(itemCollectManager.getId());
			footprint.setSaasId(SessionClient.getSaasId(request));
			footprint.setUserId(SessionClient.getLoginUserId(request));
			footprint.setItemId(itemId);
			itemCollectManager.insert(footprint);
			request.setAttribute("result", true);
			request.setAttribute("msg", "收藏成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "你已经收藏过了！");
		}
	}

	public void doRemove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		ItemCollectBO itemCollect = itemCollectManager.get(id);
		if (itemCollect != null) {
			itemCollectManager.delete(itemCollect.getId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "删除成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常！");
		}
	}

}
