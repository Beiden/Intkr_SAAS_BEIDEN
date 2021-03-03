package com.intkr.saas.module.screen.admin.lang.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.manager.lang.LangContentManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 内容
 * 
 * @table lang_content
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:16
 * @version 1.0
 */
public class LangContentAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private LangContentManager manager = IOC.get(LangContentManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String langContentId = RequestUtil.getParam(request, "langContentId");
		LangContentBO langContent = manager.get(langContentId);
		request.setAttribute("langContent", langContent);
		request.setAttribute("addId", manager.getId());
	}

}
