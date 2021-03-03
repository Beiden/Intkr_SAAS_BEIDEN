package com.intkr.saas.manager.cms.article.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.ArticleCategoryRelationshipDAO;
import com.intkr.saas.domain.bo.article.ArticleCategoryRelationshipBO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryRelationshipDO;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelationshipManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:21:52
 * @version 1.0
 */
@Repository("ArticleCategoryRelationshipManager")
public class ArticleCategoryRelationshipManagerImpl extends BaseManagerImpl<ArticleCategoryRelationshipBO, ArticleCategoryRelationshipDO> implements ArticleCategoryRelationshipManager {

	@Resource
	private ArticleCategoryRelationshipDAO articleCategoryRelationshipDAO;

	public BaseDAO<ArticleCategoryRelationshipDO> getBaseDAO() {
		return articleCategoryRelationshipDAO;
	}

}
