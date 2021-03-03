package com.intkr.saas.module.toolbox.fs;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class ShopImgDS extends BaseToolBox {

	private ImgManager mediaManager = IOC.get("ImgManager");

	public ImgBO getById(String id) {
		ImgBO bo = mediaManager.get(id);
		return bo;
	}

	public ImgBO select(HttpServletRequest request, HttpServletResponse response) {
		if (RequestUtil.existParam(request, "page") && RequestUtil.existParam(request, "pageSize")) {
			return select(request, RequestUtil.getParam(request, "page", Integer.class), RequestUtil.getParam(request, "pageSize", Integer.class));
		} else {
			return select(request, 1, 20);
		}
	}

	public ImgBO select(HttpServletRequest request, Integer page, Integer pageSize) {
		ImgBO query = new ImgBO();
		query.set_pageSize(pageSize);
		query.set_page(page);
		mediaManager.selectAndCount(query);
		return query;
	}

	public List<ImgBO> selectAll(HttpServletRequest request) {
		ImgBO query = new ImgBO();
		query.set_pageSize(100000);
		List<ImgBO> list = mediaManager.select(query);
		return list;
	}

}
