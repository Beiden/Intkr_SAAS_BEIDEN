package com.intkr.saas.module.screen.admin.fs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.type.fs.MediaType;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-15 下午1:03:11
 * @version 1.0
 */
public class VedioMgrAddUpdate {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private MediaManager mediaManager = IOC.get(MediaManager.class);

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "id")) {
			String id = RequestUtil.getParam(request, "id");
			MediaBO fileBO = mediaManager.get(id);
			request.setAttribute("dto", fileBO);
		} else {
			request.setAttribute("addId", mediaManager.getId());
		}
		request.setAttribute("MediaTypeList", MediaType.values());
	}

}
