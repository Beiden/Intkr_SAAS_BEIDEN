package com.intkr.saas.module.screen.admin.opa.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiInvokeLogBO;
import com.intkr.saas.manager.opa.ApiInvokeLogManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_invoke_log_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiInvokeLogAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiInvokeLogManager manager = IOC.get(ApiInvokeLogManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String apiInvokeLogId = RequestUtil.getParam(request, "apiInvokeLogId");
		ApiInvokeLogBO apiInvokeLog = manager.get(apiInvokeLogId);
		request.setAttribute("apiInvokeLog", apiInvokeLog);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
