package com.intkr.saas.manager.cms.comment;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.dbo.article.ArticleCommentDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:10:54
 * @version 1.0
 */
public interface ArticleCommentManager extends BaseManager<ArticleCommentBO, ArticleCommentDO> {

	public Long countByRelatedId(Long relatedId);

	public ArticleBO fill(ArticleBO article);

	public PageBO fill(PageBO page);

	public <T> List<T> fill(List<T> list);

}
