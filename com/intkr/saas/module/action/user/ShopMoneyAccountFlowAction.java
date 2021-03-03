package com.intkr.saas.module.action.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyAccountFlowBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.MoneyAccountFlowManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @moneyOperor Beiden
 * @date 2016-4-15 下午2:30:49
 * @version 1.0
 */
public class ShopMoneyAccountFlowAction extends BaseAction<MoneyAccountFlowBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountFlowManager moneyFlowManager = IOC.get("MoneyAccountFlowManager");

	public MoneyAccountFlowBO getBO(HttpServletRequest request) {
		MoneyAccountFlowBO moneyOperBO = getParameter(request);
		return moneyOperBO;
	}

	public static MoneyAccountFlowBO getParameter(HttpServletRequest request) {
		MoneyAccountFlowBO moneyOperBO = new MoneyAccountFlowBO();
		moneyOperBO.setId(RequestUtil.getParam(request, "id", Long.class));
		moneyOperBO.setRelatedId(RequestUtil.getParam(request, "relatedId", Long.class));
		moneyOperBO.setAccountId(RequestUtil.getParam(request, "accountId", Long.class));
		moneyOperBO.setType(RequestUtil.getParam(request, "type"));
		moneyOperBO.setToAccountId(RequestUtil.getParam(request, "toAccountId", Long.class));
		moneyOperBO.setStatus(RequestUtil.getParam(request, "status"));
		moneyOperBO.setMoney(RequestUtil.getParam(request, "money", Long.class));
		moneyOperBO.setFeature(RequestUtil.getParam(request, "feature"));
		moneyOperBO.setNote(RequestUtil.getParam(request, "note"));
		PagerUtil.initPage(request, moneyOperBO);
		return moneyOperBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return moneyFlowManager;
	}

}
