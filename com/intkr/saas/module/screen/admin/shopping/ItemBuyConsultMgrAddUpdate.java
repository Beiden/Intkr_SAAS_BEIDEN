package com.intkr.saas.module.screen.admin.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.shopping.ItemBuyConsultBO;
import com.intkr.saas.manager.shopping.ItemBuyConsultManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 商品咨询
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:15
 * @version 1.0
 */
public class ItemBuyConsultMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemBuyConsultManager manager = IOC.get(ItemBuyConsultManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = RequestUtil.getParam(request, "id");
		ItemBuyConsultBO itemTagRelated = manager.get(id);
		request.setAttribute("dto", itemTagRelated);
		request.setAttribute("addId", manager.getId());
	}

}
