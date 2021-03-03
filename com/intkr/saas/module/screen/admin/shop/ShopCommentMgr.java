package com.intkr.saas.module.screen.admin.shop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.type.sns.CommentStatus;
import com.intkr.saas.manager.shop.ShopCommentManager;
import com.intkr.saas.manager.shop.ShopManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.shop.ShopCommentAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午3:44:48
 * @version 1.0
 */
public class ShopCommentMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ShopCommentManager commentManager = IOC.get("ShopCommentManager");

	private UserManager userManager = IOC.get("UserManager");

	private ShopManager shopManager = IOC.get("ShopManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ShopCommentBO query = ShopCommentAction.getParameter(request);
		query = commentManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		query.setDatas(shopManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());

		request.setAttribute("commentStatusList", CommentStatus.values());
	}

}
