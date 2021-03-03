package com.intkr.saas.module.action.lang;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.lang.LangContentTranslateBO;
import com.intkr.saas.manager.lang.LangContentTranslateManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
public class LangContentTranslateAction extends BaseAction<LangContentTranslateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LangContentTranslateManager langContentTranslateManager = IOC.get(LangContentTranslateManager.class);

	public LangContentTranslateBO getBO(HttpServletRequest request) {
		LangContentTranslateBO langContentTranslateBO = getParameter(request);
		return langContentTranslateBO;
	}

	public static LangContentTranslateBO getParameter(HttpServletRequest request) {
		LangContentTranslateBO langContentTranslateBO = new LangContentTranslateBO();
		langContentTranslateBO.setId(RequestUtil.getParam(request, "id", Long.class));
		langContentTranslateBO.setContentId(RequestUtil.getParam(request, "contentId", Long.class));
		langContentTranslateBO.setLangId(RequestUtil.getParam(request, "langId", String.class));
		langContentTranslateBO.setContent(RequestUtil.getParam(request, "content", String.class));
		langContentTranslateBO.setGmtModifier(RequestUtil.getParam(request, "gmtModifier", String.class));
		PagerUtil.initPage(request, langContentTranslateBO);
		return langContentTranslateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return langContentTranslateManager;
	}

}
