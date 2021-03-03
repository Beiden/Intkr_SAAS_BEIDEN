package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateValueBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSpuTemplateValueManager;
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
public class SpuTemplateValueAction extends BaseAction<ItemSpuTemplateValueBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuTemplateValueManager itemSpuTemplateValueManager = IOC.get("ItemSpuTemplateValueManager");

	public ItemSpuTemplateValueBO getBO(HttpServletRequest request) {
		ItemSpuTemplateValueBO shopSpuTemplateValueBO = getParameter(request);
		return shopSpuTemplateValueBO;
	}

	public static ItemSpuTemplateValueBO getParameter(HttpServletRequest request) {
		ItemSpuTemplateValueBO shopSpuTemplateValueBO = new ItemSpuTemplateValueBO();
		shopSpuTemplateValueBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		shopSpuTemplateValueBO.setSpuTemplateId(RequestUtil.getParam(request, "spuTemplateId" , Long.class));
		shopSpuTemplateValueBO.setValueId(RequestUtil.getParam(request, "valueId" , Long.class));
		PagerUtil.initPage(request, shopSpuTemplateValueBO);
		return shopSpuTemplateValueBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSpuTemplateValueManager;
	}

}
