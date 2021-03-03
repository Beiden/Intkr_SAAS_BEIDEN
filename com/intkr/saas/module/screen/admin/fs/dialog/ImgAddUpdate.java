package com.intkr.saas.module.screen.admin.fs.dialog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.client.log.UserLogClient;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-10-20 下午10:11:06
 * @version 1.0
 */
public class ImgAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private ImgManager imgManager = IOC.get(ImgManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = RequestUtil.getParam(request, "id");
		ImgBO img = imgManager.get(id);
		request.setAttribute("img", img);
		request.setAttribute("addId", imgManager.getId());
	}

}
