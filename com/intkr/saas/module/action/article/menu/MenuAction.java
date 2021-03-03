package com.intkr.saas.module.action.article.menu;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.menu.FrontMenuBO;
import com.intkr.saas.domain.bo.menu.FrontMenuDetailBO;
import com.intkr.saas.manager.cms.menu.FrontMenuDetailManager;
import com.intkr.saas.manager.cms.menu.FrontMenuManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-10-15 下午4:01:36
 * @version 1.0
 */
public class MenuAction {

	private FrontMenuManager frontMenuManager = IOC.get(FrontMenuManager.class);

	private FrontMenuDetailManager frontMenuDetailManager = IOC.get(FrontMenuDetailManager.class);

	@StartTransaction
	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontMenuBO menu = get(request, response);
		frontMenuManager.insert(menu);
		if (menu.getDetails() != null) {
			for (FrontMenuDetailBO detail : menu.getDetails()) {
				detail.setId(frontMenuDetailManager.getId());
				frontMenuDetailManager.insert(detail);
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "添加成功");
	}

	@StartTransaction
	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idString = RequestUtil.getParam(request, "deleteId");
		frontMenuManager.delete(idString);
		request.setAttribute("result", true);
		request.setAttribute("msg", "删除成功");
	}

	@StartTransaction
	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FrontMenuBO menu = get(request, response);
		frontMenuManager.update(menu);
		List<FrontMenuDetailBO> newDetails = menu.getDetails();
		List<FrontMenuDetailBO> oldDetails = frontMenuDetailManager.getByFrontMenuId(menu.getId());
		List<FrontMenuDetailBO> deleteds = getDeleted(oldDetails, newDetails);
		for (FrontMenuDetailBO detail : deleteds) {
			frontMenuDetailManager.delete(detail.getId());
		}
		List<FrontMenuDetailBO> adds = getAdded(oldDetails, newDetails);
		for (FrontMenuDetailBO add : adds) {
			add.setId(frontMenuDetailManager.getId());
			frontMenuDetailManager.insert(add);
		}
		List<FrontMenuDetailBO> updateds = getUpdated(oldDetails, newDetails);
		for (FrontMenuDetailBO updated : updateds) {
			frontMenuDetailManager.update(updated);
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新成功");
	}

	private List<FrontMenuDetailBO> getUpdated(List<FrontMenuDetailBO> oldDetails, List<FrontMenuDetailBO> newDetails) {
		List<FrontMenuDetailBO> updateds = new ArrayList<FrontMenuDetailBO>();
		for (FrontMenuDetailBO oldDetail : oldDetails) {
			for (FrontMenuDetailBO detailTmp : newDetails) {
				if (oldDetail.getId().equals(detailTmp.getId())) {
					updateds.add(detailTmp);
				}
			}
		}
		return updateds;
	}

	private List<FrontMenuDetailBO> getAdded(List<FrontMenuDetailBO> oldDetails, List<FrontMenuDetailBO> newDetails) {
		List<FrontMenuDetailBO> adds = new ArrayList<FrontMenuDetailBO>();
		for (FrontMenuDetailBO newDetail : newDetails) {
			if (!contain(oldDetails, newDetail)) {
				adds.add(newDetail);
			}
		}
		return adds;
	}

	private List<FrontMenuDetailBO> getDeleted(List<FrontMenuDetailBO> oldDetails, List<FrontMenuDetailBO> newDetails) {
		List<FrontMenuDetailBO> deleteds = new ArrayList<FrontMenuDetailBO>();
		for (FrontMenuDetailBO oldDetail : oldDetails) {
			if (!contain(newDetails, oldDetail)) {
				deleteds.add(oldDetail);
			}
		}
		return deleteds;
	}

	private boolean contain(List<FrontMenuDetailBO> details, FrontMenuDetailBO detail) {
		for (FrontMenuDetailBO detailTmp : details) {
			if (detail.getId().equals(detailTmp.getId())) {
				return true;
			}
		}
		return false;
	}

	private FrontMenuBO get(HttpServletRequest request, HttpServletResponse response) {
		FrontMenuBO menu = null;
		if (RequestUtil.existParam(request, "id")) {
			Long id = RequestUtil.getParam(request, "id", Long.class);
			menu = frontMenuManager.get(id);
		} else {
			menu = new FrontMenuBO();
			menu.setId(frontMenuManager.getId());
			menu.setSaasId(SessionClient.getSaasId(request));
		}
		menu.setName(RequestUtil.getParam(request, "name"));
		String[] detailIds = request.getParameterValues("detailIds");
		menu.setDetails(new ArrayList<FrontMenuDetailBO>());
		if (detailIds != null) {
			Double sort = 1D;
			for (String id : detailIds) {
				FrontMenuDetailBO detail = new FrontMenuDetailBO();
				detail.setId(Long.valueOf(id));
				detail.setSaasId(SessionClient.getSaasId(request));
				detail.setFrontMenuId(menu.getId());
				detail.setLevel(Integer.valueOf(RequestUtil.getParam(request, id + "Level")));
				detail.setType(RequestUtil.getParam(request, id + "Type"));
				detail.setPid(RequestUtil.getParam(request, id + "Pid", Long.class));
				detail.setName(RequestUtil.getParam(request, id + "Name"));
				detail.setUrl(RequestUtil.getParam(request, id + "Url"));
				detail.setTitle(RequestUtil.getParam(request, id + "Title"));
				detail.setRelatedId(RequestUtil.getParam(request, id + "RelatedId", Long.class));
				detail.setSort(sort++);
				menu.addDetail(detail);
			}
		}
		return menu;
	}

}
