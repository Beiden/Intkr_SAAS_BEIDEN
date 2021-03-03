package com.intkr.saas.module.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.MoneyAccountBO;
import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.domain.type.user.MoneyAccountType;
import com.intkr.saas.domain.type.user.MoneyFlowStatus;
import com.intkr.saas.domain.type.user.MoneyFlowType;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.manager.user.MoneyAccountManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class MoneyAccountAction extends BaseAction<MoneyAccountBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountManager moneyAccountManager = IOC.get("MoneyAccountManager");

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private UserManager userManager = IOC.get("UserManager");

	public MoneyAccountBO getBO(HttpServletRequest request) {
		MoneyAccountBO shopMoneyAccountBO = getParameter(request);
		return shopMoneyAccountBO;
	}

	public static MoneyAccountBO getParameter(HttpServletRequest request) {
		MoneyAccountBO shopMoneyAccountBO = new MoneyAccountBO();
		shopMoneyAccountBO.setId(RequestUtil.getParam(request, "id", Long.class));
		shopMoneyAccountBO.setAccountType(RequestUtil.getParam(request, "accountType"));
		shopMoneyAccountBO.setSaasId(SessionClient.getSaas(request).getId());
		shopMoneyAccountBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		shopMoneyAccountBO.setCode(RequestUtil.getParam(request, "code"));
		shopMoneyAccountBO.setName(RequestUtil.getParam(request, "name"));
		shopMoneyAccountBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, shopMoneyAccountBO);
		return shopMoneyAccountBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return moneyAccountManager;
	}

	public void doRechargeApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moneyFlowId = RequestUtil.getParam(request, "moneyFlowId");
		RequestUtil.setParam(request, "moneyFlowId", moneyFlowId);
		if (moneyAccountManager.get(moneyFlowId) != null) {
			return;
		}
		Long price = MoneyUtil.parse(RequestUtil.getParam(request, "price"));
		MoneyAccountFlowBO flow = new MoneyAccountFlowBO();
		flow.setId(moneyFlowManager.getId());
		UserBO account = SessionClient.getLoginUser(request);

		MoneyAccountBO moneyAccountQuery = new MoneyAccountBO();
		moneyAccountQuery.setSaasId(SessionClient.getSaas(request).getId());
		moneyAccountQuery.setAccountType(MoneyAccountType.Account.getCode());
		moneyAccountQuery.setUserId(account.getId());
		moneyAccountQuery.setCode(MoneyAccount.Yuepay.getCode());
		MoneyAccountBO moneyAccount = moneyAccountManager.getOne(moneyAccountQuery);
		flow.setAccountId(moneyAccount.getId());
		flow.setStatus(MoneyFlowStatus.WaitPay.getCode());
		flow.setType(MoneyFlowType.Recharge.getCode());
		flow.setMoney(price);
		flow.setFeature("moneyFlowId", moneyFlowId);
		moneyFlowManager.insert(flow);
		request.setAttribute("msg", "预申请充值成功！");
		request.setAttribute("result", true);
		request.setAttribute("data", flow);
	}

	/**
	 * 转帐
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doTransfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moneyFlowId = RequestUtil.getParam(request, "moneyFlowId");
		if (moneyFlowManager.get(moneyFlowId) != null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "转帐成功");
			return;
		}
		Long toAccountId = RequestUtil.getParam(request, "toAccountId", Long.class);
		Long money = MoneyUtil.parse(RequestUtil.getParam(request, "money"));
		UserBO account = userManager.get(SessionClient.getLoginUserId(request));
		UserBO toAccount = userManager.get(toAccountId);
		if (account.getMoney() < money) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号余额不足");
			return;
		}
		if (account.getId().equals(toAccountId)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "不能转帐给自己");
			return;
		}
		account.setMoney(account.getMoney() - money);
		userManager.updateMoney(account);
		toAccount.setMoney(toAccount.getMoney() + money);
		userManager.updateMoney(toAccount);
		{// 转帐流水
			MoneyAccountFlowBO moneyOper = new MoneyAccountFlowBO();
			moneyOper.setId(moneyFlowManager.getId());
			MoneyAccountBO moneyAccount = moneyAccountManager.get(SessionClient.getSaasId(request), MoneyAccountType.Account.getCode(), account.getId(), MoneyAccount.Yuepay.getCode());
			moneyOper.setAccountId(moneyAccount.getId());
			moneyOper.setType(MoneyFlowType.Transfer.getCode());
			MoneyAccountBO toMoneyAccount = moneyAccountManager.get(SessionClient.getSaasId(request), MoneyAccountType.Account.getCode(), toAccount.getId(), MoneyAccount.Yuepay.getCode());
			moneyOper.setToAccountId(toMoneyAccount.getId());
			moneyOper.setStatus(MoneyFlowStatus.Finished.getCode());
			moneyOper.setMoney(money);
			moneyFlowManager.insert(moneyOper);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "转帐成功！");
	}

	/**
	 * 提现请求
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doWithdrawApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moneyString = RequestUtil.getParam(request, "money");
		Long money = MoneyUtil.parse(moneyString);
		UserBO user = userManager.get(SessionClient.getLoginUserId(request));
		Long allMoney = getAllWithdrawMoney(request);
		if (user.getMoney() < money + allMoney) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "余额不足");
			return;
		}
		MoneyAccountBO moneyAccount = moneyAccountManager.get(SessionClient.getSaasId(request), MoneyAccountType.Account.getCode(), user.getId(), MoneyAccount.Yuepay.getCode());
		MoneyAccountFlowBO moneyFlow = new MoneyAccountFlowBO();
		moneyFlow.setId(moneyFlowManager.getId());
		moneyFlow.setAccountId(moneyAccount.getId());
		moneyFlow.setType(MoneyFlowType.Withdraw.getCode());
		moneyFlow.setStatus(MoneyFlowStatus.WaitAudit.getCode());
		moneyFlow.setMoney(money);
		moneyFlow.setFeature("bankName", RequestUtil.getParam(request, "bankName"));
		moneyFlow.setFeature("bankNum", RequestUtil.getParam(request, "bankNum"));
		moneyFlow.setFeature("bankOwnerName", RequestUtil.getParam(request, "bankOwnerName"));
		moneyFlow.setFeature("phone", RequestUtil.getParam(request, "phone"));
		moneyFlowManager.insert(moneyFlow);
		request.setAttribute("result", true);
		request.setAttribute("msg", "提现申请成功");
		RequestUtil.setParam(request, "moneyOperId", moneyFlow.getId() + "");
	}

	private Long getAllWithdrawMoney(HttpServletRequest request) {
		MoneyAccountFlowBO query = new MoneyAccountFlowBO();
		UserBO user = SessionClient.getLoginUser(request);
		MoneyAccountBO moneyAccount = moneyAccountManager.get(SessionClient.getSaasId(request), MoneyAccountType.Account.getCode(), user.getId(), MoneyAccount.Yuepay.getCode());
		query.setAccountId(moneyAccount.getId());
		query.setType(MoneyFlowType.Withdraw.getCode());
		query.setStatus(MoneyFlowStatus.WaitAudit.getCode());
		List<MoneyAccountFlowBO> outList = moneyFlowManager.select(query);
		Long allMoney = 0L;
		for (MoneyAccountFlowBO bo : outList) {
			allMoney += bo.getMoney();
		}
		return allMoney;
	}

	public void doWithdrawApprove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MoneyAccountFlowBO moneyOper = moneyFlowManager.get(RequestUtil.getParam(request, "moneyOperId"));
		if (!moneyOper.getStatus().equals(MoneyFlowStatus.WaitAudit.getCode())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "审核失败");
			return;
		}
		if (!moneyOper.getType().equals(MoneyFlowType.Withdraw.getCode())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "审核失败");
			return;
		}
		moneyOper.setStatus(MoneyFlowStatus.Finished.getCode());
		moneyFlowManager.update(moneyOper);
		UserBO user = userManager.get(SessionClient.getLoginUserId(request));
		user.setMoney(user.getMoney() - moneyOper.getMoney());
		userManager.update(user);
		request.setAttribute("result", true);
		request.setAttribute("msg", "审核成功");
	}

}
