package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemSkuValueBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSkuValueManager;
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
public class SkuValueAction extends BaseAction<ItemSkuValueBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuValueManager itemSkuValueManager = IOC.get("ItemSkuValueManager");

	public ItemSkuValueBO getBO(HttpServletRequest request) {
		ItemSkuValueBO shopSkuValueBO = getParameter(request);
		return shopSkuValueBO;
	}

	public static ItemSkuValueBO getParameter(HttpServletRequest request) {
		ItemSkuValueBO shopSkuValueBO = new ItemSkuValueBO();
		shopSkuValueBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		shopSkuValueBO.setSkuId(RequestUtil.getParam(request, "skuId" , Long.class));
		shopSkuValueBO.setValueId(RequestUtil.getParam(request, "valueId" , Long.class));
		PagerUtil.initPage(request, shopSkuValueBO);
		return shopSkuValueBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSkuValueManager;
	}

}
