package com.intkr.saas.manager.cms.article;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.dbo.article.ArticleDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:32:55
 * @version 1.0
 */
public interface ArticleManager extends BaseManager<ArticleBO, ArticleDO> {

	public boolean deleteReal(Long articleId);
	
	public boolean increaseViewCount(Long articleId);
	
	public boolean recover(Long articleId);

	public ArticleCommentBO fill(ArticleCommentBO comment);

	public List<?> fill(List<?> list);

}
