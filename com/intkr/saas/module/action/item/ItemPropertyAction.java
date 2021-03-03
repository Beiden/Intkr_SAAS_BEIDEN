package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemPropertyManager;
import com.intkr.saas.manager.item.ItemPropertyValueManager;
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
public class ItemPropertyAction extends BaseAction<ItemPropertyBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemPropertyManager itemPropertyManager = IOC.get("ItemPropertyManager");

	private ItemPropertyValueManager itemPropertyValueManager = IOC.get("ItemPropertyValueManager");

	public ItemPropertyBO getBO(HttpServletRequest request) {
		ItemPropertyBO shopPropertyBO = getParameter(request);
		return shopPropertyBO;
	}

	public static ItemPropertyBO getParameter(HttpServletRequest request) {
		ItemPropertyBO shopPropertyBO = new ItemPropertyBO();
		shopPropertyBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopPropertyBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopPropertyBO.setName(RequestUtil.getParam(request, "name"));
		shopPropertyBO.setNote(RequestUtil.getParam(request, "note"));
		shopPropertyBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		shopPropertyBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopPropertyBO);
		return shopPropertyBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemPropertyManager;
	}

	public void doGetFull(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ItemPropertyBO property = itemPropertyManager.get(request.getParameter("id"));
			itemPropertyValueManager.fill(property);
			request.setAttribute("data", property);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// Long backendCategoryId = RequestUtil.getParam(request,
			// "backendCategoryId", Long.class);
			String propertyName = RequestUtil.getParam(request, "propertyName");
			ItemPropertyBO query = new ItemPropertyBO();
			query.setName(propertyName);
			ItemPropertyBO property = itemPropertyManager.selectOne(query);
			if (property == null) {
				property = new ItemPropertyBO();
				property.setId(itemPropertyManager.getId());
				property.setName(propertyName);
				itemPropertyManager.insert(property);
			}
			request.setAttribute("data", property);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
