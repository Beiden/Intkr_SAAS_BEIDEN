package com.intkr.saas.manager.cms.article.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.article.TagDAO;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleTagRelatedBO;
import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.page.PageTagRelatedBO;
import com.intkr.saas.domain.dbo.article.ArticleTagDO;
import com.intkr.saas.manager.cms.article.ArticleTagRelatedManager;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.cms.page.PageTagRelatedManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:19:26
 * @version 1.0
 */
@Repository("TagManager")
public class TagManagerImpl extends BaseManagerImpl<ArticleTagBO, ArticleTagDO> implements TagManager {

	@Resource
	private TagDAO articleTagDAO;

	@Resource
	private ArticleTagRelatedManager articleTagRelatedManager;

	@Resource
	private PageTagRelatedManager pageTagRelatedManager;

	public BaseDAO<ArticleTagDO> getBaseDAO() {
		return articleTagDAO;
	}

	public ArticleBO fill(ArticleBO article) {
		if (article == null) {
			return article;
		}
		ArticleTagRelatedBO query = new ArticleTagRelatedBO();
		query.set_pageSize(1000);
		query.setRelatedId(article.getId());
		List<ArticleTagRelatedBO> list = articleTagRelatedManager.select(query);
		List<ArticleTagBO> tags = new ArrayList<ArticleTagBO>();
		for (ArticleTagRelatedBO bo : list) {
			ArticleTagBO tag = get(bo.getTagId());
			if (tag != null) {
				tags.add(tag);
			}
		}
		article.setTags(tags);
		return article;
	}

	public PageBO fill(PageBO article) {
		if (article == null) {
			return article;
		}
		PageTagRelatedBO query = new PageTagRelatedBO();
		query.set_pageSize(1000);
		query.setRelatedId(article.getId());
		List<PageTagRelatedBO> list = pageTagRelatedManager.select(query);
		List<ArticleTagBO> tags = new ArrayList<ArticleTagBO>();
		for (PageTagRelatedBO bo : list) {
			ArticleTagBO tag = get(bo.getTagId());
			if (tag != null) {
				tags.add(tag);
			}
		}
		article.setTags(tags);
		return article;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (Object obj : list) {
			if (obj instanceof ArticleBO) {
				fill((ArticleBO) obj);
			} else if (obj instanceof PageBO) {
				fill((PageBO) obj);
			}
		}
		return list;
	}

	public ArticleTagBO getByName(String name) {
		ArticleTagBO query = new ArticleTagBO();
		query.setName(name);
		return selectOne(query);
	}

	public ArticleTagBO selectAll2() {
		ArticleTagBO query = new ArticleTagBO();
		query.set_pageSize(10000);
		select(query);
		return query;
	}

	public ArticleBO bangLink(ArticleBO article) {
		if (article == null) {
			return article;
		}
		ArticleTagBO tagList = selectAll2();
		for (Object obj : tagList.getDatas()) {
			ArticleTagBO tag = (ArticleTagBO) obj;
			Pattern pattern = Pattern.compile(tag.getName());
			Matcher matcher = pattern.matcher(article.getContent());
			int findCount = 0;
			while (matcher.find()) {
				matcher.group();
				findCount++;
			}

		}
		return article;
	}

	public static void main(String[] args) {
		TagManagerImpl manager = new TagManagerImpl();
		ArticleManagerImpl articleManager = new ArticleManagerImpl();
		ArticleBO article = articleManager.get(4607);
		manager.bangLink(article);
	}

}
