package com.intkr.saas.module.toolbox.cms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.domain.type.sns.CommentStatus;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class CommentDS extends BaseToolBox {

	private ArticleCommentManager commentManager = IOC.get("CommentManager");

	private ArticleManager articleManager = IOC.get("ArticleManager");

	public ArticleCommentBO selectByArticleId(Long articleId) {
		ArticleCommentBO query = new ArticleCommentBO();
		query.setRelatedId(articleId);
		query = commentManager.selectAndCount(query);
		query.setDatas(query.getDatas());
		query.setStatus(CommentStatus.Approve.getCode());
		return query;
	}

	public ArticleCommentBO selectLast(HttpServletRequest request) {
		ArticleCommentBO query = new ArticleCommentBO();
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query.set_pageSize(10);
		query = commentManager.selectAndCount(query);
		query.setDatas(query.getDatas());
		query.setStatus(CommentStatus.Approve.getCode());
		articleManager.fill(query.getDatas());
		return query;
	}

	public List<ArticleCommentBO> selectAll(HttpServletRequest request) {
		ArticleCommentBO query = new ArticleCommentBO();
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query.set_pageSize(100000);
		query.setStatus(CommentStatus.Approve.getCode());
		List<ArticleCommentBO> list = commentManager.select(query);
		return list;
	}

}
