package com.intkr.saas.module.screen.admin.system.dialog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-15 上午10:22:27
 * @version 1.0
 */
public class ArticleCategorySelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleCategoryManager categoryManager = IOC.get(ArticleCategoryManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<ArticleCategoryBO> list = categoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		request.setAttribute("categoryList", list);
	}

}