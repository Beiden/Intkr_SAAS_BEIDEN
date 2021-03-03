package com.intkr.saas.module.toolbox.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.type.item.ItemStatus;
import com.intkr.saas.domain.type.item.ItemType;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.item.ItemSpuManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.module.action.item.ItemAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 */
public class ItemDS extends BaseToolBox {

	private ItemManager itemManager = IOC.get(ItemManager.class);

	private ImgManager mediaManager = IOC.get(ImgManager.class);

	private ShopManager shopManager = IOC.get(ShopManager.class);

	private ImgManager imgManager = IOC.get("ImgManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	private ItemSpuManager itemSpuManager = IOC.get("ItemSpuManager");

	/**
	 * 查询列表
	 */
	public ItemBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ItemBO query = ItemAction.getParameter(request);
			initDefaultSort(request, query);
			query.setStatus(ItemStatus.UpShelf.getCode());
			itemManager.selectAndCount(query);
			if (query.get_count() > query.get_pageSize() * 100) {
				query.set_count(query.get_pageSize() * 100);
			}
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	// 设置默认排序，按权重倒序排序
	private void initDefaultSort(HttpServletRequest request, ItemBO query) {
		if (!RequestUtil.existParam(request, "orderBy")) {
			query.setQuery("orderBy", "sort");
		}
		if (!RequestUtil.existParam(request, "order")) {
			query.setQuery("order", "desc");
		}
	}

	List<ItemBO> list;

	public ItemBO getRandom(HttpServletRequest request) {
		ItemBO item = null;
		if (list == null) {
			ItemBO query = new ItemBO();
			query.setSaasId(SessionClient.getSaasId(request));
			query.set_pageSize(50);
			list = itemManager.select(query);
		}
		if (list.size() > 0) {
			item = list.get(new Random().nextInt(list.size() - 1));
		}
		imgManager.fills(item);
		itemSpuManager.fillFull(item);
		itemSkuManager.fillFull(item);
		return item;
	}

	/**
	 * 推荐
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ItemBO recommend(HttpServletRequest request) {
		try {
			ItemBO query = new ItemBO();
			query.setSaasId(SessionClient.getSaasId(request));
			query.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
			if (RequestUtil.existParam(request, "itemId")) {
				ItemBO item = itemManager.get(RequestUtil.getParam(request, "itemId", Long.class));
				query.setQuery("searchWord", item.getName());
			}
			query.setStatus(ItemStatus.UpShelf.getCode());
			query.set_pageSize(50);
			itemManager.selectAndCount(query);
			List<ItemBO> list = query.getDatas();
			List<ItemBO> returnList = new ArrayList<ItemBO>();
			if (list.size() > 0) {
				list.remove(0);
			}
			for (int i = 0; i < list.size(); i += 6) {
				returnList.add(list.get(i));
			}
			query.setDatas(returnList);
			imgManager.fill(query.getDatas());
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 猜你喜欢
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ItemBO calLike(HttpServletRequest request) {
		try {
			ItemBO query = new ItemBO();
			if (RequestUtil.existParam(request, "itemId")) {
				ItemBO item = itemManager.get(RequestUtil.getParam(request, "itemId", Long.class));
				query.setQuery("searchWord", item.getName());
			}
			query.setSaasId(SessionClient.getSaasId(request));
			query.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
			query.setStatus(ItemStatus.UpShelf.getCode());
			query.set_pageSize(50);
			itemManager.selectAndCount(query);
			List<ItemBO> list = query.getDatas();
			List<ItemBO> returnList = new ArrayList<ItemBO>();
			if (list.size() > 0) {
				list.remove(0);
			}
			if (list.size() > 0) {
				list.remove(0);
			}
			for (int i = 0; i < list.size(); i += 6) {
				returnList.add(0, list.get(i));
			}
			query.setDatas(returnList);
			imgManager.fill(query.getDatas());
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public ItemBO getById(Object itemIdObj) {
		Long itemId = null;
		if (itemIdObj instanceof String) {
			itemId = Long.valueOf((String) itemIdObj);
		} else if (itemIdObj instanceof Number) {
			itemId = ((Number) itemIdObj).longValue();
		}
		if (!IdEngine.isValidate(itemId)) {
			return null;
		}
		ItemBO item = itemManager.get(itemId);
		if (item == null) {
			return null;
		}
		if (ItemStatus.UpShelf.getCode().equals(item.getStatus())) {
			return item;
		}
		return null;
	}

	public ItemBO getFull(Object itemIdObject) {
		if (itemIdObject == null || "".equals(itemIdObject)) {
			return null;
		}
		Long itemId = null;
		if (itemIdObject instanceof String) {
			itemId = Long.valueOf((String) itemIdObject);
		} else if (itemIdObject instanceof Number) {
			itemId = ((Number) itemIdObject).longValue();
		}
		if (!IdEngine.isValidate(itemId)) {
			return null;
		}
		ItemBO item = itemManager.get(itemId);
		imgManager.fills(item);
		itemSpuManager.fillFull(item);
		itemSkuManager.fillFull(item);
		return item;
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	public ItemBO select(HttpServletRequest request) {
		ItemBO query = new ItemBO();
		query.setSaasId(SessionClient.getSaasId(request));
		query.setType(ItemType.Normal.getCode());
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
		query.setStatus(ItemStatus.UpShelf.getCode());
		itemManager.selectCount(query);
		return query;
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
