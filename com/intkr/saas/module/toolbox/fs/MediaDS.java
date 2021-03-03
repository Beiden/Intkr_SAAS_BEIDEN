package com.intkr.saas.module.toolbox.fs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.manager.fs.MediaManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class MediaDS extends BaseToolBox {

	private MediaManager mediaManager = IOC.get("MediaManager");

	public MediaBO getById(String id) {
		MediaBO bo = mediaManager.get(id);
		return bo;
	}

	public MediaBO select(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "page") && RequestUtil.existParam(request, "pageSize")) {
			return select(request, RequestUtil.getParam(request, "page", Integer.class), RequestUtil.getParam(request, "pageSize", Integer.class));
		} else {
			return select(request, 1, 20);
		}
	}

	public MediaBO select(HttpServletRequest request, Integer page, Integer pageSize) {
		MediaBO query = new MediaBO();
		query.set_pageSize(pageSize);
		query.set_page(page);
		mediaManager.selectAndCount(query);
		return query;
	}

	public List<MediaBO> selectAll(HttpServletRequest request) {
		MediaBO query = new MediaBO();
		query.set_pageSize(100000);
		List<MediaBO> list = mediaManager.select(query);
		return list;
	}

}
