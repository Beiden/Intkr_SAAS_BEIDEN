package com.intkr.saas.module.screen.admin.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.fs.MediaCategoryRelatedBO;
import com.intkr.saas.manager.fs.MediaCategoryRelatedManager;
import com.intkr.saas.module.action.fs.MediaCategoryRelatedAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class MediaCategoryRelatedMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MediaCategoryRelatedManager categoryRelatedManager = IOC.get(MediaCategoryRelatedManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MediaCategoryRelatedBO query = MediaCategoryRelatedAction.getParameter(request);
		query.setQuery("orderBy", "gmt_created");
		query.setQuery("order", "desc");
		query = categoryRelatedManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
