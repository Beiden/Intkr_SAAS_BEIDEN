package com.intkr.saas.module.action.item;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemFcategoryBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemFcategoryManager;
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
public class ItemFcategoryAction extends BaseAction<ItemFcategoryBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFcategoryManager itemCategoryManager = IOC.get("ItemFcategoryManager");

	public ItemFcategoryBO getBO(HttpServletRequest request) {
		ItemFcategoryBO shopItemCategoryBO = getParameter(request);
		return shopItemCategoryBO;
	}

	public static ItemFcategoryBO getParameter(HttpServletRequest request) {
		ItemFcategoryBO shopItemCategoryBO = new ItemFcategoryBO();
		shopItemCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopItemCategoryBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopItemCategoryBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		shopItemCategoryBO.setStatus(RequestUtil.getParam(request, "status"));
		shopItemCategoryBO.setName(RequestUtil.getParam(request, "name"));
		shopItemCategoryBO.setUrl(RequestUtil.getParam(request, "url"));
		shopItemCategoryBO.setIbcIds(RequestUtil.getParam(request, "ibcIds"));
		shopItemCategoryBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		shopItemCategoryBO.setNote(RequestUtil.getParam(request, "note"));
		shopItemCategoryBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopItemCategoryBO);
		return shopItemCategoryBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemCategoryManager;
	}

	public void doBang(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long fcategoryId = RequestUtil.getParam(request, "fcategoryId", Long.class);
			Long categoryId = RequestUtil.getParam(request, "categoryId", Long.class);
			ItemFcategoryBO category = itemCategoryManager.get(fcategoryId);
			category.addIbcId(categoryId);
			itemCategoryManager.update(category);
			request.setAttribute("data", category);
			request.setAttribute("result", true);
			request.setAttribute("msg", "绑定成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUnbang(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long fcategoryId = RequestUtil.getParam(request, "fcategoryId", Long.class);
			Long categoryId = RequestUtil.getParam(request, "categoryId", Long.class);
			ItemFcategoryBO category = itemCategoryManager.get(fcategoryId);
			category.removeIbcId(categoryId);
			itemCategoryManager.update(category);
			request.setAttribute("data", category);
			request.setAttribute("result", true);
			request.setAttribute("msg", "解绑成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ItemFcategoryBO bo = getBO(request);
			if (bo.getSort() == null) {
				bo.setSort(bo.getId().doubleValue());
			}
			long id = itemCategoryManager.insert(bo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", bo.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
