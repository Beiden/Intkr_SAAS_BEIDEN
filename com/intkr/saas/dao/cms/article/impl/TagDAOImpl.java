package com.intkr.saas.dao.cms.article.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.TagDAO;
import com.intkr.saas.domain.dbo.article.ArticleTagDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:19:26
 * @version 1.0
 */
@Repository("TagDAO")
public class TagDAOImpl extends BaseDAOImpl<ArticleTagDO> implements TagDAO {

	public String getNameSpace() {
		return "articleTag";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
