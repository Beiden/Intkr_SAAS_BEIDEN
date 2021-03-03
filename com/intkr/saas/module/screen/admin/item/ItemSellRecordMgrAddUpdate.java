package com.intkr.saas.module.screen.admin.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.shop.ItemSellRecordBO;
import com.intkr.saas.manager.shop.ItemSellRecordManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 商品销售记录
 * 
 * @table item_tag_related
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:15
 * @version 1.0
 */
public class ItemSellRecordMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemSellRecordManager manager = IOC.get(ItemSellRecordManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = RequestUtil.getParam(request, "id");
		ItemSellRecordBO itemTagRelated = manager.get(id);
		request.setAttribute("dto", itemTagRelated);
		request.setAttribute("addId", manager.getId());
	}

}
