package com.intkr.saas.module.action.fs;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.fs.MediaCategoryManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaCategoryBO;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MediaCategoryAction extends BaseAction<MediaCategoryBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static MediaCategoryManager categoryManager = IOC.get(MediaCategoryManager.class);

	public MediaCategoryBO getBO(HttpServletRequest request) {
		MediaCategoryBO mediaCategoryBO = getParameter(request);
		return mediaCategoryBO;
	}

	public static MediaCategoryBO getParameter(HttpServletRequest request) {
		MediaCategoryBO mediaCategoryBO = new MediaCategoryBO();
		mediaCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mediaCategoryBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		mediaCategoryBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mediaCategoryBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		mediaCategoryBO.setName(RequestUtil.getParam(request, "name"));
		mediaCategoryBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		mediaCategoryBO.setDescription(RequestUtil.getParam(request, "description"));
		mediaCategoryBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, mediaCategoryBO);
		return mediaCategoryBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return categoryManager;
	}

}
