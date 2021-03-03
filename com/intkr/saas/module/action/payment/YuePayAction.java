package com.intkr.saas.module.action.payment;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.OrderBO;
import com.intkr.saas.domain.bo.order.OrderTimeLineBO;
import com.intkr.saas.domain.bo.user.MoneyAccountBO;
import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.type.order.OrderStatus;
import com.intkr.saas.domain.type.order.OrderTimeLineType;
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
public class YuePayAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserManager userManager = IOC.get("UserManager");

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	private OrderManager orderManager = IOC.get("OrderManager");

	private OrderTimeLineManager orderTimeLineManager = IOC.get("OrderTimeLineManager");

	private MoneyAccountManager moneyAccountManager = IOC.get("MoneyAccountManager");

	private MoneyAccountBO getMoneyAccount(HttpServletRequest request, String accountType, Long userId, String account) {
		MoneyAccountBO moneyAccountQuery = new MoneyAccountBO();
		moneyAccountQuery.setSaasId(SessionClient.getSaas(request).getId());
		moneyAccountQuery.setAccountType(accountType);
		moneyAccountQuery.setUserId(userId);
		moneyAccountQuery.setCode(account);
		MoneyAccountBO moneyAccount = moneyAccountManager.getOne(moneyAccountQuery);
		return moneyAccount;
	}

	/**
	 * 用户帐号余额支付订单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doYuepay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long orderId = RequestUtil.getParam(request, "orderId", Long.class);
		OrderBO orderBO = orderManager.get(orderId);
		if (!OrderStatus.WaitPay.getCode().equals(orderBO.getStatus())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "订单状态异常");
			return;
		}
		UserBO user = SessionClient.getLoginUser(request);
		UserBO userBO = userManager.get(user.getId());
		if (userBO.getMoney() < orderBO.getPrice()) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "您的余额不足");
			return;
		}
		MoneyAccountFlowBO moneyOper = new MoneyAccountFlowBO();
		moneyOper.setId(moneyFlowManager.getId());
		moneyOper.setSaasId(SessionClient.getSaasId(request));
		MoneyAccountBO moneyAccount = getMoneyAccount(request, MoneyAccountType.Account.getCode(), user.getId(), MoneyAccount.Yuepay.getCode());
		moneyOper.setAccountId(moneyAccount.getId());
		moneyOper.setType(MoneyFlowType.Pay.getCode());
		MoneyAccountBO toMoneyAccount = getMoneyAccount(request, MoneyAccountType.Shop.getCode(), orderBO.getShopId(), MoneyAccount.Yuepay.getCode());
		moneyOper.setToAccountId(toMoneyAccount.getId());
		moneyOper.setMoney(orderBO.getPrice());
		moneyOper.setFeature("orderId", orderId);
		moneyOper.setStatus(MoneyFlowStatus.WaitArrive.getCode());
		moneyFlowManager.insert(moneyOper);
		userBO.setMoney(userBO.getMoney() - orderBO.getPrice());
		userManager.update(userBO);
		if (orderBO.getPayTime() == null) {
			orderBO.setPayTime(new Date());
		}
		orderBO.setPayment(MoneyAccount.Yuepay.getCode());
		orderBO.setFeature("money_detail_id", moneyOper.getId());
		orderBO.setStatus(OrderStatus.WaitSendOut.getCode());
		orderBO.setFeature("yuepayConfirmPay", true);
		{// 更新订单时间轴
			OrderTimeLineBO orderTimeLine = new OrderTimeLineBO();
			orderTimeLine.setSaasId(SessionClient.getSaasId(request));
			orderTimeLine.setId(orderTimeLineManager.getId());
			orderTimeLine.setType(OrderTimeLineType.Pay.getName());
			orderTimeLine.setTime(new Date());
			orderTimeLine.setOrderId(orderId);
			orderTimeLine.setUserId(SessionClient.getLoginUserId(request));
			orderTimeLineManager.insert(orderTimeLine);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "订单支付成功!");
		orderManager.update(orderBO);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doCanYuepay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String orderId = RequestUtil.getParam(request, "orderId");
		OrderBO orderBO = orderManager.get(orderId);
		UserBO user = SessionClient.getLoginUser(request);
		UserBO userBO = userManager.get(user.getId());
		if (userBO.getMoney() < orderBO.getPrice()) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "余额不足");
		} else {
			request.setAttribute("result", true);
			request.setAttribute("msg", "余额充足");
		}
	}

}
