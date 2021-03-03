package com.intkr.saas.manager.cms.page;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleCommentBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.dbo.page.PageDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:00:20
 * @version 1.0
 */
public interface PageManager extends BaseManager<PageBO, PageDO> {

	public boolean increaseViewCount(Long articleId);

	public ArticleCommentBO fill(ArticleCommentBO comment);

	public List<?> fill(List<?> list);

}
