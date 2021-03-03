package com.intkr.saas.module.toolbox.shopping;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.shopping.ShoppingCartManager;
import com.intkr.saas.module.action.shopping.ShoppingCartAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:59:08
 * @version 1.0
 */
public class ShoppingCartDS extends BaseToolBox {

	private ShoppingCartManager cartManager = IOC.get("ShoppingCartManager");

	private ItemManager itemManager = IOC.get("ItemManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	/**
	 * 查询列表
	 */
	public ShoppingCartBO select(HttpServletRequest request, HttpServletResponse response) {
		try {
			ShoppingCartBO query = ShoppingCartAction.getParameter(request);
			query.setId(null);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			cartManager.selectAndCount(query);
			for (Object obj : query.getDatas()) {
				ShoppingCartBO cart = (ShoppingCartBO) obj;
				ItemSkuBO sku = itemSkuManager.getFull(cart.getSkuId());
				cart.setSku(sku);
				if (sku != null) {
					cart.setItem(itemManager.get(sku.getItemId()));
				}
				ItemBO item = cart.getItem();
				imgManager.fill(item);
			}
			return query;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 查询列表
	 */
	public Map<String, Object> sumary(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> datas = new HashMap<String, Object>();
		try {
			ShoppingCartBO query = ShoppingCartAction.getParameter(request);
			query.setId(null);
			query.setSaasId(SessionClient.getSaasId(request));
			query.setUserId(SessionClient.getLoginUserId(request));
			if (!RequestUtil.existParam(request, "orderBy")) {
				query.setQuery("orderBy", "id");
			}
			if (!RequestUtil.existParam(request, "order")) {
				query.setQuery("order", "desc");
			}
			query.set_pageSize(3);
			cartManager.selectAndCount(query);
			Long allPrice = 0l;
			for (Object obj : query.getDatas()) {
				ShoppingCartBO cart = (ShoppingCartBO) obj;
				allPrice += cart.getPrice();
				ItemSkuBO sku = itemSkuManager.getFull(cart.getSkuId());
				cart.setSku(sku);
				if (sku != null) {
					cart.setItem(itemManager.get(sku.getItemId()));
				}
				ItemBO item = cart.getItem();
				imgManager.fill(item);
			}
			datas.put("count", query.get_count());
			datas.put("shoppingCartItems", query.getDatas());
			boolean hasMoreShoppingCartItems = query.get_count() > query.getDatas().size();
			datas.put("hasMoreShoppingCartItems", hasMoreShoppingCartItems);
			if (!hasMoreShoppingCartItems) {
				datas.put("allPrice", allPrice);
			}
			return datas;
		} catch (Exception e) {
			logger.error("", e);
		}
		return datas;
	}

}
