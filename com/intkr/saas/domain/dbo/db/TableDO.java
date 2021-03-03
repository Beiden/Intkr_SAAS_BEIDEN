package com.intkr.saas.domain.dbo.db;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @table table_tab
 * 
 * @author Beiden
 * @date 2020-04-02 20:59:46
 * @version 1.0
 */
public class TableDO extends BaseDO<TableDO> {

	private static final long serialVersionUID = 1L;

	// Saas
	private Long saasId;

	// database
	private Long databaseId;

	// type
	private String type;

	// pid
	private Long pid;

	// db_name
	private String dbName;

	// name
	private String name;

	// note
	private String note;

	private String indexDesc;

	//
	private String feature;

	//
	private Double sort;

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

	public String getIndexDesc() {
		return indexDesc;
	}

	public void setIndexDesc(String indexDesc) {
		this.indexDesc = indexDesc;
	}

}
