package com.intkr.saas.module.action.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.MoneyOperLogBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.MoneyOperLogManager;
import com.intkr.saas.manager.user.impl.MoneyOperLogManagerImpl;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-15 下午3:27:39
 * @version 1.0
 */
public class MoneyOperLogAction extends BaseAction<MoneyOperLogBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MoneyOperLogManager moneyOperLogManager = IOC.get(MoneyOperLogManager.class);

	public MoneyOperLogBO getBO(HttpServletRequest request) {
		MoneyOperLogBO moneyOperLogBO = getParameter(request);
		return moneyOperLogBO;
	}

	public static MoneyOperLogBO getParameter(HttpServletRequest request) {
		MoneyOperLogBO moneyOperLogBO = new MoneyOperLogBO();
		moneyOperLogBO.setId(RequestUtil.getParam(request, "id", Long.class));
		moneyOperLogBO.setOperId(RequestUtil.getParam(request, "operId", Long.class));
		moneyOperLogBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		moneyOperLogBO.setType(request.getParameter("type"));
		moneyOperLogBO.setRelatedType(request.getParameter("relatedType"));
		moneyOperLogBO.setRelatedId(RequestUtil.getParam(request, "relatedId", Long.class));
		moneyOperLogBO.setFeature(request.getParameter("feature"));
		PagerUtil.initPage(request, moneyOperLogBO);
		return moneyOperLogBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return moneyOperLogManager;
	}

}
