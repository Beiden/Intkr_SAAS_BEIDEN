package com.intkr.saas.module.action.sms;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.sms.ItemFlashActivityTimeItemBO;
import com.intkr.saas.manager.sms.ItemFlashActivityTimeItemManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 秒杀活动
 * 
 * @table item_flash_activity_time_item
 * 
 * @author Beiden
 * @date 2020-11-11 20:30:46
 * @version 1.0
 */
public class ItemFlashActivityTimeItemAction extends BaseAction<ItemFlashActivityTimeItemBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemFlashActivityTimeItemManager itemFlashActivityTimeItemManager = IOC.get(ItemFlashActivityTimeItemManager.class);

	public ItemFlashActivityTimeItemBO getBO(HttpServletRequest request) {
		ItemFlashActivityTimeItemBO itemFlashActivityTimeItemBO = getParameter(request);
		return itemFlashActivityTimeItemBO;
	}

	public static ItemFlashActivityTimeItemBO getParameter(HttpServletRequest request) {
		ItemFlashActivityTimeItemBO itemFlashActivityTimeItemBO = new ItemFlashActivityTimeItemBO();
		itemFlashActivityTimeItemBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemFlashActivityTimeItemBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemFlashActivityTimeItemBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		itemFlashActivityTimeItemBO.setActivityId(RequestUtil.getParam(request, "activityId", Long.class));
		itemFlashActivityTimeItemBO.setTimeId(RequestUtil.getParam(request, "timeId", Long.class));
		itemFlashActivityTimeItemBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		itemFlashActivityTimeItemBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemFlashActivityTimeItemBO.setPrice(RequestUtil.getParam(request, "price", Long.class));
		itemFlashActivityTimeItemBO.setCount(RequestUtil.getParam(request, "count", Long.class));
		itemFlashActivityTimeItemBO.setFlashPrice(RequestUtil.getParam(request, "flashPrice", Long.class));
		itemFlashActivityTimeItemBO.setFlashCount(RequestUtil.getParam(request, "flashCount", Long.class));
		itemFlashActivityTimeItemBO.setFlashLimitCount(RequestUtil.getParam(request, "flashLimitCount", Long.class));
		itemFlashActivityTimeItemBO.setStatus(RequestUtil.getParam(request, "status", String.class));
		itemFlashActivityTimeItemBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		itemFlashActivityTimeItemBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, itemFlashActivityTimeItemBO);
		return itemFlashActivityTimeItemBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemFlashActivityTimeItemManager;
	}

}
