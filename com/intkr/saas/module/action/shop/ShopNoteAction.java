package com.intkr.saas.module.action.shop;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopNoteBO;
import com.intkr.saas.manager.shop.ShopNoteManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 店铺公告
 * 
 * @table shop_note
 * 
 * @author Beiden
 * @date 2020-10-30 18:05:17
 * @version 1.0
 */
public class ShopNoteAction extends BaseAction<ShopNoteBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopNoteManager shopNoteManager = IOC.get(ShopNoteManager.class);

	public ShopNoteBO getBO(HttpServletRequest request) {
		ShopNoteBO shopNoteBO = getParameter(request);
		return shopNoteBO;
	}

	public static ShopNoteBO getParameter(HttpServletRequest request) {
		ShopNoteBO shopNoteBO = new ShopNoteBO();
		shopNoteBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopNoteBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopNoteBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		shopNoteBO.setType(RequestUtil.getParam(request, "type", String.class));
		shopNoteBO.setTitle(RequestUtil.getParam(request, "title", String.class));
		shopNoteBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		shopNoteBO.setContent(RequestUtil.getParam(request, "content", String.class));
		PagerUtil.initPage(request, shopNoteBO);
		return shopNoteBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopNoteManager;
	}

}
