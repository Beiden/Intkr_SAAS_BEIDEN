package com.intkr.saas.module.action.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.fs.MediaBO;
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
public class MediaAction extends BaseAction<MediaBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MediaManager mediaManager = IOC.get(MediaManager.class);

	public MediaBO getBO(HttpServletRequest request) {
		MediaBO mediaBO = getParameter(request);
		return mediaBO;
	}

	public static MediaBO getParameter(HttpServletRequest request) {
		MediaBO mediaBO = new MediaBO();
		mediaBO.setId(RequestUtil.getParam(request, "id", Long.class));
		mediaBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		mediaBO.setCategoryId(RequestUtil.getParam(request, "categoryId", Long.class));
		mediaBO.setType(RequestUtil.getParam(request, "type"));
		mediaBO.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		mediaBO.setName(RequestUtil.getParam(request, "name"));
		mediaBO.setRealname(RequestUtil.getParam(request, "realname"));
		mediaBO.setUri(RequestUtil.getParam(request, "uri"));
		mediaBO.setNote(RequestUtil.getParam(request, "note"));
		mediaBO.setFeature(RequestUtil.getParam(request, "feature"));
		PagerUtil.initPage(request, mediaBO);
		return mediaBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return mediaManager;
	}

	public void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			MediaBO query = getParameter(request);
			MediaBO media = mediaManager.selectOne(query);
			request.setAttribute("data", media);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
