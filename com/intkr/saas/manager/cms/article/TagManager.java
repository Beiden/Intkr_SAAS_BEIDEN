package com.intkr.saas.manager.cms.article;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.dbo.article.ArticleTagDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:19:26
 * @version 1.0
 */
public interface TagManager extends BaseManager<ArticleTagBO, ArticleTagDO> {

	public <T> List<T> fill(List<T> list);

	public ArticleBO fill(ArticleBO article);

	public PageBO fill(PageBO article);

	public ArticleTagBO getByName(String name);

	public ArticleBO bangLink(ArticleBO article);

}
