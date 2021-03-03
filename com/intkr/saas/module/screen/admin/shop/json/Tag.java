package com.intkr.saas.module.screen.admin.shop.json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.article.ArticleTagBO;
import com.intkr.saas.manager.cms.article.TagManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-21 下午10:37:45
 * @version 1.0
 */
public class Tag {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TagManager tagManager = IOC.get("TagManager");

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String tagId = RequestUtil.getParam(request, "tagId");
		ArticleTagBO tag = tagManager.get(tagId);
		request.setAttribute("tag", tag);
	}

}
