package com.intkr.saas.manager.cms.article;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 09:32:27
 * @version 1.0
 */
public interface ArticleCategoryManager extends BaseManager<ArticleCategoryBO, ArticleCategoryDO> {

	public List<ArticleCategoryBO> getFirstCategoryFull(Long saasId);

	public <T> List<T> fill(List<T> list);

	public ArticleBO fill(ArticleBO article);

	public List<ArticleCategoryBO> getByIds(List<Long> ids);

}
