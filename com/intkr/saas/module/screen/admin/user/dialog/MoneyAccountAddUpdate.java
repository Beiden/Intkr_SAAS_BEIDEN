package com.intkr.saas.module.screen.admin.user.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.user.MoneyAccountBO;
import com.intkr.saas.manager.user.MoneyAccountManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class MoneyAccountAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyAccountManager manager = IOC.get("MoneyAccountManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String shopMoneyAccountId = RequestUtil.getParam(request, "shopMoneyAccountId");
		MoneyAccountBO shopMoneyAccount = manager.get(shopMoneyAccountId);
		request.setAttribute("shopMoneyAccount", shopMoneyAccount);
		request.setAttribute("addId", manager.getId());
	}

}
