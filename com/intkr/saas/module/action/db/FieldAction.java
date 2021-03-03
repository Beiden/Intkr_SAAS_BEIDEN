package com.intkr.saas.module.action.db;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.db.FieldManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table field_tab
 * 
 * @author Beiden
 * @date 2020-04-02 21:01:47
 * @version 1.0
 */
public class FieldAction extends BaseAction<FieldBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private FieldManager fieldManager = IOC.get(FieldManager.class);

	public FieldBO getBO(HttpServletRequest request) {
		FieldBO fieldBO = getParameter(request);
		return fieldBO;
	}

	public static FieldBO getParameter(HttpServletRequest request) {
		FieldBO fieldBO = new FieldBO();
		fieldBO.setId(RequestUtil.getParam(request, "id", Long.class));
		fieldBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		fieldBO.setDatabaseId(RequestUtil.getParam(request, "databaseId", Long.class));
		fieldBO.setTableId(RequestUtil.getParam(request, "tableId", Long.class));
		fieldBO.setDbName(RequestUtil.getParam(request, "dbName", String.class));
		fieldBO.setName(RequestUtil.getParam(request, "name", String.class));
		fieldBO.setDbType(RequestUtil.getParam(request, "dbType", String.class));
		fieldBO.setType(RequestUtil.getParam(request, "type", String.class));
		fieldBO.setSearchType(RequestUtil.getParam(request, "searchType", String.class));
		fieldBO.setShowType(RequestUtil.getParam(request, "showType", String.class));
		fieldBO.setDbLength(RequestUtil.getParam(request, "dbLength", Integer.class));
		fieldBO.setAllowNull(RequestUtil.getParam(request, "allowNull", Integer.class));
		fieldBO.setNote(RequestUtil.getParam(request, "note", String.class));
		fieldBO.setLinks(RequestUtil.getParam(request, "links", String.class));
		fieldBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		fieldBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, fieldBO);
		return fieldBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return fieldManager;
	}

	public void doAddLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			FieldBO field = fieldManager.get(RequestUtil.getParam(request, "id"));
			String url = RequestUtil.getParam(request, "url");
			String value = RequestUtil.getParam(request, "value", "");
			String title = RequestUtil.getParam(request, "title", "");
			field.addLink(value, url, title);
			fieldManager.update(field);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdateLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			FieldBO field = fieldManager.get(RequestUtil.getParam(request, "id"));
			String url = RequestUtil.getParam(request, "url");
			String value = RequestUtil.getParam(request, "value", "");
			String title = RequestUtil.getParam(request, "title");
			field.updateLink(value, url, title);
			fieldManager.update(field);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doMoveLeftLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			FieldBO field = fieldManager.get(RequestUtil.getParam(request, "id"));
			String value = RequestUtil.getParam(request, "value", "");
			field.moveLeftLink(value);
			fieldManager.update(field);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doRemoveLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			FieldBO field = fieldManager.get(RequestUtil.getParam(request, "id"));
			String value = RequestUtil.getParam(request, "value", "");
			field.removeLink(value);
			fieldManager.update(field);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doMoveRightLink(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			FieldBO field = fieldManager.get(RequestUtil.getParam(request, "id"));
			String value = RequestUtil.getParam(request, "value", "");
			field.moveRightLink(value);
			fieldManager.update(field);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doMoveUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long fieldId = RequestUtil.getParam(request, "fieldId", Long.class);
			Long tableId = RequestUtil.getParam(request, "tableId", Long.class);
			FieldBO query = new FieldBO();
			query.setQuery("orderBy", "sort");
			query.setQuery("order", "asc");
			query.setTableId(tableId);
			query.set_pageSize(1000);
			List<FieldBO> list = fieldManager.select(query);
			int i = 0;
			for (; i < list.size(); i++) {
				FieldBO tableTmp = list.get(i);
				if (tableTmp.getId().equals(fieldId)) {
					break;
				}
			}
			if (i == 0) {

			} else if (i == 1) {
				FieldBO tableTmp = list.get(i - 1);
				FieldBO table = list.get(i);
				table.setSort(tableTmp.getSort() - 1);
				fieldManager.update(table);
			} else {
				FieldBO tableTmp1 = list.get(i - 2);
				FieldBO tableTmp = list.get(i - 1);
				FieldBO table = list.get(i);
				table.setSort((tableTmp.getSort() + tableTmp1.getSort()) / 2);
				fieldManager.update(table);
			}
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doMoveDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long fieldId = RequestUtil.getParam(request, "fieldId", Long.class);
			Long tableId = RequestUtil.getParam(request, "tableId", Long.class);
			FieldBO query = new FieldBO();
			query.setQuery("orderBy", "sort");
			query.setQuery("order", "asc");
			query.setTableId(tableId);
			query.set_pageSize(1000);
			List<FieldBO> list = fieldManager.select(query);
			int i = 0;
			for (; i < list.size(); i++) {
				FieldBO tableTmp = list.get(i);
				if (tableTmp.getId().equals(fieldId)) {
					break;
				}
			}
			if (i == list.size() - 1) {

			} else if (i == list.size() - 2) {
				FieldBO tableTmp = list.get(i + 1);
				FieldBO table = list.get(i);
				table.setSort(tableTmp.getSort() + 1);
				fieldManager.update(table);
			} else {
				FieldBO tableTmp1 = list.get(i + 2);
				FieldBO tableTmp = list.get(i + 1);
				FieldBO table = list.get(i);
				table.setSort((tableTmp.getSort() + tableTmp1.getSort()) / 2);
				fieldManager.update(table);
			}
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

}
