package com.intkr.saas.module.action.item;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemTagRelatedBO;
import com.intkr.saas.manager.item.ItemTagRelatedManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 商品标签
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:15
 * @version 1.0
 */
public class ItemTagRelatedAction extends BaseAction<ItemTagRelatedBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemTagRelatedManager itemTagRelatedManager = IOC.get(ItemTagRelatedManager.class);

	public ItemTagRelatedBO getBO(HttpServletRequest request) {
		ItemTagRelatedBO itemTagRelatedBO = getParameter(request);
		return itemTagRelatedBO;
	}

	public static ItemTagRelatedBO getParameter(HttpServletRequest request) {
		ItemTagRelatedBO itemTagRelatedBO = new ItemTagRelatedBO();
		itemTagRelatedBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemTagRelatedBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemTagRelatedBO.setTagId(RequestUtil.getParam(request, "tagId", Long.class));
		itemTagRelatedBO.setRelatedId(RequestUtil.getParam(request, "relatedId", Long.class));
		PagerUtil.initPage(request, itemTagRelatedBO);
		return itemTagRelatedBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemTagRelatedManager;
	}

}
