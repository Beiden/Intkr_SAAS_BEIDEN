package com.intkr.saas.module.action.fs;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.fs.MediaCategoryRelatedManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaCategoryRelatedBO;
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
public class MediaCategoryRelatedAction extends BaseAction<MediaCategoryRelatedBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MediaCategoryRelatedManager categoryRelatedManager = IOC.get(MediaCategoryRelatedManager.class);

	public MediaCategoryRelatedBO getBO(HttpServletRequest request) {
		MediaCategoryRelatedBO mediaCategoryRelatedBO = getParameter(request);
		return mediaCategoryRelatedBO;
	}

	public static MediaCategoryRelatedBO getParameter(HttpServletRequest request) {
		MediaCategoryRelatedBO mediaCategoryRelatedBO = new MediaCategoryRelatedBO();
		mediaCategoryRelatedBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mediaCategoryRelatedBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		mediaCategoryRelatedBO.setType(RequestUtil.getParam(request, "type"));
		mediaCategoryRelatedBO.setCategoryId(RequestUtil.getParam(request, "categoryId" , Long.class));
		mediaCategoryRelatedBO.setRelatedId(RequestUtil.getParam(request, "relatedId" , Long.class));
		PagerUtil.initPage(request, mediaCategoryRelatedBO);
		return mediaCategoryRelatedBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return categoryRelatedManager;
	}

}
