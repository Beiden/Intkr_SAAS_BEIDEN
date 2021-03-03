package com.intkr.saas.module.screen.admin.user.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RightBO;
import com.intkr.saas.domain.type.user.RightType;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.module.action.saas.SaasAction;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:30:11
 * @version 1.0
 */
public class RightAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RightManager rightManager = IOC.get(RightManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			RightBO rightBO = rightManager.get(id);
			request.setAttribute("dto", rightBO);
		} else {
			request.setAttribute("addId", rightManager.getId());
		}
		request.setAttribute("RightType", RightType.Error);
		request.setAttribute("allRightList", rightManager.getAllFull("saas"));
		SaasAction.fillSaas(request);
	}

}
