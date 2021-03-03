package com.intkr.saas.module.action.sms;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemBrandRecommendBO;
import com.intkr.saas.manager.sms.ItemBrandRecommendManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 品牌推荐
 * 
 * @table item_brand_recommend
 * 
 * @author Beiden
 * @date 2020-11-11 22:44:12
 * @version 1.0
 */
public class ItemBrandRecommendAction extends BaseAction<ItemBrandRecommendBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBrandRecommendManager itemBrandRecommendManager = IOC.get(ItemBrandRecommendManager.class);

	public ItemBrandRecommendBO getBO(HttpServletRequest request) {
		ItemBrandRecommendBO itemBrandRecommendBO = getParameter(request);
		return itemBrandRecommendBO;
	}

	public static ItemBrandRecommendBO getParameter(HttpServletRequest request) {
		ItemBrandRecommendBO itemBrandRecommendBO = new ItemBrandRecommendBO();
		itemBrandRecommendBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemBrandRecommendBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemBrandRecommendBO.setBrandId(RequestUtil.getParam(request, "brandId", Long.class));
		itemBrandRecommendBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemBrandRecommendBO.setNote(RequestUtil.getParam(request, "note", String.class));
		itemBrandRecommendBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemBrandRecommendBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemBrandRecommendBO);
		return itemBrandRecommendBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemBrandRecommendManager;
	}

}
