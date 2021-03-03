package com.intkr.saas.module.toolbox.cms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class TagDS extends BaseToolBox {

	private TagManager tagManager = IOC.get("TagManager");

	public List<ArticleTagBO> select(HttpServletRequest request) {
		ArticleTagBO query = new ArticleTagBO();
		List<ArticleTagBO> tagList = tagManager.select(query);
		return tagList;
	}

	public ArticleTagBO select(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "page") && RequestUtil.existParam(request, "pageSize")) {
			return select(request, RequestUtil.getParam(request, "page", Integer.class), RequestUtil.getParam(request, "pageSize", Integer.class));
		} else {
			return select(request, 1, 20);
		}
	}

	public ArticleTagBO select(HttpServletRequest request, Integer page, Integer pageSize) {
		ArticleTagBO query = new ArticleTagBO();
		query.set_pageSize(pageSize);
		query.set_page(page);
		tagManager.selectAndCount(query);
		return query;
	}

	public List<ArticleTagBO> selectAll(HttpServletRequest request) {
		ArticleTagBO query = new ArticleTagBO();
		query.set_pageSize(100000);
		List<ArticleTagBO> list = tagManager.select(query);
		return list;
	}

}
