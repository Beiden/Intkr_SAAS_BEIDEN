package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSkuPropertyManager;
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
public class SkuPropertyAction extends BaseAction<ItemSkuPropertyBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuPropertyManager itemSkuPropertyManager = IOC.get("ItemSkuPropertyManager");

	public ItemSkuPropertyBO getBO(HttpServletRequest request) {
		ItemSkuPropertyBO shopSkuPropertyBO = getParameter(request);
		return shopSkuPropertyBO;
	}

	public static ItemSkuPropertyBO getParameter(HttpServletRequest request) {
		ItemSkuPropertyBO shopSkuPropertyBO = new ItemSkuPropertyBO();
		shopSkuPropertyBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopSkuPropertyBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopSkuPropertyBO.setItemId(RequestUtil.getParam(request, "itemId" , Long.class));
		shopSkuPropertyBO.setPropertyId(RequestUtil.getParam(request, "propertyId" , Long.class));
		shopSkuPropertyBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		shopSkuPropertyBO.setFeature(RequestUtil.getParam(request, "feature" , String.class));
		shopSkuPropertyBO.setNote(RequestUtil.getParam(request, "note" , String.class));
		PagerUtil.initPage(request, shopSkuPropertyBO);
		return shopSkuPropertyBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSkuPropertyManager;
	}

}
