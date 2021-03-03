package com.intkr.saas.module.screen.admin.opa.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiParamBO;
import com.intkr.saas.manager.opa.ApiParamManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_param_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiParamAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiParamManager manager = IOC.get(ApiParamManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String apiParamId = RequestUtil.getParam(request, "apiParamId");
		ApiParamBO apiParam = manager.get(apiParamId);
		request.setAttribute("apiParam", apiParam);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
