package com.intkr.saas.module.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.util.RequestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Beiden
 * @date 2011-5-15 上午6:03:14
 * @version 1.0
 */
public abstract class BaseAction<BO extends BaseBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract BaseManager getBaseManager();

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BO bo = getBO(request);
			long id = getBaseManager().insert(bo);
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

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String idString = RequestUtil.getParam(request, "deleteId");
			int result = getBaseManager().delete(idString);
			request.setAttribute("data", result);
			request.setAttribute("result", true);
			if (result == 1) {
				request.setAttribute("msg", "删除成功！");
			} else if (result <= 0) {
				request.setAttribute("msg", "数据不存在！");
			} else if (result >= 0) {
				request.setAttribute("msg", "批量删除多条成功！");
			}
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doDeleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String idsString = RequestUtil.getParam(request, "deleteIds");
			String[] idsStrings = idsString.split(",");
			List<Object> ids = new ArrayList<Object>(idsStrings.length);
			for (String idString : idsStrings) {
				ids.add(idString);
			}
			getBaseManager().deleteAll(ids);
			request.setAttribute("result", true);
			request.setAttribute("msg", "批量删除成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BO bo = getBO(request);
			getBaseManager().update(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BO bo = getBO(request);
			Long count = getBaseManager().count(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "查询成功！");
			request.setAttribute("data", count);
			request.setAttribute("count", count);
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BO bo = getBO(request);
			List<BO> list = getBaseManager().select(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "查询成功！");
			request.setAttribute("data", list);
			request.setAttribute("query", list);
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doQueryAndCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			BO bo = getBO(request);
			getBaseManager().selectAndCount(bo);
			request.setAttribute("result", true);
			request.setAttribute("msg", "查询成功！");
			request.setAttribute("data", bo);
			request.setAttribute("query", bo);
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public abstract BO getBO(HttpServletRequest request);

}
