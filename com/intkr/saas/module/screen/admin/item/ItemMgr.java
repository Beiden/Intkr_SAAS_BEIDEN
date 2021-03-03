package com.intkr.saas.module.screen.admin.item;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemCategoryBO;
import com.intkr.saas.domain.type.item.ItemStatus;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemCategoryManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.item.ItemTagManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.item.ItemAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午10:26:20
 * @version 1.0
 */
public class ItemMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemManager itemManager = IOC.get("ItemManager");

	private UserManager userManager = IOC.get("UserManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	private ItemTagManager itemTagManager = IOC.get("ItemTagManager");

	private ItemCategoryManager itemCategoryManager = IOC.get("ItemCategoryManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ItemBO query = ItemAction.getParameter(request);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		query.setSaasId(SessionClient.getSaasId(request));
		itemManager.selectAndCount(query);
		List<ItemBO> list = query.getDatas();
		userManager.fill(list);
		shopManager.fill(list);
		imgManager.fill(list);
		itemTagManager.fills(list);
		itemCategoryManager.fill(list);
		itemSkuManager.fill(list);
		if (query.getQuery("categoryId") != null) {
			Object categoryId = query.getQuery("categoryId");
			ItemCategoryBO itemCategory = itemCategoryManager.get(categoryId);
			ItemCategoryBO parent = null;
			ItemCategoryBO pparent = null;
			if (itemCategory.getPid() != null) {
				parent = itemCategoryManager.get(itemCategory.getPid());
				if (parent == null) {
					query.setFirstCategoryId(itemCategory.getId());
					query.setFirstCategory(itemCategory);
				} else {
					query.setFirstCategoryId(parent.getId());
					query.setFirstCategory(parent);
					query.setSecondCategoryId(itemCategory.getId());
					query.setSecondCategory(itemCategory);
				}
			}
			if (parent != null && parent.getPid() != null) {
				pparent = itemCategoryManager.get(parent.getPid());
				if (pparent == null) {
					query.setFirstCategoryId(parent.getId());
					query.setFirstCategory(parent);
					query.setSecondCategoryId(itemCategory.getId());
					query.setSecondCategory(itemCategory);
				} else {
					query.setFirstCategoryId(pparent.getId());
					query.setFirstCategory(pparent);
					query.setSecondCategoryId(parent.getId());
					query.setSecondCategory(parent);
					query.setThirdCategoryId(itemCategory.getId());
					query.setThirdCategory(itemCategory);
				}
			}
		}
		request.setAttribute("query", query);
		request.setAttribute("list", list);
		request.setAttribute("ItemStatus", ItemStatus.Error);
	}
}
