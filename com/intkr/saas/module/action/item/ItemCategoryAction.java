package com.intkr.saas.module.action.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateValueBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemCategoryManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSpuTemplateManager;
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
public class ItemCategoryAction extends BaseAction<ItemCategoryBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static ItemManager itemManager = IOC.get("ItemManager");

	private static ItemCategoryManager categoryManager = IOC.get("ItemCategoryManager");

	private ItemSpuTemplateManager itemSpuTemplateManager = IOC.get("ItemSpuTemplateManager");

	private ItemSpuTemplateValueManager itemSpuTemplateValueManager = IOC.get("ItemSpuTemplateValueManager");

	public ItemCategoryBO getBO(HttpServletRequest request) {
		ItemCategoryBO itemCategoryBO = getParameter(request);
		return itemCategoryBO;
	}

	public static ItemCategoryBO getParameter(HttpServletRequest request) {
		ItemCategoryBO itemCategoryBO = new ItemCategoryBO();
		itemCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemCategoryBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemCategoryBO.setLevel(RequestUtil.getParam(request, "level", Integer.class));
		itemCategoryBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemCategoryBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		itemCategoryBO.setName(RequestUtil.getParam(request, "name"));
		itemCategoryBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemCategoryBO.setNote(RequestUtil.getParam(request, "note"));
		itemCategoryBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, itemCategoryBO);
		return itemCategoryBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return categoryManager;
	}

	public void doAddSpu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long categoryId = RequestUtil.getParam(request, "categoryId", Long.class);
			Long propertyId = RequestUtil.getParam(request, "propertyId", Long.class);

			ItemSpuTemplateBO spuTemplate = new ItemSpuTemplateBO();
			spuTemplate.setId(itemSpuTemplateManager.getId());
			spuTemplate.setCategoryId(categoryId);
			spuTemplate.setPropertyId(propertyId);
			itemSpuTemplateManager.insert(spuTemplate);

			String[] propertyValueIdsString = request.getParameterValues("propertyValuesId[]");
			for (String propertyValueIdString : propertyValueIdsString) {
				ItemSpuTemplateValueBO spuTemplateValue = new ItemSpuTemplateValueBO();
				spuTemplateValue.setId(itemSpuTemplateValueManager.getId());
				spuTemplateValue.setSpuTemplateId(spuTemplate.getId());
				spuTemplateValue.setValueId(Long.valueOf(propertyValueIdString));
				itemSpuTemplateValueManager.insert(spuTemplateValue);
			}

			request.setAttribute("result", true);
			request.setAttribute("msg", "绑定成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doDeleteSpu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long spuTemplateId = RequestUtil.getParam(request, "spuTemplateId", Long.class);
			ItemSpuTemplateBO spuTemplate = itemSpuTemplateManager.get(spuTemplateId);
			itemSpuTemplateValueManager.fill(spuTemplate);

			for (ItemSpuTemplateValueBO spuTemplateValue : spuTemplate.getSpuTemplateValueList()) {
				itemSpuTemplateValueManager.delete(spuTemplateValue.getId());
			}
			itemSpuTemplateManager.delete(spuTemplate.getId());

			request.setAttribute("result", true);
			request.setAttribute("msg", "解绑成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doCountItemNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long saasId = SessionClient.getSaasId(request);
			List<ItemCategoryBO> categoryList = categoryManager.getFirstCategoryFull(saasId);
			for (ItemCategoryBO firstCategory : categoryList) {
				countFirstCategory(firstCategory);
				if (firstCategory.getChilds() == null) {
					continue;
				}
				for (ItemCategoryBO secondCategory : firstCategory.getChilds()) {
					countSecondCategory(firstCategory, secondCategory);
					if (secondCategory.getChilds() == null) {
						continue;
					}
					for (ItemCategoryBO thirdCategory : secondCategory.getChilds()) {
						countThirdCategory(firstCategory, secondCategory, thirdCategory);
					}
				}
			}
			request.setAttribute("result", true);
			request.setAttribute("msg", "执行成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	private void countThirdCategory(ItemCategoryBO firstCategory, ItemCategoryBO secondCategory, ItemCategoryBO thirdCategory) {
		if (firstCategory == null || secondCategory == null || thirdCategory == null) {
			return;
		}
		ItemBO query = new ItemBO();
		query.setSaasId(firstCategory.getSaasId());
		query.setFirstCategoryId(firstCategory.getId());
		query.setSecondCategoryId(secondCategory.getId());
		query.setThirdCategoryId(thirdCategory.getId());
		Long count = itemManager.count(query);
		if (count.toString().equals(thirdCategory.getFeature("itemCount"))) {
			return;
		}
		thirdCategory.setFeature("itemCount", count.toString());
		categoryManager.update(thirdCategory);
	}

	private void countSecondCategory(ItemCategoryBO firstCategory, ItemCategoryBO secondCategory) {
		if (firstCategory == null || secondCategory == null) {
			return;
		}
		ItemBO query = new ItemBO();
		query.setSaasId(firstCategory.getSaasId());
		query.setFirstCategoryId(firstCategory.getId());
		query.setSecondCategoryId(secondCategory.getId());
		Long count = itemManager.count(query);
		if (count.toString().equals(secondCategory.getFeature("itemCount"))) {
			return;
		}
		secondCategory.setFeature("itemCount", count.toString());
		categoryManager.update(secondCategory);
	}

	private void countFirstCategory(ItemCategoryBO firstCategory) {
		if (firstCategory == null) {
			return;
		}
		ItemBO query = new ItemBO();
		query.setSaasId(firstCategory.getSaasId());
		query.setFirstCategoryId(firstCategory.getId());
		Long count = itemManager.count(query);
		if (count.toString().equals(firstCategory.getFeature("itemCount"))) {
			return;
		}
		firstCategory.setFeature("itemCount", count.toString());
		categoryManager.update(firstCategory);
	}

}
