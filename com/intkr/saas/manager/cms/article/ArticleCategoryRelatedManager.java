package com.intkr.saas.manager.cms.article;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleCategoryRelatedBO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryRelatedDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:24:49
 * @version 1.0
 */
public interface ArticleCategoryRelatedManager extends BaseManager<ArticleCategoryRelatedBO, ArticleCategoryRelatedDO> {

	public List<ArticleCategoryRelatedBO> selectByArticleId(Long articleId);

	public List<Long> getCategoryIds(Long articleId);

}
