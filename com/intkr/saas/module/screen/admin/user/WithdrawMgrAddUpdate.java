package com.intkr.saas.module.screen.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.bo.user.MoneyOperBO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.domain.type.user.MoneyFlowStatus;
import com.intkr.saas.domain.type.user.MoneyFlowType;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.manager.user.MoneyAccountManager;
import com.intkr.saas.manager.user.MoneyOperManager;
import com.intkr.saas.module.action.user.ShopMoneyAccountFlowAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @moneyOperor Beiden
 * @date 2016-4-15 下午2:29:27
 * @version 1.0
 */
public class WithdrawMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyOperManager moneyOperManager = IOC.get(MoneyOperManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MoneyOperBO moneyOper = moneyOperManager.get(request.getParameter("id"));
		userManager.fill(moneyOper);
		request.setAttribute("dto", moneyOper);
	}

}
