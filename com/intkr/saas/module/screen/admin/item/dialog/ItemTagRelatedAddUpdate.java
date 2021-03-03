package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.ItemTagRelatedBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.item.ItemTagRelatedManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 商品标签
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:15
 * @version 1.0
 */
public class ItemTagRelatedAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemTagRelatedManager manager = IOC.get(ItemTagRelatedManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String itemTagRelatedId = RequestUtil.getParam(request, "itemTagRelatedId");
		ItemTagRelatedBO itemTagRelated = manager.get(itemTagRelatedId);
		request.setAttribute("itemTagRelated", itemTagRelated);
		request.setAttribute("addId", manager.getId());
	}

}
