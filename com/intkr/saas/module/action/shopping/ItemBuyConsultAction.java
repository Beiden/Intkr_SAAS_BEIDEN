package com.intkr.saas.module.action.shopping;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shopping.ItemBuyConsultBO;
import com.intkr.saas.engine.CheckCodeEngine;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shopping.ItemBuyConsultManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-12 上午11:24:16
 * @version 1.0
 */
public class ItemBuyConsultAction extends BaseAction<ItemBuyConsultBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBuyConsultManager itemBuyConsultManager = IOC.get("ItemBuyConsultManager");

	/**
	 * 商品购买咨询
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void buyConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!CheckCodeEngine.check(request, "checkCode")) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "验证码错误，请点击验证码刷新，重新输入！");
			return;
		}
		ItemBuyConsultBO itemBuyConsult = getParameter(request);
		itemBuyConsult.setId(itemBuyConsultManager.getId());
		itemBuyConsult.setIsReply(2);
		if (SessionClient.isLogin(request)) {
			itemBuyConsult.setUserId(SessionClient.getLoginUserId(request));
		}
		itemBuyConsultManager.insert(itemBuyConsult);
		request.setAttribute("result", true);
		request.setAttribute("msg", "发布咨询成功");
	}

	public ItemBuyConsultBO getBO(HttpServletRequest request) {
		ItemBuyConsultBO itemBuyConsultBO = getParameter(request);
		return itemBuyConsultBO;
	}

	public static ItemBuyConsultBO getParameter(HttpServletRequest request) {
		ItemBuyConsultBO itemBuyConsultBO = new ItemBuyConsultBO();
		itemBuyConsultBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemBuyConsultBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemBuyConsultBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemBuyConsultBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		itemBuyConsultBO.setType(RequestUtil.getParam(request, "type"));
		itemBuyConsultBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		itemBuyConsultBO.setContent(RequestUtil.getParam(request, "content"));
		itemBuyConsultBO.setIsReply(RequestUtil.getParam(request, "isReply", Integer.class));
		itemBuyConsultBO.setReply(RequestUtil.getParam(request, "reply"));
		PagerUtil.initPage(request, itemBuyConsultBO);
		return itemBuyConsultBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemBuyConsultManager;
	}

	public void doConsult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ItemBuyConsultBO bo = getBO(request);
			bo.setId(itemBuyConsultManager.getId());
			long id = itemBuyConsultManager.insert(bo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", bo.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "咨询成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
