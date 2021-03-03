package com.intkr.saas.module.screen.admin.article.consult.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.consult.ConsultTypeBO;
import com.intkr.saas.manager.cms.consult.ConsultTypeManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ConsultTypeAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ConsultTypeManager manager = IOC.get(ConsultTypeManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String cmsConsultTypeId = RequestUtil.getParam(request, "cmsConsultTypeId");
		ConsultTypeBO cmsConsultType = manager.get(cmsConsultTypeId);
		request.setAttribute("cmsConsultType", cmsConsultType);
		if (cmsConsultType == null) {
			request.setAttribute("addId", manager.getId());
		}
	}

}
