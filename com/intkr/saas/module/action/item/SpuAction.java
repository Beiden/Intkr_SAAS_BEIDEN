package com.intkr.saas.module.action.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.distributed.session.SessionClientDistImpl;
import com.intkr.saas.domain.bo.item.ItemSpuBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSpuManager;
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
public class SpuAction extends BaseAction<ItemSpuBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuManager itemSpuManager = IOC.get("ItemSpuManager");

	public ItemSpuBO getBO(HttpServletRequest request) {
		ItemSpuBO shopSpuBO = getParameter(request);
		return shopSpuBO;
	}

	public static ItemSpuBO getParameter(HttpServletRequest request) {
		ItemSpuBO shopSpuBO = new ItemSpuBO();
		shopSpuBO.setSaasId(SessionClientDistImpl.getSaas(request).getId());
		shopSpuBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		shopSpuBO.setPropertyId(RequestUtil.getParam(request, "propertyId", Long.class));
		PagerUtil.initPage(request, shopSpuBO);
		return shopSpuBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSpuManager;
	}

	public void doGetFull(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long itemId = RequestUtil.getParam(request, "itemId", Long.class);
			List<ItemSpuBO> list = itemSpuManager.getFullByItemId(itemId);
			request.setAttribute("data", list);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
