package com.intkr.saas.domain.bo.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.JsonUtil;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
public class TableBO extends BaseBO<TableBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// database
	private Long databaseId;

	// type
	private String type;

	// pid
	private Long pid;

	// db_name
	private String dbName;

	// 名称
	private String name;

	// 备注
	private String note;

	// 索引描述
	private String indexDesc;

	// 拓展字段
	private String feature;

	// 权重
	private Double sort;

	private TableBO parent;

	private List<TableBO> childs;

	private List<FieldBO> fields;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Long databaseId) {
		this.databaseId = databaseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.toJson(map);
	}

	public TableBO getParent() {
		return parent;
	}

	public void setParent(TableBO parent) {
		this.parent = parent;
	}

	public List<TableBO> getChilds() {
		return childs;
	}

	public void setChilds(List<TableBO> childs) {
		this.childs = childs;
	}

	public void addChild(TableBO child) {
		if (childs == null) {
			childs = new ArrayList<TableBO>();
		}
		childs.add(child);
	}

	public List<FieldBO> getFields() {
		return fields;
	}

	public void setFields(List<FieldBO> fields) {
		this.fields = fields;
	}

	public String getCountSql(HttpServletRequest request) {
		initFilter(request);
		PagerUtil.initPage(request, this);
		String where = "";
		List<Object> list = new ArrayList<Object>();
		for (FieldBO field : getFields()) {
			if (field.getProperty("filterValue") != null) {
				String key = field.getDbName();
				String compare = field.getProperty("filterCompare", "=");
				if ("".equals(where)) {
					where += " " + key + " " + compare + " ? ";
				} else {
					where += " AND " + key + " " + compare + " ? ";
				}
				Object value = field.getProperty(field.getDbName());
				list.add(value);
			}
		}
		if (where.length() > 1) {
			where = " where " + where;
		}
		String sql = "select count(*) from " + getDbName() + " " + where + " limit 1";
		return sql;
	}

	public String getSelectSql(HttpServletRequest request) {
		initFilter(request);
		PagerUtil.initPage(request, this);
		String where = "";
		List<Object> list = new ArrayList<Object>();
		for (FieldBO field : getFields()) {
			if (field.getProperty("filterValue") != null) {
				String key = field.getDbName();
				String compare = field.getProperty("filterCompare", "=");
				if ("".equals(where)) {
					where += " " + key + " " + compare + " ? ";
				} else {
					where += " AND " + key + " " + compare + " ? ";
				}
				Object value = field.getProperty(field.getDbName());
				list.add(value);
			}
		}
		if (where.length() > 1) {
			where = " where " + where;
		}
		String sql = "select * from " + getDbName() + " " + where + " limit " + get_offset() + "," + get_pageSize();
		return sql;
	}

	public List<Object> getSelectSqlParam(HttpServletRequest request) {
		initFilter(request);
		PagerUtil.initPage(request, this);
		String where = "";
		List<Object> list = new ArrayList<Object>();
		for (FieldBO field : getFields()) {
			if (field.getProperty("filterValue") != null) {
				String key = field.getDbName();
				String compare = field.getProperty("filterCompare", "=");
				where += " " + key + " " + compare + " ? ";
				Object value = field.getProperty("filterValue");
				list.add(value);
			}
		}
		if (where.length() > 1) {
			where = " where " + where;
		}
		String sql = "select * from " + getDbName() + " " + where + " limit " + get_offset() + "," + get_pageSize();
		return list;
	}

	private void initFilter(HttpServletRequest request) {
		for (FieldBO field : getFields()) {
			if (RequestUtil.existParam(request, field.getDbName())) {
				field.setProperty("filterValue", RequestUtil.getParam(request, field.getDbName()));
				if (RequestUtil.existParam(request, field.getDbName() + "-compare")) {
					field.setProperty("filterCompare", RequestUtil.getParam(request, field.getDbName() + "-compare"));
				} else {
					field.setProperty("filterCompare", "=");
				}
			}
		}
	}

	public String getIndexDesc() {
		return indexDesc;
	}

	public void setIndexDesc(String indexDesc) {
		this.indexDesc = indexDesc;
	}

	public List<FieldBO> getKeySearchField() {
		if (fields == null) {
			return new ArrayList<FieldBO>();
		}
		List<FieldBO> list = new ArrayList<FieldBO>();
		for (FieldBO field : fields) {
			if ("keySearch".equalsIgnoreCase(field.getSearchType())) {
				list.add(field);
			}
		}
		return list;
	}

	public List<FieldBO> getShowField() {
		if (fields == null) {
			return new ArrayList<FieldBO>();
		}
		List<FieldBO> list = new ArrayList<FieldBO>();
		for (FieldBO field : fields) {
			if ("show".equalsIgnoreCase(field.getShowType())) {
				list.add(field);
			}
		}
		return list;
	}

	public List<FieldBO> getOptionSearchField() {
		if (fields == null) {
			return new ArrayList<FieldBO>();
		}
		List<FieldBO> list = new ArrayList<FieldBO>();
		for (FieldBO field : fields) {
			if ("optionSearch".equalsIgnoreCase(field.getSearchType())) {
				list.add(field);
			}
		}
		return list;
	}

	public boolean isKeySearch(String fieldName) {
		if (fields == null) {
			return false;
		}
		for (FieldBO field : fields) {
			if (!field.getDbName().equalsIgnoreCase(fieldName)) {
				continue;
			}
			if ("keySearch".equalsIgnoreCase(field.getSearchType())//
					|| field.getSearchType() == null //
					|| "".equals(field.getSearchType())//
			) {
				return true;
			}
		}
		return false;
	}

	public boolean isOptionSearch(String fieldName) {
		if (fields == null) {
			return false;
		}
		for (FieldBO field : fields) {
			if (!field.getDbName().equalsIgnoreCase(fieldName)) {
				continue;
			}
			if ("optionSearch".equalsIgnoreCase(field.getSearchType())) {
				return true;
			}
		}
		return false;
	}

	public boolean isShowField(String fieldName) {
		if (fields == null) {
			return false;
		}
		for (FieldBO field : fields) {
			if (!field.getDbName().equalsIgnoreCase(fieldName)) {
				continue;
			}
			if ("show".equalsIgnoreCase(field.getShowType())//
					|| field.getShowType() == null //
					|| "".equals(field.getShowType())//
			) {
				return true;
			}
		}
		return false;
	}

}
