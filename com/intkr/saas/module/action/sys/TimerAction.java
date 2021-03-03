package com.intkr.saas.module.action.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.timer.TimerManager;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.domain.bo.timer.TimerBO;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:55
 * @version 1.0
 */
public class TimerAction extends BaseAction<TimerBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TimerManager timerManager = IOC.get(TimerManager.class);

	public TimerBO getBO(HttpServletRequest request) {
		TimerBO timerBO = getParameter(request);
		return timerBO;
	}

	public static TimerBO getParameter(HttpServletRequest request) {
		TimerBO timerBO = new TimerBO();
		timerBO.setId(RequestUtil.getParam(request, "id", Long.class));
		timerBO.setStatus(RequestUtil.getParam(request, "status"));
		timerBO.setAction(RequestUtil.getParam(request, "action"));
		timerBO.setCode(RequestUtil.getParam(request, "code"));
		timerBO.setNote(RequestUtil.getParam(request, "note"));
		timerBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		timerBO.setTime(DateUtil.parse(RequestUtil.getParam(request, "time")));
		PagerUtil.initPage(request, timerBO);
		return timerBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return timerManager;
	}

	/**
	 * 设置
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		TimerBO timer = getBO(request);
		if (timer.getId() == null) {
			timer.setId(timerManager.getId());
			timerManager.insert(timer);
		} else {
			timerManager.update(timer);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "设置成功");
	}

}
