package com.intkr.saas.module.screen.admin.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.module.action.fs.MediaAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-6-18 下午10:17:54
 * @version 1.0
 */
public class MediaMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MediaManager mediaManager = IOC.get(MediaManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MediaBO query = MediaAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = mediaManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
