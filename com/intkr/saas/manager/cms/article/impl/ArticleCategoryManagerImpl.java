package com.intkr.saas.manager.cms.article.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.cms.article.ArticleCategoryDAO;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.domain.bo.article.ArticleCategoryRelatedBO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelatedManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 09:32:27
 * @version 1.0
 */
@Repository("ArticleCategoryManager")
public class ArticleCategoryManagerImpl extends BaseManagerImpl<ArticleCategoryBO, ArticleCategoryDO> implements ArticleCategoryManager {

	@Resource
	private ArticleCategoryDAO articleCategoryDAO;

	@Resource
	private ArticleCategoryRelatedManager categoryRelatedManager;

	public BaseDAO<ArticleCategoryDO> getBaseDAO() {
		return articleCategoryDAO;
	}

	public List<ArticleCategoryBO> getFirstCategoryFull(Long saasId) {
		ArticleCategoryBO query = new ArticleCategoryBO();
		query.setSaasId(saasId);
		query.set_pageSize(10000);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		List<ArticleCategoryBO> categorys = select(query);
		List<ArticleCategoryBO> firstCategorys = new ArrayList<ArticleCategoryBO>();
		Map<Long, ArticleCategoryBO> map = new HashMap<Long, ArticleCategoryBO>();
		for (ArticleCategoryBO bo : categorys) {
			map.put(bo.getId(), bo);
		}
		for (ArticleCategoryBO bo : categorys) {
			if (bo.getPid() != null && map.containsKey(bo.getPid())) {
				ArticleCategoryBO parent = map.get(bo.getPid());
				parent.addChild(bo);
				bo.setParent(parent);
			}
		}
		for (ArticleCategoryBO bo : categorys) {
			if (bo.getParent() == null) {
				firstCategorys.add(bo);
			}
		}
		return firstCategorys;
	}

	public ArticleBO fill(ArticleBO article) {
		if (article == null) {
			return article;
		}
		ArticleCategoryRelatedBO query = new ArticleCategoryRelatedBO();
		query.set_pageSize(1000);
		query.setArticleId(article.getId());
		List<Long> categoryIds = categoryRelatedManager.getCategoryIds(article.getId());
		List<ArticleCategoryBO> categorys = getByIds(categoryIds);
		article.setCategorys(categorys);
		return article;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (Object obj : list) {
			if (obj instanceof ArticleBO) {
				fill((ArticleBO) obj);
			}
		}
		return list;
	}

	public List<ArticleCategoryBO> getByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<ArticleCategoryBO>();
		}
		ArticleCategoryBO query = new ArticleCategoryBO();
		query.setQuery("ids", ids);
		query.set_pageSize(1000);
		return select(query);
	}

}
