package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyOperBO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.domain.type.user.MoneyOperStatus;
import com.intkr.saas.domain.type.user.MoneyOperType;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.MoneyOperManager;
import com.intkr.saas.manager.user.impl.UserManagerImpl;
import com.intkr.saas.manager.user.impl.MoneyOperManagerImpl;
import com.intkr.saas.module.action.user.MoneyOperAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @moneyOperor Beiden
 * @date 2016-4-15 下午2:29:27
 * @version 1.0
 */
public class MoneyOperMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyOperManager moneyOperManager = IOC.get(MoneyOperManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MoneyOperBO query = MoneyOperAction.getParameter(request);
		moneyOperManager.selectAndCount(query);
		userManager.fill(query.getDatas());
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("MoneyAccount", MoneyAccount.Error);
		request.setAttribute("MoneyOperType", MoneyOperType.Error);
		request.setAttribute("MoneyOperStatus", MoneyOperStatus.Error);
	}

}
