package com.intkr.saas.module.screen.admin.opa.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.opa.ApiTestCaseStepBO;
import com.intkr.saas.manager.opa.ApiTestCaseStepManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table api_test_case_step_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class ApiTestCaseStepAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ApiTestCaseStepManager manager = IOC.get(ApiTestCaseStepManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String apiTestCaseStepId = RequestUtil.getParam(request, "apiTestCaseStepId");
		ApiTestCaseStepBO apiTestCaseStep = manager.get(apiTestCaseStepId);
		request.setAttribute("apiTestCaseStep", apiTestCaseStep);
		request.setAttribute("addId", manager.getId());
		UserLogClient.log(request, "打开", "新增/编辑");
	}

}
