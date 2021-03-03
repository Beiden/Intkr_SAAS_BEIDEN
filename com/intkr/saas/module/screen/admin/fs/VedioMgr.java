package com.intkr.saas.module.screen.admin.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.type.fs.MediaType;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.fs.MediaAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-15 下午1:03:07
 * @version 1.0
 */
public class VedioMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MediaManager mediaManager = IOC.get(MediaManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MediaBO query = MediaAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setType(MediaType.Vedio.getCode());
		query.setQuery("orderBy", "id");
		query.setQuery("order", "desc");
		query = mediaManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
