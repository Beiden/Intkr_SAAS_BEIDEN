package com.intkr.saas.manager.cms.article.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.cms.article.ArticleCategoryRelatedDAO;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCategoryRelatedBO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryRelatedDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelatedManager;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:24:49
 * @version 1.0
 */
@Repository("ArticleCategoryRelatedManager")
public class ArticleCategoryRelatedManagerImpl extends BaseManagerImpl<ArticleCategoryRelatedBO, ArticleCategoryRelatedDO> implements ArticleCategoryRelatedManager {

	@Resource
	private ArticleCategoryRelatedDAO articleCategoryRelatedDAO;

	public BaseDAO<ArticleCategoryRelatedDO> getBaseDAO() {
		return articleCategoryRelatedDAO;
	}

	public List<ArticleCategoryRelatedBO> selectByArticleId(Long articleId) {
		ArticleCategoryRelatedBO query = new ArticleCategoryRelatedBO();
		query.set_pageSize(1000);
		query.setArticleId(articleId);
		return select(query);
	}

	public static void main(String[] args) {
		ArticleManager articleManager = IOC.get("ArticleManager");
		ArticleCategoryRelatedManager categoryRelatedManager = IOC.get("ArticleCategoryRelatedManager");
		ArticleBO query = new ArticleBO();
		query.setSaasId(1L);
		query.set_pageSize(100000);
		List<ArticleBO> list = articleManager.select(query);
		for (ArticleBO article : list) {
			ArticleCategoryRelatedBO cr = new ArticleCategoryRelatedBO();
			cr.setSaasId(1L);
			cr.setArticleId(article.getId());
			{
				cr.setId(categoryRelatedManager.getId());
				cr.setCategoryId(2204L);
				categoryRelatedManager.insert(cr);
			}
			{
				cr.setId(categoryRelatedManager.getId());
				cr.setCategoryId(2205L);
				categoryRelatedManager.insert(cr);
			}
			{
				cr.setId(categoryRelatedManager.getId());
				cr.setCategoryId(2206L);
				categoryRelatedManager.insert(cr);
			}
			{
				cr.setId(categoryRelatedManager.getId());
				cr.setCategoryId(2207L);
				categoryRelatedManager.insert(cr);
			}
		}
	}

	public List<Long> getCategoryIds(Long articleId) {
		if (articleId == null) {
			return new ArrayList<Long>();
		}
		ArticleCategoryRelatedBO query = new ArticleCategoryRelatedBO();
		query.set_pageSize(1000);
		query.setArticleId(articleId);
		List<ArticleCategoryRelatedBO> list = select(query);
		List<Long> categoryIds = new ArrayList<Long>();
		for (ArticleCategoryRelatedBO bo : list) {
			categoryIds.add(bo.getCategoryId());
		}
		return categoryIds;
	}

}
