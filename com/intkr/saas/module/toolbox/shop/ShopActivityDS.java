package com.intkr.saas.module.toolbox.shop;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.type.shop.ActivityStatus;
import com.intkr.saas.domain.type.shop.ActivityType;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.shop.ShopActivityManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:59:08
 * @version 1.0
 */
public class ShopActivityDS extends BaseToolBox {

	private ShopActivityManager activityManager = IOC.get(ShopActivityManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private ImgManager mediaManager = IOC.get(ImgManager.class);

	/**
	 * 根据ID获得文章详细信息
	 * 
	 * @param activityId
	 * @return
	 */
	public ShopActivityBO getById(Long activityId) {
		if (!IdEngine.isValidate(activityId)) {
			return null;
		}
		ShopActivityBO activity = activityManager.get(activityId);
		if (activity == null) {
			return null;
		}
		if (ActivityStatus.Approve.getCode().equals(activity.getStatus())) {// 文章是否是已发布状态
			return activity;
		}
		return null;
	}

	public String getCoverImg(ShopActivityBO activity) {
		if (activity == null) {
			return "/_content/themes/intkr/uploads/2013/09/08.jpg";
		}
		if (IdEngine.isValidate(activity.getCoverImgId())) {
			if (activity.getCoverImg() == null) {
				mediaManager.fill(activity);
			}
			if (activity.getCoverImg() != null) {
				return activity.getCoverImg().getUri();
			}
		}
		Document doc = Jsoup.parse(activity.getContent());
		Elements imgs = doc.select("img");
		if (imgs.isEmpty()) {
			return "/_content/themes/intkr/uploads/2013/09/08.jpg";
		} else {
			return imgs.get(0).attr("src");
		}
	}

	/**
	 * 填充用户信息
	 * 
	 * @param activity
	 */
	public void fillUser(ShopActivityBO activity) {
		if (activity == null) {
			return;
		}
		userManager.fill(activity);
	}

	/**
	 * 根据类目查询文章列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ShopActivityBO selectLast(String categoryId, Integer page, Integer pageSize) {
		ShopActivityBO query = new ShopActivityBO();
		query.setType(ActivityType.Common.getCode());
		query.set_page(page);
		query.set_pageSize(pageSize);
		query.setQuery("categoryId", categoryId);
		query.setStatus(ActivityStatus.Approve.getCode());
		activityManager.select(query);
		return query;
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ShopActivityBO select(HttpServletRequest request, HttpServletResponse response) {
		ShopActivityBO query = new ShopActivityBO();
		query.setType(ActivityType.Common.getCode());
		if (RequestUtil.existParam(request, "_page") && RequestUtil.existParam(request, "_pageSize")) {
			query.set_pageSize(RequestUtil.getParam(request, "_pageSize", Integer.class));
			query.set_page(RequestUtil.getParam(request, "_page", Integer.class));
		}
		if (RequestUtil.existParam(request, "categoryId")) {
			query.setQuery("categoryId", request.getParameter("categoryId"));
		}
		if (RequestUtil.existParam(request, "searchWord")) {
			query.setQuery("searchWord", request.getParameter("searchWord") + "%");
		}
		query.setStatus(ActivityStatus.Approve.getCode());
		activityManager.selectAndCount(query);
		return query;
	}

	public List<ShopActivityBO> selectAll() {
		ShopActivityBO query = new ShopActivityBO();
		query.setType(ActivityType.Common.getCode());
		query.set_pageSize(100000);
		query.setStatus(ActivityStatus.Approve.getCode());
		List<ShopActivityBO> list = activityManager.select(query);
		return list;
	}

	public ShopActivityBO select(Integer page, Integer pageSize) {
		ShopActivityBO query = new ShopActivityBO();
		query.setType(ActivityType.Common.getCode());
		query.set_pageSize(pageSize);
		query.set_page(page);
		query.setStatus(ActivityStatus.Approve.getCode());
		activityManager.selectAndCount(query);
		return query;
	}

}
