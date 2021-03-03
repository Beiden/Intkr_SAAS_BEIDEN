package com.intkr.saas.module.screen.admin.item.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.manager.item.ItemSpuTemplateManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ItemSpuTemplateAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSpuTemplateManager itemSpuTemplateManager = IOC.get("ItemSpuTemplateManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopSpuTemplateId = RequestUtil.getParam(request, "shopSpuTemplateId");
		ItemSpuTemplateBO shopSpuTemplate = itemSpuTemplateManager.get(shopSpuTemplateId);
		request.setAttribute("shopSpuTemplate", shopSpuTemplate);
		request.setAttribute("addId", itemSpuTemplateManager.getId());
	}

}
