package com.intkr.saas.util.db;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.util.StringUtil;

/**
 * 
 * @author Beiden
 * @date 2017-2-20 下午2:30:50
 * @version 1.0
 */
public class TableDO {

	// 数据库表的名称
	private String name;
	private String proName;
	private String clazName;

	// 数据库表的描述
	private String desc = "";
	private String indexDesc = "";

	private List<ColumnDO> columns = new ArrayList<ColumnDO>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.proName = StringUtil.hump(name);
		this.clazName = StringUtil.upperCaseFirstCharater(this.proName);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void addColumn(ColumnDO columnDO) {
		if (columnDO != null) {
			columns.add(columnDO);
		}
	}

	public List<ColumnDO> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnDO> columns) {
		this.columns = columns;
	}

	public String getProName() {
		return proName;
	}

	public String getClazName() {
		return clazName;
	}

	public boolean containDate() {
		TableDO table = this;
		if (table.getColumns() == null) {
			return false;
		}
		for (ColumnDO column : table.getColumns()) {
			if ("gmt_created".equals(column.getName()) || "gmt_modified".equals(column.getName())) {
				continue;
			}
			if ("Date".equalsIgnoreCase(column.getClazType())) {
				return true;
			}
		}
		return false;
	}

	public boolean hasFeature() {
		TableDO table = this;
		if (table.getColumns() == null) {
			return false;
		}
		for (ColumnDO column : table.getColumns()) {
			if ("feature".equalsIgnoreCase(column.getName())) {
				return true;
			}
		}
		return false;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setClazName(String clazName) {
		this.clazName = clazName;
		this.proName = StringUtil.lowerCaseFirstCharater(clazName);
	}

	public String getIndexDesc() {
		return indexDesc;
	}

	public void setIndexDesc(String indexDesc) {
		this.indexDesc = indexDesc;
	}

}
