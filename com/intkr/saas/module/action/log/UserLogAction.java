package com.intkr.saas.module.action.log;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.bo.log.UserLogBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.log.UserLogManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 用户操作日志
 * 
 * @author Beiden
 * @date 2019-10-12 上午9:56:12
 * @version 1.0.0
 */
public class UserLogAction extends BaseAction<UserLogBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserLogManager userLogManager = IOC.get(UserLogManager.class);

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public UserLogBO getBO(HttpServletRequest request) {
		UserLogBO carUserLogBO = getParameter(request);
		return carUserLogBO;
	}

	public static UserLogBO getParameter(HttpServletRequest request) {
		UserLogBO carUserLogBO = new UserLogBO();
		carUserLogBO.setId(RequestUtil.getParam(request, "id", Long.class));
		carUserLogBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		carUserLogBO.setTime(DateUtil.parse(RequestUtil.getParam(request, "time")));
		carUserLogBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		carUserLogBO.setStaffId(RequestUtil.getParam(request, "staffId", Long.class));
		carUserLogBO.setStaffName(RequestUtil.getParam(request, "staffName"));
		carUserLogBO.setType(RequestUtil.getParam(request, "type"));
		carUserLogBO.setObj(RequestUtil.getParam(request, "obj"));
		carUserLogBO.setNote(RequestUtil.getParam(request, "note"));
		carUserLogBO.setIp(RequestUtil.getParam(request, "ip"));
		carUserLogBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, carUserLogBO);
		return carUserLogBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return userLogManager;
	}

	// 开启|停止用户日志监控
	public void doSetUserLogFalg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
		if (saasId == null) {
			saasId = SessionClient.getSaasId(request);
		}
		OptionBO option = optionManager.getByName(saasId, "user_log");
		if (option == null) {
			option = new OptionBO();
			option.setSaasId(saasId);
			option.setId(optionManager.getId());
			option.setName("user_log");
			option.setValue("1");
			option.setAutoload(1);
			optionManager.insert(option);
		}
		if ("1".equals(option.getValue())) {
			option.setValue("2");
		} else {
			option.setValue("1");
		}
		optionManager.update(option);
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

	public void doLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String type = RequestUtil.getParam(request, "type");
			String obj = RequestUtil.getParam(request, "obj");
			UserLogClient.log(request, type, obj);
			request.setAttribute("result", true);
			request.setAttribute("msg", "成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String idString = RequestUtil.getParam(request, "deleteId");
			userLogManager.delete(idString);
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
			userLogManager.deleteAll(ids);
			request.setAttribute("result", true);
			request.setAttribute("msg", "批量删除成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
