package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSpuTemplateManager;
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
public class SpuTemplateAction extends BaseAction<ItemSpuTemplateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuTemplateManager itemSpuTemplateManager = IOC.get("ItemSpuTemplateManager");

	public ItemSpuTemplateBO getBO(HttpServletRequest request) {
		ItemSpuTemplateBO shopSpuTemplateBO = getParameter(request);
		return shopSpuTemplateBO;
	}

	public static ItemSpuTemplateBO getParameter(HttpServletRequest request) {
		ItemSpuTemplateBO shopSpuTemplateBO = new ItemSpuTemplateBO();
		shopSpuTemplateBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		shopSpuTemplateBO.setCategoryId(RequestUtil.getParam(request, "categoryId" , Long.class));
		shopSpuTemplateBO.setPropertyId(RequestUtil.getParam(request, "propertyId" , Long.class));
		PagerUtil.initPage(request, shopSpuTemplateBO);
		return shopSpuTemplateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSpuTemplateManager;
	}

}
