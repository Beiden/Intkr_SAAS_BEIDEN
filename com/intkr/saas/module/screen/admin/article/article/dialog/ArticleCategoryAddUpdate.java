package com.intkr.saas.module.screen.admin.article.article.dialog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ArticleCategoryAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCategoryManager manager = IOC.get(ArticleCategoryManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<ArticleCategoryBO> categoryList = manager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		request.setAttribute("categoryList", categoryList);

		String articleCategoryId = RequestUtil.getParam(request, "articleCategoryId");
		ArticleCategoryBO articleCategory = manager.get(articleCategoryId);
		request.setAttribute("articleCategory", articleCategory);
		request.setAttribute("addId", manager.getId());
	}

}
