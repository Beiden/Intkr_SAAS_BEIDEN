package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyValueBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSkuPropertyValueManager;
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
public class SkuPropertyValueAction extends BaseAction<ItemSkuPropertyValueBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuPropertyValueManager itemSkuPropertyValueManager = IOC.get("ItemSkuPropertyValueManager");

	public ItemSkuPropertyValueBO getBO(HttpServletRequest request) {
		ItemSkuPropertyValueBO shopSkuPropertyValueBO = getParameter(request);
		return shopSkuPropertyValueBO;
	}

	public static ItemSkuPropertyValueBO getParameter(HttpServletRequest request) {
		ItemSkuPropertyValueBO shopSkuPropertyValueBO = new ItemSkuPropertyValueBO();
		shopSkuPropertyValueBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		shopSkuPropertyValueBO.setSkuPropertyId(RequestUtil.getParam(request, "skuPropertyId", Long.class));
		shopSkuPropertyValueBO.setImgId(RequestUtil.getParam(request, "imgId", Long.class));
		shopSkuPropertyValueBO.setName(RequestUtil.getParam(request, "name"));
		shopSkuPropertyValueBO.setColor(RequestUtil.getParam(request, "color"));
		shopSkuPropertyValueBO.setFeature(RequestUtil.getParam(request, "feature"));
		shopSkuPropertyValueBO.setValueId(RequestUtil.getParam(request, "valueId", Long.class));
		PagerUtil.initPage(request, shopSkuPropertyValueBO);
		return shopSkuPropertyValueBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSkuPropertyValueManager;
	}

}
