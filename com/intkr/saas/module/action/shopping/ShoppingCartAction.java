package com.intkr.saas.module.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemSkuBO;
import com.intkr.saas.domain.bo.shopping.ShoppingCartBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemSkuManager;
import com.intkr.saas.manager.shopping.ShoppingCartManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:28:03
 * @version 1.0
 */
public class ShoppingCartAction extends BaseAction<ShoppingCartBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShoppingCartManager shopCartManager = IOC.get("ShoppingCartManager");

	private ItemSkuManager itemSkuManager = IOC.get("ItemSkuManager");

	public ShoppingCartBO getBO(HttpServletRequest request) {
		ShoppingCartBO favoriteBO = getParameter(request);
		return favoriteBO;
	}

	public static ShoppingCartBO getParameter(HttpServletRequest request) {
		ShoppingCartBO favoriteBO = new ShoppingCartBO();
		favoriteBO.setId(RequestUtil.getParam(request, "id", Long.class));
		favoriteBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		favoriteBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		favoriteBO.setSkuId(RequestUtil.getParam(request, "skuId", Long.class));
		if (RequestUtil.existParam(request, "unitPrice")) {
			favoriteBO.setUnitPrice(MoneyUtil.parse(RequestUtil.getParam(request, "unitPrice")));
		}
		favoriteBO.setCount(RequestUtil.getParam(request, "count", Integer.class));
		if (RequestUtil.existParam(request, "price")) {
			favoriteBO.setPrice(MoneyUtil.parse(RequestUtil.getParam(request, "price")));
		}
		favoriteBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, favoriteBO);
		return favoriteBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopCartManager;
	}
	
	@StartTransaction
	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long skuId = RequestUtil.getParam(request, "skuId", Long.class);
		ShoppingCartBO query = new ShoppingCartBO();
		query.setUserId(SessionClient.getLoginUserId(request));
		query.setSkuId(skuId);
		ShoppingCartBO shopCart = shopCartManager.selectOne(query);
		if (shopCart == null) {
			shopCart = new ShoppingCartBO();
			shopCart.setId(shopCartManager.getId());
			shopCart.setSaasId(SessionClient.getSaasId(request));
			shopCart.setUserId(SessionClient.getLoginUserId(request));
			shopCart.setSkuId(skuId);
			shopCart.setCount(RequestUtil.getParam(request, "count", Integer.class));
			ItemSkuBO sku = itemSkuManager.get(skuId);
			if (shopCart.getCount() == null) {
				shopCart.setCount(1);
			}
			shopCart.setUnitPrice(sku.getPrice());
			shopCart.setPrice(shopCart.getUnitPrice() * shopCart.getCount());
			shopCartManager.insert(shopCart);
		} else {
			Integer count = RequestUtil.getParam(request, "count", Integer.class);
			if (count != null) {
				shopCart.setCount(shopCart.getCount() + count);
				shopCartManager.update(shopCart);
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "加入购物车成功！");
	}

	@StartTransaction
	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long skuId = RequestUtil.getParam(request, "skuId", Long.class);
		ShoppingCartBO query = new ShoppingCartBO();
		query.setUserId(SessionClient.getLoginUserId(request));
		query.setSkuId(skuId);
		ShoppingCartBO shopCart = shopCartManager.selectOne(query);
		if (shopCart == null) {
			shopCart = new ShoppingCartBO();
			shopCart.setId(shopCartManager.getId());
			shopCart.setSaasId(SessionClient.getSaasId(request));
			shopCart.setUserId(SessionClient.getLoginUserId(request));
			shopCart.setSkuId(skuId);
			shopCart.setCount(RequestUtil.getParam(request, "count", Integer.class));
			ItemSkuBO sku = itemSkuManager.get(skuId);
			if (shopCart.getCount() == null) {
				shopCart.setCount(1);
			}
			shopCart.setUnitPrice(sku.getPrice());
			shopCart.setPrice(shopCart.getUnitPrice() * shopCart.getCount());
			shopCartManager.insert(shopCart);
		} else {
			Integer count = RequestUtil.getParam(request, "count", Integer.class);
			if (count != null) {
				shopCart.setCount(count);
				shopCart.setPrice(shopCart.getUnitPrice() * shopCart.getCount());
				shopCartManager.update(shopCart);
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功！");
	}

	@StartTransaction
	public void doRemove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		ShoppingCartBO cart = shopCartManager.get(id);
		if (cart != null) {
			shopCartManager.delete(cart.getId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "删除成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统！");
		}
	}

}
