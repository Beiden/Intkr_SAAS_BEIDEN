package com.intkr.saas.module.screen.opa.media.media;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.module.action.fs.MediaAction;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-6-5 上午8:10:20
 * @version 1.0
 */
public class Search {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MediaAction action = IOC.get(MediaAction.class);
		action.doQueryAndCount(request, response);
	}

}
