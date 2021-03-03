package com.intkr.saas.module.action.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.distributed.search.client.ImgSearchClient;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.fs.ImgManager;
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
public class ImgAction extends BaseAction<ImgBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ImgManager imgManager = IOC.get(ImgManager.class);

	private ImgSearchClient imgSearchClient = IOC.get(ImgSearchClient.class);

	public ImgBO getBO(HttpServletRequest request) {
		ImgBO cmsImgBO = getParameter(request);
		return cmsImgBO;
	}

	public static ImgBO getParameter(HttpServletRequest request) {
		ImgBO img = new ImgBO();
		img.setId(RequestUtil.getParam(request, "id", Long.class));
		img.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		img.setCategoryId(RequestUtil.getParam(request, "categoryId", Long.class));
		img.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		img.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		img.setName(RequestUtil.getParam(request, "name"));
		img.setRealname(RequestUtil.getParam(request, "realname"));
		img.setUri(RequestUtil.getParam(request, "uri"));
		img.setNote(RequestUtil.getParam(request, "note"));
		img.setFeature(RequestUtil.getParam(request, "feature"));
		if (RequestUtil.existParam(request, "searchData")) {
			img.setQuery("searchData", RequestUtil.getParam(request, "searchData"));
		}
		PagerUtil.initPage(request, img);
		return img;
	}

	public BaseManager<?, ?> getBaseManager() {
		return imgManager;
	}

	public void doSelectOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ImgBO query = getParameter(request);
			ImgBO media = imgManager.selectOne(query);
			request.setAttribute("data", media);
			request.setAttribute("result", true);
			request.setAttribute("msg", "获取成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doFullDump(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long saasId = SessionClient.getSaasId(request);
		imgSearchClient.fullDump(saasId);
		request.setAttribute("result", true);
		request.setAttribute("msg", "操作成功！");
	}

}
