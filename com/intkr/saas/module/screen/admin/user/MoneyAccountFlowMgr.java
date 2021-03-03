package com.intkr.saas.module.screen.admin.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.domain.type.user.MoneyFlowStatus;
import com.intkr.saas.domain.type.user.MoneyFlowType;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.module.action.user.ShopMoneyAccountFlowAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @moneyOperor Beiden
 * @date 2016-4-15 下午2:29:27
 * @version 1.0
 */
public class MoneyAccountFlowMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private UserManager userManager = IOC.get("UserManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MoneyAccountFlowBO query = ShopMoneyAccountFlowAction.getParameter(request);
		query = moneyFlowManager.selectAndCount(query);
		query.setDatas(userManager.fill(query.getDatas()));
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		request.setAttribute("MoneyAccount", MoneyAccount.Error);
		request.setAttribute("MoneyOperType", MoneyFlowType.Error);
		request.setAttribute("MoneyOperStatus", MoneyFlowStatus.Error);
	}

}
