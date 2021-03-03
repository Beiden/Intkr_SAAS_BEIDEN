package com.intkr.saas.manager.cms.comment.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.comment.ArticleCommentDAO;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.dbo.article.ArticleCommentDO;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:10:54
 * @version 1.0
 */
@Repository("CommentManager")
public class ArticleCommentManagerImpl extends BaseManagerImpl<ArticleCommentBO, ArticleCommentDO> implements ArticleCommentManager {

	@Resource
	private ArticleCommentDAO commentDAO;

	public BaseDAO<ArticleCommentDO> getBaseDAO() {
		return commentDAO;
	}

	public Long countByRelatedId(Long relatedId) {
		ArticleCommentBO query = new ArticleCommentBO();
		query.setRelatedId(relatedId);
		return count(query);
	}

	public ArticleBO fill(ArticleBO article) {
		if (article != null) {
			ArticleCommentBO query = new ArticleCommentBO();
			query.setRelatedId(article.getId());
			List<ArticleCommentBO> commentList = select(query);
			article.setComments(commentList);
		}
		return article;
	}

	public PageBO fill(PageBO article) {
		if (article != null) {
			ArticleCommentBO query = new ArticleCommentBO();
			query.setRelatedId(article.getId());
			List<ArticleCommentBO> commentList = select(query);
			article.setComments(commentList);
		}
		return article;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof ArticleBO) {
				fill((ArticleBO) bo);
			} else if (bo instanceof PageBO) {
				fill((PageBO) bo);
			}
		}
		return list;
	}

}
