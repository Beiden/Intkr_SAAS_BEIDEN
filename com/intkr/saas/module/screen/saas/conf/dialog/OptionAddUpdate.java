package com.intkr.saas.module.screen.saas.conf.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class OptionAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			OptionBO optionBO = optionManager.get(id);
			request.setAttribute("option", optionBO);
		} else {
			request.setAttribute("addId", optionManager.getId());
		}
		SaasAction.fillSaas(request);
	}

}
