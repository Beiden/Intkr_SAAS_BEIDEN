package com.intkr.saas.module.action.article.article;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCategoryRelatedBO;
import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.domain.bo.article.ArticleTagRelatedBO;
import com.intkr.saas.domain.type.cms.ArticleStatus;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.cms.article.ArticleCategoryRelatedManager;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.cms.article.ArticleTagRelatedManager;
import com.intkr.saas.manager.cms.article.TagManager;
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
public class ArticleAction extends BaseAction<ArticleBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ArticleManager articleManager = IOC.get(ArticleManager.class);

	private ArticleCategoryRelatedManager categoryRelatedManager = IOC.get(ArticleCategoryRelatedManager.class);

	private ArticleTagRelatedManager articleTagRelatedManager = IOC.get(ArticleTagRelatedManager.class);

	private TagManager tagManager = IOC.get(TagManager.class);

	private ArticleBO getBOForAddUpdate(HttpServletRequest request) {
		ArticleBO article = getParameter(request);
		article.setSpeImgId(RequestUtil.getParam(request, "speImgId", Long.class, -1l));
		if (RequestUtil.existParam(request, "customColumnName")) {
			String[] customColumnNames = request.getParameterValues("customColumnName");
			String[] customColumnValues = request.getParameterValues("customColumnValue");
			Map<String, String> customColumn = new HashMap<String, String>();
			for (int i = 0; i < customColumnNames.length; i++) {
				customColumn.put(customColumnNames[i], customColumnValues[i]);
			}
			article.setFeature("customColumn", customColumn);
		}
		return article;
	}

	public ArticleBO getBO(HttpServletRequest request) {
		ArticleBO article = getParameter(request);
		return article;
	}

	public static ArticleBO getParameter(Object request) {
		ArticleBO article = new ArticleBO();
		article.setId(RequestUtil.getParam(request, "id", Long.class));
		article.setIsDeleted(RequestUtil.getParam(request, "isDeleted", 0));
		article.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		article.setType(RequestUtil.getParam(request, "type"));
		article.setForm(RequestUtil.getParam(request, "form"));
		article.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		article.setTitle(RequestUtil.getParam(request, "title"));
		article.setStatus(RequestUtil.getParam(request, "status"));
		article.setKeywords(RequestUtil.getParam(request, "keywords"));
		article.setDescription(RequestUtil.getParam(request, "description"));
		article.setSummary(RequestUtil.getParam(request, "summary"));
		article.setContent(RequestUtil.getParam(request, "content"));
		if (RequestUtil.existParam(request, "publicTime")) {
			article.setPublicTime(DateUtil.parse(RequestUtil.getParam(request, "publicTime")));
		}
		if (RequestUtil.existParam(request, "categoryId")) {
			article.setQuery("categoryId", RequestUtil.getParam(request, "categoryId"));
		}
		if (RequestUtil.existParam(request, "searchWord")) {
			article.setQuery("searchWord", RequestUtil.getParam(request, "searchWord") + "%");
		}
		if (RequestUtil.existParam(request, "dayEnd")) {
			article.setQuery("dayEnd", RequestUtil.getParam(request, "dayEnd"));
		}
		article.setAllowComment(RequestUtil.getParam(request, "allowComment", Integer.class));
		article.setSpeImgId(RequestUtil.getParam(request, "speImgId", Long.class));
		article.setViewCount(RequestUtil.getParam(request, "viewCount", Integer.class));
		article.setCommentCount(RequestUtil.getParam(request, "commentCount", Integer.class));
		article.setPraiseUpCount(RequestUtil.getParam(request, "praiseUpCount", Integer.class));
		article.setPraiseDownCount(RequestUtil.getParam(request, "praiseDownCount", Integer.class));
		article.setLikeCount(RequestUtil.getParam(request, "likeCount", Integer.class));
		article.setCollectCount(RequestUtil.getParam(request, "collectCount", Integer.class));
		article.setAttentionCount(RequestUtil.getParam(request, "attentionCount", Integer.class));
		article.setFeature(RequestUtil.getParam(request, "feature"));
		if (RequestUtil.existParam(request, "minPublicTime")) {
			article.setQuery("minPublicTime", RequestUtil.getParam(request, "minPublicTime"));
		}
		PagerUtil.initPage(request, article);
		return article;
	}

	public BaseManager<?, ?> getBaseManager() {
		return articleManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArticleBO article = getBOForAddUpdate(request);
		article.setSaasId(SessionClient.getSaasId(request));
		if (article.getPublicTime() != null && article.getPublicTime().after(new Date())) {
			article.setStatus(ArticleStatus.Timing.getCode());
		}
		{// 初始化插入值
			article.setViewCount(0);
			article.setCommentCount(0);
			article.setPraiseUpCount(0);
			article.setPraiseDownCount(0);
			article.setLikeCount(0);
			article.setCollectCount(0);
			article.setAttentionCount(0);
			if (article.getAllowComment() == null) {
				article.setAllowComment(2);
			}
		}
		articleManager.insert(article);
		insertTags(request, article);
		insertCategoryRelated(request, article);
		request.setAttribute("msg", "文章发布成功");
		request.setAttribute("result", true);
	}

	public void doIncreaseViewCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long articleId = RequestUtil.getParam(request, "articleId", Long.class);
		articleManager.increaseViewCount(articleId);
		request.setAttribute("msg", "操作成功");
		request.setAttribute("result", true);
	}

	public void doRecover(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long articleId = RequestUtil.getParam(request, "articleId", Long.class);
		ArticleBO article = articleManager.get(articleId);
		if (!SessionClient.isSaasData(request, article)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "文章不存在！");
			return;
		}
		articleManager.recover(articleId);
		request.setAttribute("msg", "操作成功");
		request.setAttribute("result", true);
	}

	// @ParamCheck(schema = "action/article/ArticleAction/doUpdate.schema")
	@StartTransaction
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArticleBO article = getBOForAddUpdate(request);
		if (article.getPublicTime() != null && article.getPublicTime().after(new Date())) {
			article.setStatus(ArticleStatus.Timing.getCode());
		}
		articleManager.update(article);
		deleteTagRelated(article);
		deleteCategoryRelated(article);
		insertTags(request, article);
		insertCategoryRelated(request, article);
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新成功");
	}

	private void deleteTagRelated(ArticleBO bo) {
		List<ArticleTagRelatedBO> list = articleTagRelatedManager.selectByRelatedId(bo.getId());
		for (ArticleTagRelatedBO cr : list) {
			articleTagRelatedManager.delete(cr.getId());
		}
	}

	private void deleteCategoryRelated(ArticleBO bo) {
		List<ArticleCategoryRelatedBO> list = categoryRelatedManager.selectByArticleId(bo.getId());
		for (ArticleCategoryRelatedBO cr : list) {
			categoryRelatedManager.delete(cr.getId());
		}
	}

	private void insertTags(HttpServletRequest request, ArticleBO bo) {
		String[] tags = request.getParameterValues("tags");
		if (tags != null) {
			for (String tagName : tags) {
				ArticleTagBO tag = getTagBO(request, tagName);
				ArticleTagRelatedBO cr = new ArticleTagRelatedBO();
				cr.setId(articleTagRelatedManager.getId());
				cr.setSaasId(SessionClient.getSaasId(request));
				cr.setTagId(tag.getId());
				cr.setRelatedId(bo.getId());
				articleTagRelatedManager.insert(cr);
			}
		}
	}

	private ArticleTagBO getTagBO(HttpServletRequest request, String tagName) {
		ArticleTagBO tag = tagManager.getByName(tagName);
		if (tag == null) {
			tag = new ArticleTagBO();
			tag.setId(tagManager.getId());
			tag.setSaasId(SessionClient.getSaasId(request));
			tag.setName(tagName);
			tagManager.insert(tag);
		}
		return tag;
	}

	private void insertCategoryRelated(HttpServletRequest request, ArticleBO bo) {
		String[] categorys = request.getParameterValues("categorys");
		if (categorys != null) {
			for (String categoryId : categorys) {
				ArticleCategoryRelatedBO cr = new ArticleCategoryRelatedBO();
				cr.setId(categoryRelatedManager.getId());
				cr.setSaasId(SessionClient.getSaasId(request));
				cr.setCategoryId(Long.valueOf(categoryId));
				cr.setArticleId(bo.getId());
				categoryRelatedManager.insert(cr);
			}
		}
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doDeleteOne(request, response);
	}

	public void doDeleteReal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long articleId = RequestUtil.getParam(request, "deleteId", Long.class);
		articleManager.deleteReal(articleId);
		request.setAttribute("msg", "操作成功");
		request.setAttribute("result", true);
	}

}
