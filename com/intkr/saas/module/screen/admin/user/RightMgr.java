package com.intkr.saas.module.screen.admin.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RightBO;
import com.intkr.saas.domain.type.user.RightType;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:29:59
 * @version 1.0
 */
public class RightMgr {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RightManager rightManager = IOC.get(RightManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List<RightBO> list = rightManager.getAllFull("saas");
		request.setAttribute("list", list);
		request.setAttribute("RightType", RightType.Error);
		SaasAction.fillSaas(request);
	}

}