package com.intkr.saas.module.action.log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.log.LogBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.log.LogManager;
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
public class LogAction extends BaseAction<LogBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LogManager logManager = IOC.get(LogManager.class);

	public LogBO getBO(HttpServletRequest request) {
		LogBO signLogBO = getParameter(request);
		return signLogBO;
	}

	public static LogBO getParameter(HttpServletRequest request) {
		LogBO signLogBO = new LogBO();
		signLogBO.setId(RequestUtil.getParam(request, "id", Long.class));
		signLogBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		signLogBO.setGroupName(RequestUtil.getParam(request, "groupName"));
		signLogBO.setContent(RequestUtil.getParam(request, "content"));
		PagerUtil.initPage(request, signLogBO);
		return signLogBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return logManager;
	}

	public void doLog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long saasId = SessionClient.getSaasId(request);
			String name = RequestUtil.getParam(request, "name");
			if (name == null || "".equals(name)) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "日志name为空！");
				return;
			}
			String content = RequestUtil.getParam(request, "content");
			LogBO log = new LogBO();
			log.setId(logManager.getId());
			log.setSaasId(saasId);
			log.setGroupName(name);
			log.setContent(content);
			logManager.insert(log);
			request.setAttribute("result", true);
			request.setAttribute("msg", "记录成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
