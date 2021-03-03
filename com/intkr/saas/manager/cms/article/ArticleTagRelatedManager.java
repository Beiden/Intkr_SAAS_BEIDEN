package com.intkr.saas.manager.cms.article;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleTagRelatedBO;
import com.intkr.saas.domain.dbo.article.ArticleTagRelatedDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:21:47
 * @version 1.0
 */
public interface ArticleTagRelatedManager extends BaseManager<ArticleTagRelatedBO, ArticleTagRelatedDO> {

	public List<ArticleTagRelatedBO> selectByRelatedId(Long relatedId);

}
