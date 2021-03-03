package com.intkr.saas.module.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.domain.bo.user.MoneyOperBO;
import com.intkr.saas.domain.bo.user.MoneyOperLogBO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.domain.type.user.MoneyOperLogType;
import com.intkr.saas.domain.type.user.MoneyOperStatus;
import com.intkr.saas.domain.type.user.MoneyOperType;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.manager.user.MoneyOperLogManager;
import com.intkr.saas.manager.user.MoneyOperManager;
import com.intkr.saas.manager.user.impl.MoneyOperLogManagerImpl;
import com.intkr.saas.manager.user.impl.MoneyOperManagerImpl;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.MoneyUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @moneyOperor Beiden
 * @date 2016-4-15 下午2:30:49
 * @version 1.0
 */
public class MoneyOperAction extends BaseAction<MoneyOperBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyOperManager moneyOperManager = IOC.get(MoneyOperManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private MoneyOperLogManager moneyOperLogManager = IOC.get(MoneyOperLogManager.class);

	public void approve(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MoneyOperBO moneyOper = moneyOperManager.get(request.getParameter("moneyOperId"));
		if (!moneyOper.getStatus().equals(MoneyOperStatus.WaitAudit.getCode())) {
			logApproveWithdrawApply(request, moneyOper, "审核失败");
			request.setAttribute("result", false);
			request.setAttribute("msg", "审核失败");
			return;
		}
		if (!moneyOper.getType().equals(MoneyOperType.Out.getCode())) {
			logApproveWithdrawApply(request, moneyOper, "审核失败");
			request.setAttribute("result", false);
			request.setAttribute("msg", "审核失败");
			return;
		}
		moneyOper.setStatus(MoneyOperStatus.Finished.getCode());
		moneyOperManager.update(moneyOper);
		UserBO user = userManager.get(SessionClient.getLoginUserId(request));
		user.setMoney(user.getMoney() - moneyOper.getMoney());
		userManager.update(user);
		logApproveWithdrawApply(request, moneyOper, "审核成功");
		request.setAttribute("result", true);
		request.setAttribute("msg", "审核成功");
	}

	private void logApproveWithdrawApply(HttpServletRequest request, MoneyOperBO moneyOper, String msg) {
		MoneyOperLogBO log = new MoneyOperLogBO();
		log.setId(moneyOperLogManager.getId());
		log.setOperId(SessionClient.getLoginUserId(request));
		log.setUserId(moneyOper.getUserId());
		log.setRelatedType("moneyOperId");
		log.setRelatedId(moneyOper.getId());
		log.setType(MoneyOperLogType.ApproveWithdrawApply.getCode());
		log.setFeature("msg", msg);
		moneyOperLogManager.insert(log);
	}

	/**
	 * 提现请求
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void withdrawApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moneyString = request.getParameter("money");
		Long money = MoneyUtil.parse(moneyString);
		UserBO user = userManager.get(SessionClient.getLoginUserId(request));
		Long allMoney = getAllWithdrawMoney(request);
		if (user.getMoney() < money + allMoney) {
			request.setAttribute("result", false);
			logWithdrawApply(request, new MoneyOperBO(), "余额不足");
			request.setAttribute("result", false);
			request.setAttribute("msg", "余额不足");
			return;
		}
		MoneyOperBO moneyOper = new MoneyOperBO();
		moneyOper.setId(moneyOperManager.getId());
		moneyOper.setUserId(SessionClient.getLoginUserId(request));
		moneyOper.setAccount(MoneyAccount.Yuepay.getCode());
		moneyOper.setType(MoneyOperType.Out.getCode());
		moneyOper.setStatus(MoneyOperStatus.WaitAudit.getCode());
		moneyOper.setMoney(money);
		moneyOper.setFeature("bankName", request.getParameter("bankName"));
		moneyOper.setFeature("bankNum", request.getParameter("bankNum"));
		moneyOper.setFeature("bankOwnerName", request.getParameter("bankOwnerName"));
		moneyOper.setFeature("phone", request.getParameter("phone"));
		moneyOperManager.insert(moneyOper);
		logWithdrawApply(request, moneyOper, "提现申请成功");
		request.setAttribute("result", true);
		request.setAttribute("msg", "提现申请成功");
		RequestUtil.setParam(request, "moneyOperId", moneyOper.getId() + "");
	}

	private void logWithdrawApply(HttpServletRequest request, MoneyOperBO moneyOper, String msg) {
		MoneyOperLogBO log = new MoneyOperLogBO();
		log.setId(moneyOperLogManager.getId());
		log.setOperId(SessionClient.getLoginUserId(request));
		log.setUserId(SessionClient.getLoginUserId(request));
		log.setType(MoneyOperLogType.WithdrawApply.getCode());
		log.setRelatedType("moneyOperId");
		log.setRelatedId(moneyOper.getId());
		log.setFeature("bankName", request.getParameter("bankName"));
		log.setFeature("bankNum", request.getParameter("bankNum"));
		log.setFeature("bankOwnerName", request.getParameter("bankOwnerName"));
		log.setFeature("msg", msg);
		moneyOperLogManager.insert(log);
	}

	private Long getAllWithdrawMoney(HttpServletRequest request) {
		MoneyOperBO query = new MoneyOperBO();
		query.setUserId(SessionClient.getLoginUserId(request));
		query.setType(MoneyOperType.Out.getCode());
		query.setStatus(MoneyOperStatus.WaitAudit.getCode());
		List<MoneyOperBO> outList = moneyOperManager.select(query);
		Long allMoney = 0L;
		for (MoneyOperBO bo : outList) {
			allMoney += bo.getMoney();
		}
		return allMoney;
	}

	/**
	 * 充值请求
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void rechargeApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String moneyString = request.getParameter("money");
		Long money = MoneyUtil.parse(moneyString);
		MoneyOperBO moneyOper = new MoneyOperBO();
		moneyOper.setId(moneyOperManager.getId());
		moneyOper.setUserId(SessionClient.getLoginUserId(request));
		moneyOper.setType(MoneyOperType.In.getCode());
		moneyOper.setStatus(MoneyOperStatus.WaitForPay.getCode());
		moneyOper.setMoney(money);
		moneyOperManager.insert(moneyOper);
		request.setAttribute("result", true);
		request.setAttribute("msg", "申请成功");
		RequestUtil.setParam(request, "moneyOperId", moneyOper.getId() + "");
		MoneyOperLogBO log = logRechargeApply(request, moneyOper);
		moneyOperLogManager.insert(log);
	}

	private MoneyOperLogBO logRechargeApply(HttpServletRequest request, MoneyOperBO moneyOper) {
		MoneyOperLogBO log = new MoneyOperLogBO();
		log.setId(moneyOperLogManager.getId());
		log.setOperId(SessionClient.getLoginUserId(request));
		log.setUserId(SessionClient.getLoginUserId(request));
		log.setType(MoneyOperLogType.RechargeApply.getCode());
		log.setRelatedType("moneyOperId");
		log.setRelatedId(moneyOper.getId());
		log.setFeature("msg", "申请成功");
		return log;
	}

	/**
	 * 转帐
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void transfer(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String transferId = request.getParameter("transferId");
		if (moneyOperManager.get(transferId) != null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "转帐成功");
			return;
		}
		String toUserAccount = request.getParameter("toUserAccount");
		UserBO toUser = userManager.getByAccount(SessionClient.getSaasId(request), toUserAccount);
		Long money = MoneyUtil.parse(request.getParameter("money"));
		if (toUser == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "转入帐户不存在");
			return;
		}
		UserBO user = userManager.get(SessionClient.getLoginUserId(request));
		if (user.getMoney() < money) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "帐号余额不足");
			return;
		}
		if (user.getId().equals(toUser.getId())) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "不能转帐给自己");
			return;
		}
		user.setMoney(user.getMoney() - money);
		userManager.updateMoney(user);
		toUser.setMoney(toUser.getMoney() + money);
		userManager.updateMoney(toUser);
		{
			MoneyOperBO moneyOper = new MoneyOperBO();
			moneyOper.setId(moneyOperManager.getId());
			moneyOper.setUserId(user.getId());
			moneyOper.setAccount(MoneyAccount.Yuepay.getCode());
			moneyOper.setType(MoneyOperType.TransferOut.getCode());
			moneyOper.setRelatedUserId(toUser.getId());
			moneyOper.setRelatedAccount(MoneyAccount.Yuepay.getCode());
			moneyOper.setStatus(MoneyOperStatus.Finished.getCode());
			moneyOper.setMoney(money);
			moneyOperManager.insert(moneyOper);
		}
		{
			MoneyOperBO moneyOper = new MoneyOperBO();
			moneyOper.setId(moneyOperManager.getId());
			moneyOper.setUserId(toUser.getId());
			moneyOper.setAccount(MoneyAccount.Yuepay.getCode());
			moneyOper.setType(MoneyOperType.TransferIn.getCode());
			moneyOper.setRelatedUserId(user.getId());
			moneyOper.setRelatedAccount(MoneyAccount.Yuepay.getCode());
			moneyOper.setStatus(MoneyOperStatus.Finished.getCode());
			moneyOper.setMoney(money);
			moneyOperManager.insert(moneyOper);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "转帐成功！");
	}

	public MoneyOperBO getBO(HttpServletRequest request) {
		MoneyOperBO moneyOperBO = getParameter(request);
		return moneyOperBO;
	}

	public static MoneyOperBO getParameter(HttpServletRequest request) {
		MoneyOperBO moneyOperBO = new MoneyOperBO();
		moneyOperBO.setId(RequestUtil.getParam(request, "id", Long.class));
		moneyOperBO.setAccount(request.getParameter("account"));
		moneyOperBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		moneyOperBO.setType(request.getParameter("type"));
		moneyOperBO.setRelatedUserId(RequestUtil.getParam(request, "relatedUserId", Long.class));
		moneyOperBO.setRelatedAccount(request.getParameter("relatedAccount"));
		moneyOperBO.setStatus(request.getParameter("status"));
		moneyOperBO.setMoney(RequestUtil.getParam(request, "money", Long.class));
		moneyOperBO.setFeature(request.getParameter("feature"));
		PagerUtil.initPage(request, moneyOperBO);
		return moneyOperBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return moneyOperManager;
	}

}
