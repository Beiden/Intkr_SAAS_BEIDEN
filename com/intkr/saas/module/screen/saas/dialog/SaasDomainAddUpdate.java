package com.intkr.saas.module.screen.saas.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.manager.saas.SaasDomainManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class SaasDomainAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SaasDomainManager manager = IOC.get(SaasDomainManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = RequestUtil.getParam(request, "saasDomainId");
		SaasDomainBO saasDomain = manager.get(id);
		request.setAttribute("saasDomain", saasDomain);
		request.setAttribute("addId", manager.getId());
	}

}
