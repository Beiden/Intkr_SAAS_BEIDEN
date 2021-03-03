package com.intkr.saas.module.action.db;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.db.DatabaseManager;
import com.intkr.saas.manager.db.FieldManager;
import com.intkr.saas.manager.db.TableManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
public class TableAction extends BaseAction<TableBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private TableManager tableManager = IOC.get(TableManager.class);

	private FieldManager fieldManager = IOC.get(FieldManager.class);

	private DatabaseManager databaseManager = IOC.get(DatabaseManager.class);

	public TableBO getBO(HttpServletRequest request) {
		TableBO tableBO = getParameter(request);
		return tableBO;
	}

	public static TableBO getParameter(HttpServletRequest request) {
		TableBO tableBO = new TableBO();
		tableBO.setId(RequestUtil.getParam(request, "id", Long.class));
		tableBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		tableBO.setDatabaseId(RequestUtil.getParam(request, "databaseId", Long.class));
		tableBO.setType(RequestUtil.getParam(request, "type", String.class));
		tableBO.setPid(RequestUtil.getParam(request, "pid", Long.class));
		tableBO.setDbName(RequestUtil.getParam(request, "dbName", String.class));
		tableBO.setName(RequestUtil.getParam(request, "name", String.class));
		tableBO.setNote(RequestUtil.getParam(request, "note", String.class));
		tableBO.setIndexDesc(RequestUtil.getParam(request, "desc", String.class));
		tableBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		tableBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		if (RequestUtil.existParam(request, "dbNameLike")) {
			tableBO.setQuery("dbNameLike", RequestUtil.getParam(request, "dbNameLike"));
			tableBO.setQuery("dbNameLikeSQL", "%" + RequestUtil.getParam(request, "dbNameLike") + "%");
		}
		if (RequestUtil.existParam(request, "nameLike")) {
			tableBO.setQuery("nameLike", RequestUtil.getParam(request, "nameLike"));
			tableBO.setQuery("nameLikeSQL", "%" + RequestUtil.getParam(request, "nameLike") + "%");
		}
		PagerUtil.initPage(request, tableBO);
		return tableBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return tableManager;
	}

	public void doSyncTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long datasourceId = RequestUtil.getParam(request, "datasourceId", Long.class);
			Long databaseId = RequestUtil.getParam(request, "databaseId", Long.class);
			String tableName = RequestUtil.getParam(request, "tableName", String.class);
			databaseManager.syncTable(datasourceId, databaseId, tableName);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doCopyTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "tableId", Long.class);
			TableBO table = tableManager.get(tableId);
			table.setId(tableManager.getId());
			table.setSort(table.getId().doubleValue());
			tableManager.insert(table);
			resortMenuTree(table.getDatabaseId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdatePid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "id", Long.class);
			Long pid = RequestUtil.getParam(request, "pid", Long.class);
			TableBO table = tableManager.get(tableId);
			table.setPid(pid);
			tableManager.update(table);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdateNote(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "id", Long.class);
			String note = RequestUtil.getParam(request, "note");
			TableBO table = tableManager.get(tableId);
			table.setNote(note);
			tableManager.update(table);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdateDesc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "id", Long.class);
			String desc = RequestUtil.getParam(request, "desc", "");
			tableManager.updateIndexDesc(tableId, desc);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdateName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "id", Long.class);
			String name = RequestUtil.getParam(request, "name");
			TableBO table = tableManager.get(tableId);
			table.setName(name);
			tableManager.update(table);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdateDbName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "id", Long.class);
			String name = RequestUtil.getParam(request, "name");
			TableBO table = tableManager.get(tableId);
			table.setDbName(name);
			tableManager.update(table);
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
			Long tableId = RequestUtil.getParam(request, "id", Long.class);
			TableBO table = tableManager.get(tableId);
			List<TableBO> tableTree = databaseManager.getMenu(table.getDatabaseId());
			List<TableBO> list = findLevel(tableId, tableTree);
			int i = 0;
			for (; i < list.size(); i++) {
				TableBO tableTmp = list.get(i);
				if (tableTmp.getId().equals(tableId)) {
					break;
				}
			}
			if (i >= 1) {// 不是第一个
				TableBO prev = list.get(i - 1);
				Double sort = prev.getSort();
				prev.setSort(table.getSort());
				table.setSort(sort);
				tableManager.update(table);
				tableManager.update(prev);
			}
			resortMenuTree(table.getDatabaseId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doCheckSort(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long databaseId = RequestUtil.getParam(request, "databaseId", Long.class);
			resortMenuTree(databaseId);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	private void resortMenuTree(Long databaseId) {
		List<TableBO> tableTree = databaseManager.getMenu(databaseId);
		resortMenuTree(tableTree);
	}

	private void resortMenuTree(List<TableBO> tableTree) {
		if (tableTree == null || tableTree.isEmpty()) {
			return;
		}
		boolean needResort = false;
		for (int i = 0; i < tableTree.size() - 1; i++) {
			TableBO table = tableTree.get(i);
			resortMenuTree(table.getChilds());
			TableBO next = tableTree.get(i + 1);
			if (table.getSort().equals(next.getSort())) {
				needResort = true;
			}
		}
		if (needResort) {
			for (int i = 0; i < tableTree.size(); i++) {
				TableBO table = tableTree.get(i);
				table.setSort((i + 1) * 10d);
				tableManager.update(table);
			}
		}
	}

	private List<TableBO> findLevel(Long tableId, List<TableBO> tableTree) {
		if (tableTree == null) {
			return null;
		}
		for (TableBO api : tableTree) {
			if (api.getId().equals(tableId)) {
				return tableTree;
			} else {
				List<TableBO> findLevel = findLevel(tableId, api.getChilds());
				if (findLevel != null) {
					return findLevel;
				}
			}
		}
		return null;
	}

	public void doMoveDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "id", Long.class);
			TableBO table = tableManager.get(tableId);
			List<TableBO> tableTree = databaseManager.getMenu(table.getDatabaseId());
			List<TableBO> list = findLevel(tableId, tableTree);
			int i = 0;
			for (; i < list.size(); i++) {
				TableBO tableTmp = list.get(i);
				if (tableTmp.getId().equals(tableId)) {
					break;
				}
			}
			if (i <= list.size() - 2) {
				TableBO next = list.get(i + 1);
				Double sort = next.getSort();
				next.setSort(table.getSort());
				table.setSort(sort);
				tableManager.update(table);
				tableManager.update(next);
			}
			resortMenuTree(table.getDatabaseId());
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doUpdateSearchType(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			Long tableId = RequestUtil.getParam(request, "tableId", Long.class);
			TableBO table = tableManager.getFull(tableId);
			for (FieldBO field : table.getFields()) {
				String searchType = RequestUtil.getParam(request, field.getDbName() + "-searchType");
				String name = RequestUtil.getParam(request, field.getDbName() + "-name");
				String showType = RequestUtil.getParam(request, field.getDbName() + "-showType");
				boolean update = false;
				if (!name.equalsIgnoreCase(field.getName())) {
					field.setName(name);
					update = true;
				}
				if (!searchType.equalsIgnoreCase(field.getSearchType())) {
					field.setSearchType(searchType);
					update = true;
				}
				if (!showType.equalsIgnoreCase(field.getShowType())) {
					field.setShowType(showType);
					update = true;
				}
				if (update) {
					fieldManager.update(field);
				}
			}
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功！");
		} catch (Exception e) {
			logger.error("params=" + request.getParameterMap(), e);
			request.setAttribute("result", false);
			request.setAttribute("msg", "系统异常，请稍后再试！");
		}
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doAdd(request, response);
		resortMenuTree(RequestUtil.getParam(request, "databaseId", Long.class));
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.doUpdate(request, response);
		resortMenuTree(RequestUtil.getParam(request, "databaseId", Long.class));
	}

}
