package com.intkr.saas.module.screen.admin.system.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.module.action.fs.ImgAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-11-3 下午05:43:58
 * @version 1.0
 */
public class ImgSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ImgManager mediaManager = IOC.get(ImgManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		ImgBO query = ImgAction.getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		mediaManager.selectAndCount(query);
		request.setAttribute("result", query);
	}

}
