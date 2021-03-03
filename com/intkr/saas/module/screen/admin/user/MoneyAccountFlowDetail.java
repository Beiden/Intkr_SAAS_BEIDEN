package com.intkr.saas.module.screen.admin.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午9:05:29
 * @version 1.0
 */
public class MoneyAccountFlowDetail {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MoneyAccountFlowBO moneyOper = moneyFlowManager.get(RequestUtil.getParam(request, "id"));
//		userManager.fill(moneyOper);
		request.setAttribute("dto", moneyOper);
	}

}
