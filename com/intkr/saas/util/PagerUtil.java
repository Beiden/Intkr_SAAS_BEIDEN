package com.intkr.saas.util;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.domain.BaseDO;
import com.intkr.saas.domain.Pager;

/**
 * 
 * @author Beiden
 * @date 2011-6-30 上午9:07:12
 * @version 1.0
 */
public class PagerUtil {

	public static boolean initPage(Object request, Pager query) {
		boolean result = false;
		if (RequestUtil.existParam(request, "orderBy")) {
			query.setQuery("orderBy", RequestUtil.getParam(request, "orderBy"));
		}
		if (RequestUtil.existParam(request, "order")) {
			query.setQuery("order", RequestUtil.getParam(request, "order"));
		}
		if (RequestUtil.existParam(request, "_page")) {
			query.set_page(RequestUtil.getParam(request, "_page", Integer.class));
			result = true;
		} else if (RequestUtil.existParam(request, "page")) {
			query.set_page(RequestUtil.getParam(request, "page", Integer.class));
			result = true;
		}
		if (RequestUtil.existParam(request, "_pageSize")) {
			query.set_pageSize(RequestUtil.getParam(request, "_pageSize", Integer.class));
			result = true;
		} else if (RequestUtil.existParam(request, "pageSize")) {
			query.set_pageSize(RequestUtil.getParam(request, "pageSize", Integer.class));
			result = true;
		}
		return result;
	}

	public static boolean initPage(HttpServletRequest request, BaseDO query) {
		return initPage(request, (Pager) query);
	}

}
