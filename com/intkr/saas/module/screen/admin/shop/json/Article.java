package com.intkr.saas.module.screen.admin.shop.json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-21 下午10:37:45
 * @version 1.0
 */
public class Article {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleManager articleManager = IOC.get("ArticleManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String articleId = RequestUtil.getParam(request, "articleId");
		ArticleBO article = articleManager.get(articleId);
		request.setAttribute("article", article);
	}

}
