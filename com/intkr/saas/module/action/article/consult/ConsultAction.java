package com.intkr.saas.module.action.article.consult;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.consult.ConsultBO;
import com.intkr.saas.domain.bo.consult.ConsultTypeBO;
import com.intkr.saas.domain.type.article.ConsultStatus;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.cms.consult.ConsultManager;
import com.intkr.saas.manager.cms.consult.ConsultTypeManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.IpUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 下午10:11:06
 * @version 1.0
 */
public class ConsultAction extends BaseAction<ConsultBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ConsultManager cmsConsultManager = IOC.get(ConsultManager.class);

	private ConsultTypeManager cmsConsultTypeManager = IOC.get(ConsultTypeManager.class);

	public ConsultBO getBO(HttpServletRequest request) {
		ConsultBO cmsConsultBO = getParameter(request);
		return cmsConsultBO;
	}

	public static ConsultBO getParameter(HttpServletRequest request) {
		ConsultBO cmsConsultBO = new ConsultBO();
		cmsConsultBO.setId(RequestUtil.getParam(request, "id", Long.class));
		cmsConsultBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		cmsConsultBO.setTypeId(RequestUtil.getParam(request, "typeId", Long.class));
		cmsConsultBO.setType(RequestUtil.getParam(request, "type"));
		cmsConsultBO.setIp(RequestUtil.getParam(request, "ip"));
		cmsConsultBO.setCompany(RequestUtil.getParam(request, "company"));
		cmsConsultBO.setAddress(RequestUtil.getParam(request, "address"));
		cmsConsultBO.setWebsite(RequestUtil.getParam(request, "website"));
		cmsConsultBO.setName(RequestUtil.getParam(request, "name"));
		cmsConsultBO.setEmail(RequestUtil.getParam(request, "email"));
		cmsConsultBO.setPhone(RequestUtil.getParam(request, "phone"));
		cmsConsultBO.setTel(RequestUtil.getParam(request, "tel"));
		cmsConsultBO.setStatus(RequestUtil.getParam(request, "status"));
		cmsConsultBO.setTitle(RequestUtil.getParam(request, "title"));
		cmsConsultBO.setContent(RequestUtil.getParam(request, "content"));
		for (Object key : request.getParameterMap().keySet()) {
			String name = (String) key;
			if (name.startsWith("feature_")) {
				cmsConsultBO.setFeature(name, request.getParameter(name));
			}
		}
		PagerUtil.initPage(request, cmsConsultBO);
		return cmsConsultBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return cmsConsultManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ConsultBO bo = getBO(request);
			if (bo.getType() != null && cmsConsultTypeManager.getByName(bo.getType()) == null) {
				ConsultTypeBO type = new ConsultTypeBO();
				type.setId(cmsConsultTypeManager.getId());
				type.setName(bo.getType());
				type.setStatus("on");
				cmsConsultTypeManager.insert(type);
				bo.setTypeId(type.getId());
			}
			bo.setId(cmsConsultManager.getId());
			bo.setIp(IpUtil.getIp(request));
			if (bo.getStatus() == null) {
				bo.setStatus(ConsultStatus.WaitAudit.getCode());
			}
			cmsConsultManager.insert(bo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", bo.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
