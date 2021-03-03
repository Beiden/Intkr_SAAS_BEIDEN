package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemBrandBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemBrandManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 品牌
 * 
 * @table item_brand
 * 
 * @author Beiden
 * @date 2020-11-11 22:33:08
 * @version 1.0
 */
public class ItemBrandAction extends BaseAction<ItemBrandBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBrandManager itemBrandManager = IOC.get(ItemBrandManager.class);

	public ItemBrandBO getBO(HttpServletRequest request) {
		ItemBrandBO itemBrandBO = getParameter(request);
		return itemBrandBO;
	}

	public static ItemBrandBO getParameter(HttpServletRequest request) {
		ItemBrandBO itemBrandBO = new ItemBrandBO();
		itemBrandBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemBrandBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemBrandBO.setCategoryId(RequestUtil.getParam(request, "categoryId", Long.class));
		itemBrandBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemBrandBO.setNote(RequestUtil.getParam(request, "note", String.class));
		itemBrandBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemBrandBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemBrandBO);
		return itemBrandBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemBrandManager;
	}

}
