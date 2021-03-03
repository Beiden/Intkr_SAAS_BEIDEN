package com.intkr.saas.module.screen.admin.system.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.user.RightBO;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.module.action.user.right.RightAction;

/**
 * 
 * @author Beiden
 * @date 2011-11-22 下午2:59:47
 * @version 1.0
 */
public class RightSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RightManager rightManager = IOC.get(RightManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		RightBO query = RightAction.getParameter(request);
		query = rightManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}