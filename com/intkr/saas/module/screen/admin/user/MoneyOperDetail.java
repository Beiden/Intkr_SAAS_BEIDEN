package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyOperBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.MoneyOperManager;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午9:05:29
 * @version 1.0
 */
public class MoneyOperDetail {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyOperManager moneyOperManager = IOC.get(MoneyOperManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MoneyOperBO moneyOper = moneyOperManager.get(request.getParameter("id"));
		userManager.fill(moneyOper);
		request.setAttribute("dto", moneyOper);
	}

}
