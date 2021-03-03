package com.intkr.saas.dao.cms.article.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.ArticleCategoryRelationshipDAO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryRelationshipDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:21:52
 * @version 1.0
 */
@Repository("ArticleCategoryRelationshipDAO")
public class ArticleCategoryRelationshipDAOImpl extends BaseDAOImpl<ArticleCategoryRelationshipDO> implements ArticleCategoryRelationshipDAO {

	public String getNameSpace() {
		return "articleCategoryRelationship";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
