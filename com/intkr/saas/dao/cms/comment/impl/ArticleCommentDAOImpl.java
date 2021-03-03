package com.intkr.saas.dao.cms.comment.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.comment.ArticleCommentDAO;
import com.intkr.saas.domain.dbo.article.ArticleCommentDO;
import com.intkr.saas.dao.BaseDAOImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:10:54
 * @version 1.0
 */
@Repository("CommentDAO")
public class ArticleCommentDAOImpl extends BaseDAOImpl<ArticleCommentDO> implements ArticleCommentDAO {

	public String getNameSpace() {
		return "cmsComment";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
