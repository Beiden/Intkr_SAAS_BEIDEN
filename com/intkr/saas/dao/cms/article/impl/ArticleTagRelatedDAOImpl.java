package com.intkr.saas.dao.cms.article.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.ArticleTagRelatedDAO;
import com.intkr.saas.domain.dbo.article.ArticleTagRelatedDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:21:47
 * @version 1.0
 */
@Repository("ArticleTagRelatedDAO")
public class ArticleTagRelatedDAOImpl extends BaseDAOImpl<ArticleTagRelatedDO> implements ArticleTagRelatedDAO {

	public String getNameSpace() {
		return "articleTagRelated";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
