package com.intkr.saas.module.action.sms;

import java.util.Date;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityBO;
import com.intkr.saas.manager.sms.ItemFlashActivityManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:31
 * @version 1.0
 */
public class ItemFlashActivityAction extends BaseAction<ItemFlashActivityBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityManager itemFlashActivityManager = IOC.get(ItemFlashActivityManager.class);

	public ItemFlashActivityBO getBO(HttpServletRequest request) {
		ItemFlashActivityBO itemFlashActivityBO = getParameter(request);
		return itemFlashActivityBO;
	}

	public static ItemFlashActivityBO getParameter(HttpServletRequest request) {
		ItemFlashActivityBO itemFlashActivityBO = new ItemFlashActivityBO();
		itemFlashActivityBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemFlashActivityBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemFlashActivityBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemFlashActivityBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemFlashActivityBO.setType(RequestUtil.getParam(request, "type", String.class));
		itemFlashActivityBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemFlashActivityBO.setStartTime(RequestUtil.getParam(request, "startTime", Date.class));
		itemFlashActivityBO.setEndTime(RequestUtil.getParam(request, "endTime", Date.class));
		itemFlashActivityBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemFlashActivityBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemFlashActivityBO);
		return itemFlashActivityBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemFlashActivityManager;
	}

}
