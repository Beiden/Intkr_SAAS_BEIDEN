package com.intkr.saas.manager.cms.article.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.ArticleTagRelatedDAO;
import com.intkr.saas.domain.bo.article.ArticleTagRelatedBO;
import com.intkr.saas.domain.dbo.article.ArticleTagRelatedDO;
import com.intkr.saas.manager.cms.article.ArticleTagRelatedManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:21:47
 * @version 1.0
 */
@Repository("ArticleTagRelatedManager")
public class ArticleTagRelatedManagerImpl extends BaseManagerImpl<ArticleTagRelatedBO, ArticleTagRelatedDO> implements ArticleTagRelatedManager {

	@Resource
	private ArticleTagRelatedDAO articleTagRelatedDAO;

	public BaseDAO<ArticleTagRelatedDO> getBaseDAO() {
		return articleTagRelatedDAO;
	}

	public List<ArticleTagRelatedBO> selectByRelatedId(Long relatedId) {
		ArticleTagRelatedBO query = new ArticleTagRelatedBO();
		query.set_pageSize(1000);
		query.setRelatedId(relatedId);
		return select(query);
	}

}
