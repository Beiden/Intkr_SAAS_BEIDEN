package com.intkr.saas.module.screen.admin.opa.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.opa.OaLogBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.opa.OaLogManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 接口日志
 * 
 * @table oa_log
 * 
 * @author Beiden
 * @date 2020-11-04 20:43:19
 * @version 1.0
 */
public class OaLogAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OaLogManager manager = IOC.get(OaLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String oaLogId = RequestUtil.getParam(request, "oaLogId");
		OaLogBO oaLog = manager.get(oaLogId);
		request.setAttribute("oaLog", oaLog);
		request.setAttribute("addId", manager.getId());
	}

}
