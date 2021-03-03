package com.intkr.saas.module.toolbox.cms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class ArticleCategoryDS extends BaseToolBox {

	private ArticleCategoryManager articleCategoryManager = IOC.get("ArticleCategoryManager");

	public List<ArticleCategoryBO> select(HttpServletRequest request) {
		List<ArticleCategoryBO> list = articleCategoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		return list;
	}

}
