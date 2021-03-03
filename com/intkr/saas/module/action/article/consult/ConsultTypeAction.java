package com.intkr.saas.module.action.article.consult;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.consult.ConsultTypeBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.manager.cms.consult.ConsultTypeManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class ConsultTypeAction extends BaseAction<ConsultTypeBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ConsultTypeManager cmsConsultTypeManager = IOC.get(ConsultTypeManager.class);

	public ConsultTypeBO getBO(HttpServletRequest request) {
		ConsultTypeBO cmsConsultTypeBO = getParameter(request);
		return cmsConsultTypeBO;
	}

	public static ConsultTypeBO getParameter(HttpServletRequest request) {
		ConsultTypeBO cmsConsultTypeBO = new ConsultTypeBO();
		cmsConsultTypeBO.setId(RequestUtil.getParam(request, "id", Long.class));
		cmsConsultTypeBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		cmsConsultTypeBO.setName(RequestUtil.getParam(request, "name"));
		cmsConsultTypeBO.setType(RequestUtil.getParam(request, "type"));
		cmsConsultTypeBO.setStatus(RequestUtil.getParam(request, "status"));
		cmsConsultTypeBO.setNote(RequestUtil.getParam(request, "note"));
		cmsConsultTypeBO.setFeature(RequestUtil.getParam(request, "feature"));
		cmsConsultTypeBO.setSort(RequestUtil.getParam(request, "sort" , Double.class));
		PagerUtil.initPage(request, cmsConsultTypeBO);
		return cmsConsultTypeBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return cmsConsultTypeManager;
	}

}
