package com.intkr.saas.module.toolbox.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.type.cms.ArticleStatus;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.manager.cms.comment.ArticleCommentManager;
import com.intkr.saas.manager.cms.page.PageCategoryManager;
import com.intkr.saas.manager.cms.page.PageManager;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class PageDS extends BaseToolBox {

	private PageManager articleManager = IOC.get("PageManager");

	private PageCategoryManager categoryManager = IOC.get("PageCategoryManager");

	private UserManager userManager = IOC.get("UserManager");

	private TagManager tagManager = IOC.get("TagManager");

	private ImgManager imgManager = IOC.get("ImgManager");

	private ArticleCommentManager commentManager = IOC.get("CommentManager");

	public PageBO getById(String articleId) {
		return getById(Long.valueOf(articleId));
	}

	public void increaseViewCount(String articleId) {
		articleManager.increaseViewCount(Long.valueOf(articleId));
	}

	/**
	 * 根据ID获得文章详细信息
	 * 
	 * @param articleId
	 * @return
	 */
	public PageBO getById(Long articleId) {
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		PageBO article = articleManager.get(articleId);
		if (article == null) {
			return null;
		}
		if (ArticleStatus.Public.getCode().equals(article.getStatus())) {// 文章是否是已发布状态
			return article;
		}
		return null;
	}

	/**
	 * 填充类目信息
	 * 
	 * @param article
	 */
	public void fillCategorys(PageBO article) {
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
	public void fillComment(PageBO article) {
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
	public String getCoverImg(PageBO article) {
		if (article == null) {
			return "/_content/themes/intkr/uploads/2013/09/08.jpg";
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
			return "/_content/themes/intkr/uploads/2013/09/08.jpg";
		} else {
			return imgs.get(0).attr("src");
		}
	}

	/**
	 * 填充标签信息
	 * 
	 * @param article
	 */
	public void fillTags(PageBO article) {
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
	public void fillUser(PageBO article) {
		if (article == null) {
			return;
		}
		userManager.fill(article);
	}

	/**
	 * 自增加文章浏览次数
	 * 
	 * @param articleId
	 */
	public void increaseViewCount(Long articleId) {
		articleManager.increaseViewCount(articleId);
	}

	/**
	 * 根据类目查询文章列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public PageBO selectLast(HttpServletRequest request, String categoryId, Integer page, Integer pageSize) {
		PageBO query = new PageBO();
		query.set_page(page);
		query.set_pageSize(pageSize);
		query.setQuery("categoryId", categoryId);
		query.setStatus(ArticleStatus.Public.getCode());
		query.setQuery("orderBy", "public_time");
		query.setQuery("order", "desc");
		articleManager.select(query);
		return query;
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public PageBO select(HttpServletRequest request, HttpServletResponse response) {
		PageBO query = new PageBO();
		PagerUtil.initPage(request, query);
		if (RequestUtil.existParam(request, "categoryId")) {
			query.setQuery("categoryId", RequestUtil.getParam(request, "categoryId"));
		}
		if (RequestUtil.existParam(request, "searchWord")) {
			query.setQuery("searchWord", RequestUtil.getParam(request, "searchWord") + "%");
		}
		query.setStatus(ArticleStatus.Public.getCode());
		query.setQuery("orderBy", "public_time");
		query.setQuery("order", "desc");
		articleManager.selectAndCount(query);
		return query;
	}

	public PageBO selectAll(HttpServletRequest request) {
		PageBO query = new PageBO();
		query.set_pageSize(100000);
		query.setStatus(ArticleStatus.Public.getCode());
		query.setQuery("orderBy", "public_time");
		query.setQuery("order", "desc");
		articleManager.selectAndCount(query);
		return query;
	}

	public PageBO select(HttpServletRequest request, Integer page, Integer pageSize) {
		PageBO query = new PageBO();
		query.set_pageSize(pageSize);
		query.set_page(page);
		query.setStatus(ArticleStatus.Public.getCode());
		query.setQuery("orderBy", "public_time");
		query.setQuery("order", "desc");
		articleManager.selectAndCount(query);
		return query;
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
		 * 获得摘要信息
		 * 
		 * @param article
		 * @param length
		 * @return
		 */
		public static String getSummary(PageBO article, Integer length) {
			if (article.getContent() == null || article.getContent().length() < length) {
				return article.getContent();
			} else {
				Document doc = Jsoup.parse(article.getContent());
				String content = doc.text();
				if (content == null || content.length() < length) {
					return content;
				}
				return content.substring(0, length);
			}
		}

		/**
		 * 获得发布日期
		 * 
		 * @param article
		 * @return
		 */
		public static String getPubDay(PageBO article) {
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
		public static String getPubMooth(PageBO article) {
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
		public static String getPubMoothEn(PageBO article) {
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

	}

}
