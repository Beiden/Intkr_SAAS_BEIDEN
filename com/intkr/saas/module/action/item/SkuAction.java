package com.intkr.saas.module.action.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.item.ItemSkuPropertyBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSkuManager;
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
public class SkuAction extends BaseAction<ItemSkuBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	private ItemSkuPropertyManager itemSkuPropertyManager = IOC.get("ItemSkuPropertyManager");

	public ItemSkuBO getBO(HttpServletRequest request) {
		ItemSkuBO shopSkuBO = getParameter(request);
		return shopSkuBO;
	}

	public static ItemSkuBO getParameter(HttpServletRequest request) {
		ItemSkuBO shopSkuBO = new ItemSkuBO();
		shopSkuBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopSkuBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopSkuBO.setStatus(RequestUtil.getParam(request, "status"));
		shopSkuBO.setIsDefault(RequestUtil.getParam(request, "isDefault", Integer.class));
		shopSkuBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		shopSkuBO.setImgId(RequestUtil.getParam(request, "imgId", Long.class));
		shopSkuBO.setColor(RequestUtil.getParam(request, "color"));
		shopSkuBO.setPrice(RequestUtil.getParam(request, "price", Long.class));
		shopSkuBO.setInventory(RequestUtil.getParam(request, "inventory", Integer.class));
		shopSkuBO.setName(RequestUtil.getParam(request, "name"));
		shopSkuBO.setFeature(RequestUtil.getParam(request, "feature"));
		shopSkuBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		shopSkuBO.setNote(RequestUtil.getParam(request, "note"));
		PagerUtil.initPage(request, shopSkuBO);
		return shopSkuBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemSkuManager;
	}

	public void doGetFull(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			if (RequestUtil.existParam(request, "skuId")) {
				Long skuId = RequestUtil.getParam(request, "skuId", Long.class);
				ItemSkuBO sku = itemSkuManager.getFull(skuId);
				request.setAttribute("data", sku);
			} else {
				Long itemId = RequestUtil.getParam(request, "itemId", Long.class);
				List<ItemSkuBO> list = itemSkuManager.getFullByItemId(itemId);
				List<ItemSkuPropertyBO> skuPropertyList = itemSkuPropertyManager.getFullByItemId(itemId);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("propertys", skuPropertyList);
				data.put("skus", list);
				request.setAttribute("data", data);
			}
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdateInventory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long skuId = RequestUtil.getParam(request, "id", Long.class);
			Integer inventory = RequestUtil.getParam(request, "inventory", Integer.class);
			ItemSkuBO sku = itemSkuManager.getFull(skuId);
			boolean result = itemSkuManager.updateInventory(skuId, inventory);
			request.setAttribute("result", true);
			request.setAttribute("data", result);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
