package com.intkr.saas.module.screen.admin.shop.basic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.link.LinkBO;
import com.intkr.saas.domain.type.cms.LinkType;
import com.intkr.saas.manager.saas.LinkManager;
import com.intkr.saas.module.action.article.link.LinkAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-3 上午8:51:26
 * @version 1.0
 */
public class FriendLinkMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LinkManager linkManager = IOC.get("LinkManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		LinkBO query = LinkAction.getParameter(request);
		query.setType(LinkType.FriendLink.getCode());
		query = linkManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
