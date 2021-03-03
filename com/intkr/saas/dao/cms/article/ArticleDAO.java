package com.intkr.saas.dao.cms.article;

import com.intkr.saas.domain.dbo.article.ArticleDO;
import com.intkr.saas.dao.BaseDAO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:32:55
 * @version 1.0
 */
public interface ArticleDAO extends BaseDAO<ArticleDO> {

	public boolean recover(Long articleId);

	public boolean deleteReal(Long articleId);

}
