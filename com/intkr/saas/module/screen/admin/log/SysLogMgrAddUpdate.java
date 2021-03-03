package com.intkr.saas.module.screen.admin.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.log.SysLogManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.log.SysLogBO;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:29:21
 * @version 1.0
 */
public class SysLogMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SysLogManager sysLogManager = IOC.get(SysLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			Long id = Long.valueOf(RequestUtil.getParam(request, "id"));
			SysLogBO sysLogBO = sysLogManager.get(id);
			request.setAttribute("dto", sysLogBO);
		} else {
			request.setAttribute("addId", sysLogManager.getId());
		}
	}

}
