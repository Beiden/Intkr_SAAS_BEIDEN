package com.intkr.saas.module.action.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.user.MoneyAccountBO;
import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.domain.type.user.MoneyAccountType;
import com.intkr.saas.domain.type.user.MoneyFlowStatus;
import com.intkr.saas.domain.type.user.MoneyFlowType;
import com.intkr.saas.manager.order.OrderManager;
import com.intkr.saas.manager.order.OrderTimeLineManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.manager.user.MoneyAccountManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-27 下午3:09:44
 * @version 1.0
 */
public class MoneyAccountAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get("UserManager");

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	private MoneyAccountManager moneyAccountManager = IOC.get("MoneyAccountManager");

	public void doLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderId = request.getParameter("orderId");
		OrderBO order = orderManager.get(orderId);
		if (!OrderStatus.WaitPay.getCode().equals(order.getStatus())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单状态异常！");
			return;
		}
		{// 付款流水
			MoneyAccountBO moneyAccount = getMoneyAccount(request, MoneyAccountType.Account.getCode(), SessionClient.getLoginUserId(request), RequestUtil.getParam(request, "moneyAccountType"));
			MoneyAccountBO toMoneyAccount = getMoneyAccount(request, MoneyAccountType.Shop.getCode(), order.getShopId(), MoneyAccount.Yuepay.getCode());
			MoneyAccountFlowBO query = new MoneyAccountFlowBO();
			query.setRelatedId(order.getId());
			query.setAccountId(moneyAccount.getId());
			query.setType(MoneyFlowType.Pay.getCode());
			query.setToAccountId(toMoneyAccount.getId());
			query.setStatus(MoneyFlowStatus.WaitPay.getCode());
			MoneyAccountFlowBO moneyFlow = moneyFlowManager.selectOne(query);
			if (moneyFlow == null) {
				moneyFlow = new MoneyAccountFlowBO();
				moneyFlow.setId(moneyFlowManager.getId());
				moneyFlow.setSaasId(SessionClient.getSaasId(request));
				moneyFlow.setRelatedId(order.getId());
				moneyFlow.setAccountId(moneyAccount.getId());
				moneyFlow.setType(MoneyFlowType.Pay.getCode());
				moneyFlow.setToAccountId(toMoneyAccount.getId());
				moneyFlow.setStatus(MoneyFlowStatus.WaitPay.getCode());
				moneyFlow.setMoney(order.getPrice());
				moneyFlow.setNote(order.getName() == null ? "无" : order.getName());
				moneyFlowManager.insert(moneyFlow);
			} else {
				moneyFlow.setMoney(order.getPrice());
				moneyFlowManager.update(moneyFlow);
			}
			request.setAttribute("data", moneyFlow);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "创建支付记录成功！");
	}

	private MoneyAccountBO getMoneyAccount(HttpServletRequest request, String accountType, Long userId, String account) {
		MoneyAccountBO moneyAccountQuery = new MoneyAccountBO();
		moneyAccountQuery.setSaasId(SessionClient.getSaas(request).getId());
		moneyAccountQuery.setAccountType(accountType);
		moneyAccountQuery.setUserId(userId);
		moneyAccountQuery.setCode(account);
		MoneyAccountBO moneyAccount = moneyAccountManager.getOne(moneyAccountQuery);
		return moneyAccount;
	}

}
