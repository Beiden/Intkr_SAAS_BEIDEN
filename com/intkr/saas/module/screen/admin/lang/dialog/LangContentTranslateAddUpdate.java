package com.intkr.saas.module.screen.admin.lang.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.lang.LangContentTranslateBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.lang.LangContentTranslateManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
public class LangContentTranslateAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LangContentTranslateManager manager = IOC.get(LangContentTranslateManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String langContentTranslateId = RequestUtil.getParam(request, "langContentTranslateId");
		LangContentTranslateBO langContentTranslate = manager.get(langContentTranslateId);
		request.setAttribute("langContentTranslate", langContentTranslate);
		request.setAttribute("addId", manager.getId());
	}

}
