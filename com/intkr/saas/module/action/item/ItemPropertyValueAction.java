package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemPropertyValueBO;
import com.intkr.saas.manager.BaseManager;
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
public class ItemPropertyValueAction extends BaseAction<ItemPropertyValueBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemPropertyValueManager itemPropertyValueManager = IOC.get("ItemPropertyValueManager");

	public ItemPropertyValueBO getBO(HttpServletRequest request) {
		ItemPropertyValueBO shopPropertyValueBO = getParameter(request);
		return shopPropertyValueBO;
	}

	public static ItemPropertyValueBO getParameter(HttpServletRequest request) {
		ItemPropertyValueBO shopPropertyValueBO = new ItemPropertyValueBO();
		shopPropertyValueBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopPropertyValueBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopPropertyValueBO.setPropertyId(RequestUtil.getParam(request, "propertyId", Long.class));
		shopPropertyValueBO.setValue(RequestUtil.getParam(request, "value"));
		shopPropertyValueBO.setNote(RequestUtil.getParam(request, "note"));
		shopPropertyValueBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		shopPropertyValueBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopPropertyValueBO);
		return shopPropertyValueBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemPropertyValueManager;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long propertyId = RequestUtil.getParam(request, "propertyId", Long.class);
			String propertyValueName = RequestUtil.getParam(request, "propertyValueName");
			ItemPropertyValueBO query = new ItemPropertyValueBO();
			query.setPropertyId(propertyId);
			query.setValue(propertyValueName);
			ItemPropertyValueBO propertyValue = itemPropertyValueManager.selectOne(query);
			if (propertyValue == null) {
				propertyValue = new ItemPropertyValueBO();
				propertyValue.setId(itemPropertyValueManager.getId());
				propertyValue.setPropertyId(propertyId);
				propertyValue.setValue(propertyValueName);
				itemPropertyValueManager.insert(propertyValue);
			}
			request.setAttribute("data", propertyValue);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
