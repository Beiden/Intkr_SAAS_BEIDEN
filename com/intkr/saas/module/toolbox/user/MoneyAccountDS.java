package com.intkr.saas.module.toolbox.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.MoneyAccountBO;
import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.domain.type.user.MoneyAccountType;
import com.intkr.saas.domain.type.user.MoneyFlowStatus;
import com.intkr.saas.domain.type.user.MoneyFlowType;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.manager.user.MoneyAccountManager;
import com.intkr.saas.module.action.user.ShopMoneyAccountFlowAction;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2017-10-6
 * @version 1.0
 */
public class MoneyAccountDS extends BaseToolBox {

	private MoneyAccountManager moneyAccountManager = IOC.get("MoneyAccountManager");

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private MoneyAccount moneyAccount = MoneyAccount.Error;

	private MoneyFlowType moneyFlowType = MoneyFlowType.Error;

	private MoneyFlowStatus moneyFlowStatus = MoneyFlowStatus.Error;

	public Long getFlowId() {
		return moneyFlowManager.getId();
	}

	public MoneyAccountFlowBO selectFlow(HttpServletRequest request) {
		MoneyAccountFlowBO query = ShopMoneyAccountFlowAction.getParameter(request);
		UserBO account = SessionClient.getLoginUser(request);
		List<MoneyAccountBO> myAccounts = moneyAccountManager.select(MoneyAccountType.Account.getCode(), account.getId());
		List<Long> accountIds = new ArrayList<Long>();
		for (MoneyAccountBO bo : myAccounts) {
			accountIds.add(bo.getId());
		}
		query.setQuery("accountIds", accountIds);
		if (query.getQuery("orderBy") == null) {
			query.setQuery("orderBy", "gmt_created");
		}
		if (query.getQuery("order") == null) {
			query.setQuery("order", "desc");
		}
		moneyFlowManager.selectAndCount(query);
		moneyAccountManager.fill(query.getDatas());
		return query;
	}

	public MoneyFlowType getMoneyFlowType() {
		return moneyFlowType;
	}

	public void setMoneyFlowType(MoneyFlowType moneyFlowType) {
		this.moneyFlowType = moneyFlowType;
	}

	public MoneyFlowStatus getMoneyFlowStatus() {
		return moneyFlowStatus;
	}

	public void setMoneyFlowStatus(MoneyFlowStatus moneyFlowStatus) {
		this.moneyFlowStatus = moneyFlowStatus;
	}

	public MoneyAccount getMoneyAccount() {
		return moneyAccount;
	}

	public void setMoneyAccount(MoneyAccount moneyAccount) {
		this.moneyAccount = moneyAccount;
	}

}
