package com.intkr.saas.module.screen.admin.fs.dialog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaCategoryBO;
import com.intkr.saas.manager.fs.MediaCategoryManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MediaCategoryAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MediaCategoryManager categoryManager = IOC.get(MediaCategoryManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<MediaCategoryBO> categoryList = categoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		request.setAttribute("categoryList", categoryList);

		String mediaCategoryId = RequestUtil.getParam(request, "mediaCategoryId");
		MediaCategoryBO mediaCategory = categoryManager.get(mediaCategoryId);
		request.setAttribute("mediaCategory", mediaCategory);
		request.setAttribute("addId", categoryManager.getId());
	}

}
