package com.intkr.saas.manager.cms.article.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.ArticleDAO;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.dbo.article.ArticleDO;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.type.sns.CommentType;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.cms.article.ArticleManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:32:55
 * @version 1.0
 */
@Repository("ArticleManager")
public class ArticleManagerImpl extends BaseManagerImpl<ArticleBO, ArticleDO> implements ArticleManager {

	@Resource
	private ArticleDAO articleDAO;

	public BaseDAO<ArticleDO> getBaseDAO() {
		return articleDAO;
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
		ArticleBO article = get(articleId);
		if (article == null) {
			return false;
		}
		if (article.getViewCount() == null) {
			article.setViewCount(1);
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

	public boolean recover(Long articleId) {
		return articleDAO.recover(articleId);
	}

	public boolean deleteReal(Long articleId) {
		return articleDAO.deleteReal(articleId);
	}

}
