package com.intkr.saas.module.action.user.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RoleRightBO;
import com.intkr.saas.domain.bo.user.RoleBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.RoleRightManager;
import com.intkr.saas.manager.user.RoleManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:29:02
 * @version 1.0
 */
public class RoleAction extends BaseAction<RoleBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RoleManager roleManager = IOC.get(RoleManager.class);

	private RoleRightManager roleRightManager = IOC.get(RoleRightManager.class);

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			RoleBO bo = getBO(request);
			if (roleManager.getByCode(bo.getCode()) != null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "编码已存在！");
				return;
			}
			roleManager.insert(bo);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("id", bo.getId());
			request.setAttribute("result", true);
			request.setAttribute("data", data);
			request.setAttribute("msg", "添加成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			RoleBO bo = getBO(request);
			RoleBO oldBO = roleManager.get(bo.getId());
			if (!oldBO.getCode().equals(bo.getCode()) && roleManager.getByCode(bo.getCode()) != null) {
				request.setAttribute("result", false);
				request.setAttribute("msg", "编码已存在！");
				return;
			}
			roleManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public RoleBO getBO(HttpServletRequest request) {
		RoleBO roleBO = getParameter(request);
		return roleBO;
	}

	public static RoleBO getParameter(HttpServletRequest request) {
		RoleBO roleBO = new RoleBO();
		roleBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		roleBO.setId(RequestUtil.getParam(request, "id", Long.class));
		roleBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		roleBO.setType(RequestUtil.getParam(request, "type"));
		roleBO.setCode(RequestUtil.getParam(request, "code"));
		roleBO.setName(RequestUtil.getParam(request, "name"));
		roleBO.setNote(RequestUtil.getParam(request, "note"));
		roleBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, roleBO);
		return roleBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return roleManager;
	}

	/**
	 * 授权
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void doAuth(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long roleId = RequestUtil.getParam(request, "roleId", Long.class);
		String[] newRights = request.getParameterValues("rightIds");
		if (newRights == null) {
			newRights = new String[0];
		}
		List<RoleRightBO> oldAuthList = roleRightManager.selectByRoleId(roleId);
		List<String> addRight = new ArrayList<String>();
		for (String newRight : newRights) {
			if (!contain(oldAuthList, newRight)) {
				addRight.add(newRight);
				RoleRightBO newAuth = new RoleRightBO();
				newAuth.setId(roleRightManager.getId());
				newAuth.setRightId(Long.valueOf(newRight));
				newAuth.setRoleId(roleId);
				roleRightManager.insert(newAuth);
			}
		}
		for (RoleRightBO oldRight : oldAuthList) {
			if (!contain(newRights, oldRight)) {
				roleRightManager.delete(oldRight.getId());
			}
		}
		request.setAttribute("result", true);
		request.setAttribute("msg", "授权成功");
	}

	private boolean contain(String[] newRights, RoleRightBO oldRight) {
		for (String newRight : newRights) {
			if (oldRight.getRoleId().equals(newRight)) {
				return true;
			}
		}
		return false;
	}

	private boolean contain(List<RoleRightBO> oldAuthList, String newRight) {
		for (RoleRightBO oldRight : oldAuthList) {
			if (oldRight.getRoleId().equals(newRight)) {
				return true;
			}
		}
		return false;
	}

}
