package com.intkr.saas.module.toolbox.cms;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.article.ArticleCategoryBO;
import com.intkr.saas.domain.type.cms.ArticleStatus;
import com.intkr.saas.manager.cms.article.ArticleCategoryManager;
import com.intkr.saas.manager.cms.article.ArticleManager;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.article.article.ArticleAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class ArticleDS extends BaseToolBox {

	private static Cache<String, ArticleBO> lastArticleCache = CacheBuilder.newBuilder()//
			.expireAfterAccess(30, TimeUnit.MINUTES).//
			maximumSize(10000).//
			build();

	private ArticleManager articleManager = IOC.get("ArticleManager");

	private ArticleCategoryManager categoryManager = IOC.get("ArticleCategoryManager");

	private UserManager accountManager = IOC.get("UserManager");

	private TagManager tagManager = IOC.get("TagManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	private ArticleCommentManager commentManager = IOC.get("CommentManager");

	/**
	 * 根据ID获得文章详细信息
	 * 
	 * @param articleId
	 * @return
	 */
	public ArticleBO getById(Object articleIdObject) {
		try {
			if (articleIdObject == null || "".equals(articleIdObject)) {
				return null;
			}
			Long articleId = null;
			if (articleIdObject instanceof Long) {
				articleId = (Long) articleIdObject;
			} else if (articleIdObject instanceof String) {
				articleId = Long.valueOf((String) articleIdObject);
			}
			if (!IdEngine.isValidate(articleId)) {
				return null;
			}
			ArticleBO article = articleManager.get(articleId);
			if (article == null) {
				return null;
			}
			return article;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 填充类目信息
	 * 
	 * @param article
	 */
	public void fillCategorys(ArticleBO article) {
		if (article == null) {
			return;
		}
		categoryManager.fill(article);
	}

	/**
	 * 填充类目信息
	 * 
	 * @param article
	 */
	public void fillComment(ArticleBO article) {
		if (article == null) {
			return;
		}
		commentManager.fill(article);
	}

	/**
	 * 是否有封面图
	 * 
	 * @param article
	 */
	public String getCoverImg(ArticleBO article) {
		try {
			if (article == null) {
				return "/asset/img/demo/08.jpg";
			}
			if (IdEngine.isValidate(article.getSpeImgId())) {
				if (article.getSpeImg() == null) {
					imgManager.fill(article);
				}
				return article.getSpeImg().getUri();
			}
			Document doc = Jsoup.parse(article.getContent());
			Elements imgs = doc.select("img");
			if (imgs.isEmpty()) {
				return "/asset/img/demo/08.jpg";
			} else {
				return imgs.get(0).attr("src");
			}
		} catch (Exception e) {
			return "/asset/img/demo/08.jpg";
		}
	}

	/**
	 * 填充标签信息
	 * 
	 * @param article
	 */
	public void fillTags(ArticleBO article) {
		if (article == null) {
			return;
		}
		tagManager.fill(article);
	}

	/**
	 * 填充用户信息
	 * 
	 * @param article
	 */
	public void fillUser(ArticleBO article) {
		if (article == null) {
			return;
		}
		accountManager.fill(article);
	}

	/**
	 * 自增加文章浏览次数
	 * 
	 * @param articleId
	 */
	public void increaseViewCount(Object articleIdObject) {
		Long articleId = null;
		if (articleIdObject instanceof Long) {
			articleId = (Long) articleIdObject;
		} else if (articleIdObject instanceof String) {
			articleId = Long.valueOf((String) articleIdObject);
		}
		articleManager.increaseViewCount(articleId);
	}

	public ArticleBO selectAndCount(HttpServletRequest request) {
		ArticleBO query = ArticleAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		articleManager.selectAndCount(query);
		accountManager.fill(query.getDatas());
		categoryManager.fill(query.getDatas());
		return query;
	}

	public ArticleBO selectBefore(HttpServletRequest request, String time) {
		if (time == null || "".equals(time)) {
			return null;
		}
		RequestUtil.setParam(request, "maxPublicTime", time);
        RequestUtil.setParam(request, "orderBy", "public_time");
        RequestUtil.setParam(request, "order", "desc");
		RequestUtil.setParam(request, "status", "public");
		ArticleBO query = ArticleAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		articleManager.selectAndCount(query);
		accountManager.fill(query.getDatas());
		categoryManager.fill(query.getDatas());
		return query;
	}

	public ArticleBO selectAndCount(Map<String, Object> param) {
		ArticleBO query = ArticleAction.getParameter(param);
		articleManager.selectAndCount(query);
		accountManager.fill(query.getDatas());
		categoryManager.fill(query.getDatas());
		return query;
	}

	public void increaseViewCount(String articleId) {
		try {
			increaseViewCount(Long.valueOf(articleId));
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public ArticleBO getLast(HttpServletRequest request, final String categoryName) {
		try {
			final Long saasId = SessionClient.getSaasId(request);
			return lastArticleCache.get(saasId + "|" + categoryName, new Callable<ArticleBO>() {
				public ArticleBO call() throws Exception {
					ArticleCategoryBO category = null;
					if (categoryName == null || "".equals(categoryName)) {

					} else {
						ArticleCategoryBO categoryQuery = new ArticleCategoryBO();
						categoryQuery.setSaasId(saasId);
						categoryQuery.setName(categoryName);
						category = categoryManager.selectOne(categoryQuery);
					}
					ArticleBO query = new ArticleBO();
					if (category != null) {
						query.setQuery("categoryId", category.getId());
					}
					query.setSaasId(saasId);
					query.setStatus(ArticleStatus.Public.getCode());
					query.setQuery("orderBy", "public_time");
					query.setQuery("order", "desc");
					articleManager.select(query);
					for (Object obj : query.getDatas()) {
						ArticleBO article = (ArticleBO) obj;
						String coverImgUrl = getCoverImg(article);
						article.setProperty("coverImgUrl", coverImgUrl);
					}
					return query;
				}
			});
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	private ArticleUtil util = new ArticleUtil();

	public ArticleUtil getUtil() {
		return util;
	}

	public void setUtil(ArticleUtil util) {
		this.util = util;
	}

	public static class ArticleUtil {

		/**
		 * 
		 * @param content
		 * @param length
		 * @return
		 */
		public static String getSummary(String content, Integer length) {
			Document doc = Jsoup.parse(content);
			content = doc.text();
			if (content.length() < length) {
				return content;
			} else {
				return content.substring(0, length);
			}
		}

		/**
		 * 获得摘要信息
		 * 
		 * @param article
		 * @param length
		 * @return
		 */
		public static String getSummary(ArticleBO article, Integer length) {
			Document doc = Jsoup.parse(article.getContent());
			String content = doc.text();
			if (content == null || content.length() < length) {
				return content;
			}
			return content.substring(0, length);
		}

		public static String getTitleSimpl(ArticleBO article, Integer length) {
			if (article.getContent() == null || article.getContent().length() < length) {
				return article.getContent();
			} else {
				Document doc = Jsoup.parse(article.getTitle());
				String content = doc.text();
				if (content == null || content.length() < length) {
					return content;
				}
				return content.substring(0, length) + "...";
			}
		}

		/**
		 * 获得发布日期
		 * 
		 * @param article
		 * @return
		 */
		public static String getPubDay(ArticleBO article) {
			if (article == null || article.getPublicTime() == null) {
				return null;
			}
			String day = DateUtil.format("dd", article.getPublicTime());
			return day;
		}

		/**
		 * 获得发布的月份
		 * 
		 * @param article
		 * @return
		 */
		public static String getPubMooth(ArticleBO article) {
			if (article == null || article.getPublicTime() == null) {
				return null;
			}
			String day = DateUtil.format("MM", article.getPublicTime());
			return day;
		}

		/**
		 * 获得发布的月份(English)
		 * 
		 * @param article
		 * @return
		 */
		public static String getPubMoothEn(ArticleBO article) {
			String day = getPubMooth(article);
			if ("01".equals(day)) {
				return "Jan";
			} else if ("02".equals(day)) {
				return "Feb";
			} else if ("03".equals(day)) {
				return "Mar";
			} else if ("04".equals(day)) {
				return "Apr";
			} else if ("05".equals(day)) {
				return "May";
			} else if ("06".equals(day)) {
				return "Jun";
			} else if ("07".equals(day)) {
				return "Jul";
			} else if ("08".equals(day)) {
				return "Aug";
			} else if ("09".equals(day)) {
				return "Sep";
			} else if ("10".equals(day)) {
				return "Oct";
			} else if ("11".equals(day)) {
				return "Nov";
			} else if ("12".equals(day)) {
				return "Dec";
			}
			return day;
		}

		public static String getPubMoothZH(ArticleBO article) {
			String day = getPubMooth(article);
			if ("01".equals(day)) {
				return "一月";
			} else if ("02".equals(day)) {
				return "二月";
			} else if ("03".equals(day)) {
				return "三月";
			} else if ("04".equals(day)) {
				return "四月";
			} else if ("05".equals(day)) {
				return "五月";
			} else if ("06".equals(day)) {
				return "六月";
			} else if ("07".equals(day)) {
				return "七月";
			} else if ("08".equals(day)) {
				return "八月";
			} else if ("09".equals(day)) {
				return "九月";
			} else if ("10".equals(day)) {
				return "十月";
			} else if ("11".equals(day)) {
				return "十一月";
			} else if ("12".equals(day)) {
				return "十二月";
			}
			return day;
		}

	}

}
