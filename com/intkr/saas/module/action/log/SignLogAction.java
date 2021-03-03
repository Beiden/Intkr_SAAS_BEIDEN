package com.intkr.saas.module.action.log;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.log.SignLogBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.log.SignLogManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 用户登录日志
 * 
 * @author Beiden
 * @date 2019-10-12 上午9:52:42
 * @version 1.0.0
 */
public class SignLogAction extends BaseAction<SignLogBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private SignLogManager signLogManager = IOC.get(SignLogManager.class);

	public SignLogBO getBO(HttpServletRequest request) {
		SignLogBO signLogBO = getParameter(request);
		return signLogBO;
	}

	public static SignLogBO getParameter(HttpServletRequest request) {
		SignLogBO signLogBO = new SignLogBO();
		signLogBO.setId(RequestUtil.getParam(request, "id", Long.class));
		signLogBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		signLogBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		signLogBO.setType(RequestUtil.getParam(request, "type"));
		signLogBO.setIp(RequestUtil.getParam(request, "ip"));
		signLogBO.setCountry(RequestUtil.getParam(request, "country"));
		signLogBO.setProvince(RequestUtil.getParam(request, "province"));
		signLogBO.setCity(RequestUtil.getParam(request, "city"));
		signLogBO.setArea(RequestUtil.getParam(request, "area"));
		signLogBO.setIsp(RequestUtil.getParam(request, "isp"));
		PagerUtil.initPage(request, signLogBO);
		return signLogBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return signLogManager;
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String idString = RequestUtil.getParam(request, "deleteId");
			signLogManager.delete(idString);
			request.setAttribute("result", true);
			request.setAttribute("msg", "删除成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doDeleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String idsString = RequestUtil.getParam(request, "deleteIds");
			String[] idsStrings = idsString.split(",");
			List<Object> ids = new ArrayList<Object>(idsStrings.length);
			for (String idString : idsStrings) {
				ids.add(idString);
			}
			signLogManager.deleteAll(ids);
			request.setAttribute("result", true);
			request.setAttribute("msg", "批量删除成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
