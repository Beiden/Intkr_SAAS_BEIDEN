package com.intkr.saas.module.screen.admin.article.consult.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.consult.ConsultBO;
import com.intkr.saas.domain.type.article.ConsultStatus;
import com.intkr.saas.manager.cms.consult.ConsultManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ConsultAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ConsultManager manager = IOC.get(ConsultManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String cmsConsultId = RequestUtil.getParam(request, "cmsConsultId");
		ConsultBO cmsConsult = manager.get(cmsConsultId);
		request.setAttribute("cmsConsult", cmsConsult);
		if (cmsConsult == null) {
			request.setAttribute("addId", manager.getId());
		}
		request.setAttribute("ConsultStatus", ConsultStatus.Error);
	}

}
