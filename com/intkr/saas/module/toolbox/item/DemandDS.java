package com.intkr.saas.module.toolbox.item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.domain.bo.item.DemandBO;
import com.intkr.saas.domain.type.item.DemandStatus;
import com.intkr.saas.domain.type.item.DemandType;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.DemandManager;
import com.intkr.saas.manager.user.UserManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

public class DemandDS extends BaseToolBox {

	private DemandManager demandManager = IOC.get(DemandManager.class);

	private UserManager userManager = IOC.get(UserManager.class);

	private ImgManager mediaManager = IOC.get(ImgManager.class);

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public DemandBO select(HttpServletRequest request, HttpServletResponse response) {
		DemandBO query = new DemandBO();
		query.setType(DemandType.Normal.getCode());
		if (RequestUtil.existParam(request, "_page") && RequestUtil.existParam(request, "_pageSize")) {
			query.set_pageSize(RequestUtil.getParam(request, "_pageSize", Integer.class));
			query.set_page(RequestUtil.getParam(request, "_page", Integer.class));
		}
		if (RequestUtil.existParam(request, "categoryId")) {
			query.setQuery("categoryId", request.getParameter("categoryId"));
		}
		if (RequestUtil.existParam(request, "searchWord")) {
			query.setQuery("searchWord", request.getParameter("searchWord") + "%");
		}
		query.setStatus(DemandStatus.Approve.getCode());
		demandManager.selectAndCount(query);
		return query;
	}

	public DemandBO getById(Long articleId) {
		if (!IdEngine.isValidate(articleId)) {
			return null;
		}
		DemandBO demand = demandManager.get(articleId);
		if (demand == null) {
			return null;
		}
		if (DemandStatus.Approve.getCode().equals(demand.getStatus())) {
			return demand;
		}
		return null;
	}

	public DemandBO fillUser(DemandBO demand) {
		userManager.fill(demand);
		return demand;
	}

	public DemandBO fillImg(DemandBO demand) {
		mediaManager.fill(demand);
		return demand;
	}

	public DemandBO fillsImg(DemandBO demand) {
		mediaManager.fills(demand);
		return demand;
	}

}
