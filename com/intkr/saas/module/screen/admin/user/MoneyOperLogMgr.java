package com.intkr.saas.module.screen.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyOperLogBO;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.MoneyOperLogManager;
import com.intkr.saas.manager.user.impl.UserManagerImpl;
import com.intkr.saas.manager.user.impl.MoneyOperLogManagerImpl;
import com.intkr.saas.module.action.user.MoneyOperLogAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午8:23:29
 * @version 1.0
 */
public class MoneyOperLogMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyOperLogManager moneyOperLogManager = IOC.get(MoneyOperLogManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MoneyOperLogBO query = MoneyOperLogAction.getParameter(request);
		moneyOperLogManager.selectAndCount(query);
		userManager.fill(query.getDatas());
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
	}

}
