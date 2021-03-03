package com.intkr.saas.module.action.log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.bo.log.SysLogBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.log.SysLogManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 系统日志
 * 
 * @author Beiden
 * @date 2019-10-12 上午9:54:56
 * @version 1.0.0
 */
public class SysLogAction extends BaseAction<SysLogBO> {

	protected static final Logger logger = LoggerFactory.getLogger(SysLogAction.class);

	protected SysLogManager sysLogManager = IOC.get(SysLogManager.class);

	private OptionManager optionManager = IOC.get(OptionManager.class);

	public SysLogBO getBO(HttpServletRequest request) {
		return getParameter(request);
	}

	public static SysLogBO getParameter(HttpServletRequest request) {
		SysLogBO sysLogBO = new SysLogBO();
		sysLogBO.setId(RequestUtil.getParam(request, "id", Long.class));
		sysLogBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		sysLogBO.setIp(RequestUtil.getParam(request, "ip"));
		sysLogBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		sysLogBO.setReferer(RequestUtil.getParam(request, "referer"));
		sysLogBO.setGoUrl(RequestUtil.getParam(request, "goUrl"));
		sysLogBO.setQueryString(RequestUtil.getParam(request, "queryString"));
		sysLogBO.setGoAction(RequestUtil.getParam(request, "goAction"));
		sysLogBO.setGetUrl(RequestUtil.getParam(request, "getUrl"));
		sysLogBO.setParams(RequestUtil.getParam(request, "params"));
		sysLogBO.setFeature(RequestUtil.getParam(request, "feature"));
		sysLogBO.setServerCostTime(RequestUtil.getParam(request, "serverCostTime", Long.class));
		sysLogBO.setClientCostTime(RequestUtil.getParam(request, "clientCostTime", Long.class));
		PagerUtil.initPage(request, sysLogBO);
		return sysLogBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return sysLogManager;
	}

	// 开启|停止系统日志监控
	public void doSetSystemLogFalg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = RequestUtil.getParam(request, "saasId", Long.class);
		if (saasId == null) {
			saasId = SessionClient.getSaasId(request);
		}
		OptionBO option = optionManager.getByName(saasId, "system_log");
		if (option == null) {
			option = new OptionBO();
			option.setSaasId(saasId);
			option.setId(optionManager.getId());
			option.setName("system_log");
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

	public static LoadingCache<Long, Date> cahceBuilder = CacheBuilder.newBuilder()//
			.expireAfterAccess(5, TimeUnit.MINUTES).//
			maximumSize(100000).//
			build(new CacheLoader<Long, Date>() {
				public Date load(Long key) throws Exception {
					Calendar c = Calendar.getInstance();
					c.add(Calendar.MINUTE, -5);
					return c.getTime();
				}
			});

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String idString = RequestUtil.getParam(request, "deleteId");
			sysLogManager.delete(idString);
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
			sysLogManager.deleteAll(ids);
			request.setAttribute("result", true);
			request.setAttribute("msg", "批量删除成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (RequestUtil.existParam(request, "sysLog_id")) {
			Long sysLog_id = RequestUtil.getParam(request, "sysLog_id", Long.class);
			SysLogBO bo = sysLogManager.get(sysLog_id);
			if (bo != null) {
				Date startTime = cahceBuilder.get(sysLog_id);
				Date endTime = new Date();
				Long clientCostTime = endTime.getTime() - startTime.getTime();
				bo.setClientCostTime(clientCostTime);
				sysLogManager.update(bo);
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "记录成功");
	}

}
