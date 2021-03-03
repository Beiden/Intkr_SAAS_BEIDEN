package com.intkr.saas.module.action.article.page;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.page.PageCategoryRelatedBO;
import com.intkr.saas.domain.bo.page.PageTagRelatedBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.cms.page.PageCategoryRelatedManager;
import com.intkr.saas.manager.cms.page.PageManager;
import com.intkr.saas.manager.cms.page.PageTagRelatedManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class PageAction extends BaseAction<PageBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private PageManager articleManager = IOC.get(PageManager.class);

	private PageCategoryRelatedManager categoryRelatedManager = IOC.get(PageCategoryRelatedManager.class);

	private PageTagRelatedManager pageTagRelatedManager = IOC.get(PageTagRelatedManager.class);

	private TagManager tagManager = IOC.get(TagManager.class);

	public PageBO getBO(HttpServletRequest request) {
		PageBO cmsPageBO = getParameter(request);
		return cmsPageBO;
	}

	public static PageBO getParameter(HttpServletRequest request) {
		PageBO cmsPageBO = new PageBO();
		cmsPageBO.setId(RequestUtil.getParam(request, "id", Long.class));
		cmsPageBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		cmsPageBO.setType(RequestUtil.getParam(request, "type"));
		cmsPageBO.setForm(RequestUtil.getParam(request, "form"));
		cmsPageBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		cmsPageBO.setTitle(RequestUtil.getParam(request, "title"));
		cmsPageBO.setStatus(RequestUtil.getParam(request, "status"));
		cmsPageBO.setKeywords(RequestUtil.getParam(request, "keywords"));
		cmsPageBO.setDescription(RequestUtil.getParam(request, "description"));
		cmsPageBO.setSummary(RequestUtil.getParam(request, "summary"));
		cmsPageBO.setContent(RequestUtil.getParam(request, "content"));
		if (RequestUtil.existParam(request, "publicTime")) {
			cmsPageBO.setPublicTime(DateUtil.parse(RequestUtil.getParam(request, "publicTime")));
		}
		cmsPageBO.setAllowComment(RequestUtil.getParam(request, "allowComment", Integer.class));
		cmsPageBO.setSpeImgId(RequestUtil.getParam(request, "speImgId", Long.class));
		cmsPageBO.setViewCount(RequestUtil.getParam(request, "viewCount", Integer.class));
		cmsPageBO.setCommentCount(RequestUtil.getParam(request, "commentCount", Integer.class));
		cmsPageBO.setPraiseUpCount(RequestUtil.getParam(request, "praiseUpCount", Integer.class));
		cmsPageBO.setPraiseDownCount(RequestUtil.getParam(request, "praiseDownCount", Integer.class));
		cmsPageBO.setLikeCount(RequestUtil.getParam(request, "likeCount", Integer.class));
		cmsPageBO.setCollectCount(RequestUtil.getParam(request, "collectCount", Integer.class));
		cmsPageBO.setAttentionCount(RequestUtil.getParam(request, "attentionCount", Integer.class));
		cmsPageBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, cmsPageBO);
		return cmsPageBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return articleManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageBO bo = getBO(request);
		articleManager.insert(bo);
		insertTags(request, bo);
		insertCategoryRelated(request, bo);
		request.setAttribute("msg", "添加成功");
		request.setAttribute("result", true);
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageBO article = getBO(request);
		articleManager.update(article);
		deleteTagRelated(article);
		deleteCategoryRelated(article);
		insertTags(request, article);
		insertCategoryRelated(request, article);
		request.setAttribute("msg", "更新成功");
		request.setAttribute("result", true);
	}

	private void deleteTagRelated(PageBO bo) {
		List<PageTagRelatedBO> list = pageTagRelatedManager.selectByRelatedId(bo.getId());
		for (PageTagRelatedBO cr : list) {
			pageTagRelatedManager.delete(cr.getId());
		}
	}

	private void deleteCategoryRelated(PageBO bo) {
		List<PageCategoryRelatedBO> list = categoryRelatedManager.selectByPageId(bo.getId());
		for (PageCategoryRelatedBO cr : list) {
			categoryRelatedManager.delete(cr.getId());
		}
	}

	private void insertTags(HttpServletRequest request, PageBO bo) {
		String[] tags = request.getParameterValues("tags");
		if (tags != null) {
			for (String tagName : tags) {
				ArticleTagBO tag = getTagBO(request, tagName);
				PageTagRelatedBO cr = new PageTagRelatedBO();
				cr.setId(pageTagRelatedManager.getId());
				cr.setTagId(tag.getId());
				cr.setRelatedId(bo.getId());
				pageTagRelatedManager.insert(cr);
			}
		}
	}

	private ArticleTagBO getTagBO(HttpServletRequest request, String tagName) {
		ArticleTagBO tag = tagManager.getByName(tagName);
		if (tag == null) {
			tag = new ArticleTagBO();
			tag.setId(tagManager.getId());
			tag.setName(tagName);
			tagManager.insert(tag);
		}
		return tag;
	}

	private void insertCategoryRelated(HttpServletRequest request, PageBO page) {
		String[] categorys = request.getParameterValues("categorys");
		if (categorys != null) {
			for (String categoryId : categorys) {
				PageCategoryRelatedBO cr = new PageCategoryRelatedBO();
				cr.setId(categoryRelatedManager.getId());
				cr.setCategoryId(Long.valueOf(categoryId));
				cr.setPageId(page.getId());
				categoryRelatedManager.insert(cr);
			}
		}
	}

}
