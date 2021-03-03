package com.intkr.saas.dao.cms.article.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.ArticleCategoryRelatedDAO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryRelatedDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:24:49
 * @version 1.0
 */
@Repository("ArticleCategoryRelatedDAO")
public class ArticleCategoryRelatedDAOImpl extends BaseDAOImpl<ArticleCategoryRelatedDO> implements ArticleCategoryRelatedDAO {

	public String getNameSpace() {
		return "articleCategoryRelated";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
