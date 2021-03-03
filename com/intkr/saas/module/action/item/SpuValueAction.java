package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemSpuValueBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSpuValueManager;
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
public class SpuValueAction extends BaseAction<ItemSpuValueBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuValueManager itemSpuValueManager = IOC.get("ItemSpuValueManager");

	public ItemSpuValueBO getBO(HttpServletRequest request) {
		ItemSpuValueBO shopSpuValueBO = getParameter(request);
		return shopSpuValueBO;
	}

	public static ItemSpuValueBO getParameter(HttpServletRequest request) {
		ItemSpuValueBO shopSpuValueBO = new ItemSpuValueBO();
		shopSpuValueBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		shopSpuValueBO.setSpuId(RequestUtil.getParam(request, "spuId" , Long.class));
		shopSpuValueBO.setValueId(RequestUtil.getParam(request, "valueId" , Long.class));
		PagerUtil.initPage(request, shopSpuValueBO);
		return shopSpuValueBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSpuValueManager;
	}

}
