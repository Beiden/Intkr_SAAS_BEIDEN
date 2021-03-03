package com.intkr.saas.dao.cms.article.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.cms.article.ArticleDAO;
import com.intkr.saas.domain.dbo.article.ArticleDO;
import com.intkr.saas.engine.db.MyBatisEngine;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:32:55
 * @version 1.0
 */
@Repository("ArticleDAO")
public class ArticleDAOImpl extends BaseDAOImpl<ArticleDO> implements ArticleDAO {

	public String getNameSpace() {
		return "article";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

	public boolean recover(Long articleId) {
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", articleId);
			session.delete(getNameSpace() + ".recover", map);
			session.commit();
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean deleteReal(Long articleId) {
		SqlSession session = MyBatisEngine.getSession(getDataSourceName());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("primary", articleId);
			session.delete(getNameSpace() + ".deleteReal", map);
			session.commit();
		} finally {
			session.close();
		}
		return true;
	}

}
