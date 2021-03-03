package com.intkr.saas.manager.cms.page.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageDAO;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.dbo.page.PageDO;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.type.sns.CommentType;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.cms.page.PageManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:00:20
 * @version 1.0
 */
@Repository("PageManager")
public class PageManagerImpl extends BaseManagerImpl<PageBO, PageDO> implements PageManager {

	@Resource
	private PageDAO pageDAO;

	public BaseDAO<PageDO> getBaseDAO() {
		return pageDAO;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof ArticleCommentBO) {
				fill((ArticleCommentBO) obj);
			}
		}
		return list;
	}

	public boolean increaseViewCount(Long articleId) {
		if (!IdEngine.isValidate(articleId)) {
			return false;
		}
		PageBO article = get(articleId);
		if (article == null) {
			return false;
		}
		if (article.getViewCount() == null) {
			article.setViewCount(0);
		}
		article.setViewCount(article.getViewCount() + 1);
		update(article);
		return true;
	}

	public ArticleCommentBO fill(ArticleCommentBO comment) {
		if (comment == null || !IdEngine.isValidate(comment.getRelatedId())) {
			return comment;
		}
		if (!CommentType.Article.getCode().equals(comment.getType())) {
			return comment;
		}
		comment.setProperty("article", get(comment.getRelatedId()));
		return comment;
	}

}
