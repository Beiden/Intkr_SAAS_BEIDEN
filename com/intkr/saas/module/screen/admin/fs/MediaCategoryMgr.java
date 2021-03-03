package com.intkr.saas.module.screen.admin.fs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaCategoryBO;
import com.intkr.saas.manager.fs.MediaCategoryManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class MediaCategoryMgr {

	private MediaCategoryManager categoryManager = IOC.get(MediaCategoryManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<MediaCategoryBO> list = categoryManager.getFirstCategoryFull(SessionClient.getSaas(request).getId());
		request.setAttribute("list", list);
	}

}
