package com.intkr.saas.module.action.sms;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemRecommendBO;
import com.intkr.saas.manager.sms.ItemRecommendManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 商品推荐
 * 
 * @table item_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 23:10:14
 * @version 1.0
 */
public class ItemRecommendAction extends BaseAction<ItemRecommendBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemRecommendManager itemRecommendManager = IOC.get(ItemRecommendManager.class);

	public ItemRecommendBO getBO(HttpServletRequest request) {
		ItemRecommendBO itemRecommendBO = getParameter(request);
		return itemRecommendBO;
	}

	public static ItemRecommendBO getParameter(HttpServletRequest request) {
		ItemRecommendBO itemRecommendBO = new ItemRecommendBO();
		itemRecommendBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemRecommendBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemRecommendBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemRecommendBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		itemRecommendBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemRecommendBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemRecommendBO.setNote(RequestUtil.getParam(request, "note", String.class));
		itemRecommendBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemRecommendBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemRecommendBO);
		return itemRecommendBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemRecommendManager;
	}

}
