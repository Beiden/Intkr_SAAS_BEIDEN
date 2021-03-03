package com.intkr.saas.module.action.lang;

import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.manager.BaseManager;
import javax.servlet.http.HttpServletRequest;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.lang.LanguageBO;
import com.intkr.saas.manager.lang.LanguageManager;
import com.intkr.saas.util.PagerUtil;

/**
 * 语言
 * 
 * @table language
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:03
 * @version 1.0
 */
public class LanguageAction extends BaseAction<LanguageBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LanguageManager languageManager = IOC.get(LanguageManager.class);

	public LanguageBO getBO(HttpServletRequest request) {
		LanguageBO languageBO = getParameter(request);
		return languageBO;
	}

	public static LanguageBO getParameter(HttpServletRequest request) {
		LanguageBO languageBO = new LanguageBO();
		languageBO.setId(RequestUtil.getParam(request, "id", Long.class));
		languageBO.setLangId(RequestUtil.getParam(request, "langId", String.class));
		languageBO.setName(RequestUtil.getParam(request, "name", String.class));
		languageBO.setNote(RequestUtil.getParam(request, "note", String.class));
		languageBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		PagerUtil.initPage(request, languageBO);
		return languageBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return languageManager;
	}

}
