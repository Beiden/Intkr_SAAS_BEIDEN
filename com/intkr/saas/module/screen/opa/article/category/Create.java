package com.intkr.saas.module.screen.opa.article.category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.article.article.ArticleCategoryAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-5 上午8:10:20
 * @version 1.0
 */
public class Create {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArticleCategoryAction action = IOC.get(ArticleCategoryAction.class);
		action.doAdd(request, response);
	}

}
