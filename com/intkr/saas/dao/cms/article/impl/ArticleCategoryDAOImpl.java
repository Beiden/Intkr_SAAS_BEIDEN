package com.intkr.saas.dao.cms.article.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.ArticleCategoryDAO;
import com.intkr.saas.domain.dbo.article.ArticleCategoryDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 09:32:27
 * @version 1.0
 */
@Repository("ArticleCategoryDAO")
public class ArticleCategoryDAOImpl extends BaseDAOImpl<ArticleCategoryDO> implements ArticleCategoryDAO {

	public String getNameSpace() {
		return "articleCategory";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
