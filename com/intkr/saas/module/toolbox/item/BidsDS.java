package com.intkr.saas.module.toolbox.item;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.type.item.ItemStatus;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class BidsDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get(ItemManager.class);

	private ImgManager mediaManager = IOC.get(ImgManager.class);

	private ShopManager shopManager = IOC.get(ShopManager.class);

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ItemBO select(HttpServletRequest request, HttpServletResponse response) {
		ItemBO query = new ItemBO();
		if (RequestUtil.existParam(request, "_page") && RequestUtil.existParam(request, "_pageSize")) {
			query.set_pageSize(RequestUtil.getParam(request, "_pageSize", Integer.class));
			query.set_page(RequestUtil.getParam(request, "_page", Integer.class));
		}
		if (RequestUtil.existParam(request, "categoryId")) {
			query.setQuery("categoryId", request.getParameter("categoryId"));
		}
		if (RequestUtil.existParam(request, "searchWord")) {
			query.setQuery("searchWord", request.getParameter("searchWord") + "%");
		}
		Set<String> statusSet = new HashSet<String>();
		statusSet.add(ItemStatus.PreHeat.getCode());
		statusSet.add(ItemStatus.InProgress.getCode());
		statusSet.add(ItemStatus.WaitResult.getCode());
		statusSet.add(ItemStatus.Result.getCode());
		statusSet.add(ItemStatus.Over.getCode());
		query.setQuery("statusSet", statusSet);
		itemManager.selectAndCount(query);
		return query;
	}

	public ItemBO getById(Long articleId) {
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		ItemBO item = itemManager.get(articleId);
		if (item == null) {
			return null;
		}
		if (ItemStatus.PreHeat.getCode().equals(item.getStatus()) || //
				ItemStatus.InProgress.getCode().equals(item.getStatus()) || //
				ItemStatus.WaitResult.getCode().equals(item.getStatus()) || //
				ItemStatus.Result.getCode().equals(item.getStatus()) || //
				ItemStatus.Over.getCode().equals(item.getStatus())) {
			return item;
		}
		return null;
	}

	public ItemBO fillShop(ItemBO item) {
		shopManager.fill(item);
		return item;
	}

	public ItemBO fillImg(ItemBO item) {
		mediaManager.fill(item);
		return item;
	}

	public ItemBO fillsImg(ItemBO item) {
		mediaManager.fills(item);
		return item;
	}

}
