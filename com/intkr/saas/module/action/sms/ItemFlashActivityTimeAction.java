package com.intkr.saas.module.action.sms;

import java.util.Date;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeBO;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:39
 * @version 1.0
 */
public class ItemFlashActivityTimeAction extends BaseAction<ItemFlashActivityTimeBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityTimeManager itemFlashActivityTimeManager = IOC.get(ItemFlashActivityTimeManager.class);

	public ItemFlashActivityTimeBO getBO(HttpServletRequest request) {
		ItemFlashActivityTimeBO itemFlashActivityTimeBO = getParameter(request);
		return itemFlashActivityTimeBO;
	}

	public static ItemFlashActivityTimeBO getParameter(HttpServletRequest request) {
		ItemFlashActivityTimeBO itemFlashActivityTimeBO = new ItemFlashActivityTimeBO();
		itemFlashActivityTimeBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemFlashActivityTimeBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemFlashActivityTimeBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemFlashActivityTimeBO.setActivityId(RequestUtil.getParam(request, "activityId", Long.class));
		itemFlashActivityTimeBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemFlashActivityTimeBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemFlashActivityTimeBO.setStartTime(RequestUtil.getParam(request, "startTime", Date.class));
		itemFlashActivityTimeBO.setEndTime(RequestUtil.getParam(request, "endTime", Date.class));
		itemFlashActivityTimeBO.setItemCount(RequestUtil.getParam(request, "itemCount", Long.class));
		itemFlashActivityTimeBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemFlashActivityTimeBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemFlashActivityTimeBO);
		return itemFlashActivityTimeBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemFlashActivityTimeManager;
	}

}
