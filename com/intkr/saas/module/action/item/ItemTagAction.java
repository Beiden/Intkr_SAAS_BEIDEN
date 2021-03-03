package com.intkr.saas.module.action.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.item.ItemTagBO;
import com.intkr.saas.domain.bo.item.ItemTagRelatedBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.item.ItemTagManager;
import com.intkr.saas.manager.item.ItemTagRelatedManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 商品标签
 * 
 * @table item_tag
 * 
 * @author Beiden
 * @date 2020-11-02 09:54:32
 * @version 1.0
 */
public class ItemTagAction extends BaseAction<ItemTagBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ItemTagManager itemTagManager = IOC.get(ItemTagManager.class);

	private ItemTagRelatedManager itemTagRelatedManager = IOC.get(ItemTagRelatedManager.class);

	private ItemManager itemManager = IOC.get(ItemManager.class);

	public ItemTagBO getBO(HttpServletRequest request) {
		ItemTagBO itemTagBO = getParameter(request);
		return itemTagBO;
	}

	public static ItemTagBO getParameter(HttpServletRequest request) {
		ItemTagBO itemTagBO = new ItemTagBO();
		itemTagBO.setId(RequestUtil.getParam(request, "id", Long.class));
		itemTagBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		itemTagBO.setName(RequestUtil.getParam(request, "name", String.class));
		itemTagBO.setImgDesc(RequestUtil.getParam(request, "imgDesc", String.class));
		itemTagBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		itemTagBO.setNote(RequestUtil.getParam(request, "note", String.class));
		PagerUtil.initPage(request, itemTagBO);
		return itemTagBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return itemTagManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestUtil.setParam(request, "sort", RequestUtil.getParam(request, "id"));
		super.doAdd(request, response);
	}

	public void doTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long itemId = RequestUtil.getParam(request, "itemId", Long.class);
		Long tagId = RequestUtil.getParam(request, "tagId", Long.class);
		ItemBO item = itemManager.get(itemId);
		if (item == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "商品不存在！");
			return;
		}
		if (item.containsTagId(tagId)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "商品重复打标异常！");
			return;
		}
		item.addTagId(tagId);
		itemManager.update(item);

		ItemTagRelatedBO related = new ItemTagRelatedBO();
		related.setId(itemTagRelatedManager.getId());
		related.setSaasId(SessionClient.getSaasId(request));
		related.setTagId(tagId);
		related.setRelatedId(itemId);
		itemTagRelatedManager.insert(related);

		request.setAttribute("result", true);
		request.setAttribute("msg", "打标成功！");
	}

	public void doTagName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tagName = RequestUtil.getParam(request, "tagName");
		ItemTagBO query = new ItemTagBO();
		query.setName(tagName);
		query.setSaasId(SessionClient.getSaasId(request));
		ItemTagBO tag = itemTagManager.selectOne(query);
		if (tag == null) {
			ItemTagBO insert = new ItemTagBO();
			insert.setSaasId(SessionClient.getSaasId(request));
			insert.setId(itemTagManager.getId());
			insert.setSort(insert.getId().doubleValue());
			insert.setName(tagName);
			itemTagManager.insert(insert);
			RequestUtil.setParam(request, "tagId", insert.getId() + "");
		} else {
			RequestUtil.setParam(request, "tagId", tag.getId() + "");
		}
		doTag(request, response);
	}

}
