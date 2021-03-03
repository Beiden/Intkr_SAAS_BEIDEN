package com.intkr.saas.module.action.lang;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.manager.lang.LangContentManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 内容
 * 
 * @table lang_content
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:16
 * @version 1.0
 */
public class LangContentAction extends BaseAction<LangContentBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LangContentManager langContentManager = IOC.get(LangContentManager.class);

	public LangContentBO getBO(HttpServletRequest request) {
		LangContentBO langContentBO = getParameter(request);
		return langContentBO;
	}

	public static LangContentBO getParameter(HttpServletRequest request) {
		LangContentBO langContentBO = new LangContentBO();
		langContentBO.setId(RequestUtil.getParam(request, "id", Long.class));
		langContentBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		langContentBO.setCategory(RequestUtil.getParam(request, "category", String.class));
		langContentBO.setContent(RequestUtil.getParam(request, "content", String.class));
		langContentBO.setGmtModifier(RequestUtil.getParam(request, "gmtModifier", String.class));
		PagerUtil.initPage(request, langContentBO);
		return langContentBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return langContentManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LangContentBO bo = getBO(request);
		bo.setId(null);
		if (langContentManager.exist(bo)) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "重复翻译！");
			return;
		}
		super.doAdd(request, response);
	}

}
