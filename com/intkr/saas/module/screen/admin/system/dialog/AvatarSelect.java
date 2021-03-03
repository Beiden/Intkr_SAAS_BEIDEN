package com.intkr.saas.module.screen.admin.system.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.sns.SysImgBO;
import com.intkr.saas.manager.sns.SysImgManager;
import com.intkr.saas.module.action.sys.SysImgAction;

/**
 * 
 * @author Beiden
 * @date 2016-5-26 下午2:44:09
 * @version 1.0
 */
public class AvatarSelect {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SysImgManager sysImgManager = IOC.get(SysImgManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		SysImgBO query = SysImgAction.getParameter(request);
		query = sysImgManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}