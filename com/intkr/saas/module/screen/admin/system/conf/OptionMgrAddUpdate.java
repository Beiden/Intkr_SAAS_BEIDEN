package com.intkr.saas.module.screen.admin.system.conf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.conf.OptionBO;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:48:18
 * @version 1.0
 */
public class OptionMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			OptionBO optionBO = optionManager.get(id);
			request.setAttribute("dto", optionBO);
		} else {
			request.setAttribute("addId", optionManager.getId());
		}
	}

}
