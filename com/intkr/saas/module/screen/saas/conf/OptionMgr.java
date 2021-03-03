package com.intkr.saas.module.screen.saas.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.module.action.conf.OptionAction;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:48:02
 * @version 1.0
 */
public class OptionMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		OptionBO query = OptionAction.getParameter(request);
		query = optionManager.selectAndCount(query);
		request.setAttribute("query", query);
		request.setAttribute("list", query.getDatas());
		SaasAction.fillSaas(request);
	}

}
