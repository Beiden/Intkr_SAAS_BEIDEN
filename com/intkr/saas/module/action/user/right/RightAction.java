package com.intkr.saas.module.action.user.right;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.user.RightBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.user.RightManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-4-11 上午9:17:10
 * @version 1.0
 */
public class RightAction extends BaseAction<RightBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RightManager rightManager = IOC.get(RightManager.class);

	public RightBO getBO(HttpServletRequest request) {
		RightBO rightBO = getParameter(request);
		return rightBO;
	}

	public static RightBO getParameter(HttpServletRequest request) {
		RightBO rightBO = new RightBO();
		rightBO.setSys(RequestUtil.getParam(request, "sys"));
		rightBO.setId(RequestUtil.getParam(request, "id", Long.class));
		rightBO.setCode(RequestUtil.getParam(request, "code"));
		rightBO.setType(RequestUtil.getParam(request, "type"));
		rightBO.setName(RequestUtil.getParam(request, "name"));
		rightBO.setNote(RequestUtil.getParam(request, "note"));
		rightBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		rightBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, rightBO);
		return rightBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return rightManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			RightBO bo = getBO(request);
			bo.setSort(bo.getId().doubleValue());
			rightManager.insert(bo);
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
			RightBO bo = getBO(request);
			RightBO oldBO = rightManager.get(bo.getId());
			rightManager.update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
