package com.intkr.saas.module.action.shopping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.shopping.ItemCompareBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shopping.ItemCompareManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class ItemCompareAction extends BaseAction<ItemCompareBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemCompareManager shopCompareManager = IOC.get("ShopCompareManager");

	public ItemCompareBO getBO(HttpServletRequest request) {
		ItemCompareBO shopCompareBO = getParameter(request);
		return shopCompareBO;
	}

	public static ItemCompareBO getParameter(HttpServletRequest request) {
		ItemCompareBO shopCompareBO = new ItemCompareBO();
		shopCompareBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopCompareBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		shopCompareBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopCompareBO.setItemId(RequestUtil.getParam(request, "itemId", Long.class));
		PagerUtil.initPage(request, shopCompareBO);
		return shopCompareBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return shopCompareManager;
	}

	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long itemId = RequestUtil.getParam(request, "itemId", Long.class);
		ItemCompareBO query = new ItemCompareBO();
		query.setUserId(SessionClient.getLoginUserId(request));
		query.setItemId(itemId);
		if (shopCompareManager.count(query) == 0) {
			ItemCompareBO compare = new ItemCompareBO();
			compare.setSaasId(SessionClient.getSaasId(request));
			compare.setId(shopCompareManager.getId());
			compare.setUserId(SessionClient.getLoginUserId(request));
			compare.setItemId(itemId);
			shopCompareManager.insert(compare);
			request.setAttribute("result", true);
			request.setAttribute("msg", "添加成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "你已经添加过了！");
		}
	}

	public void doRemove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		ItemCompareBO itemCollect = shopCompareManager.get(id);
		if (itemCollect != null) {
			shopCompareManager.delete(itemCollect.getId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "删除成功！");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常！");
		}
	}

}
