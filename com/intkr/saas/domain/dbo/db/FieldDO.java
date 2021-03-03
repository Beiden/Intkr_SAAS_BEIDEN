package com.intkr.saas.domain.dbo.db;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @table field_tab
 * 
 * @author Beiden
 * @date 2020-04-02 21:01:47
 * @version 1.0
 */
public class FieldDO extends BaseDO<FieldDO> {

	private static final long serialVersionUID = 1L;

	// Saas
	private Long saasId;

	// database
	private Long databaseId;

	// table
	private Long tableId;

	// db_name
	private String dbName;

	// name
	private String name;

	// db_type
	private String dbType;

	// type
	private String type;

	// db_length
	private Integer dbLength;

	// allow_null
	private Integer allowNull;

	// links
	private String links;

	// searchType
	private String searchType;

	// showType
	private String showType;

	// note
	private String note;

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

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
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

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDbLength() {
		return dbLength;
	}

	public void setDbLength(Integer dbLength) {
		this.dbLength = dbLength;
	}

	public Integer getAllowNull() {
		return allowNull;
	}

	public void setAllowNull(Integer allowNull) {
		this.allowNull = allowNull;
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

	public String getLinks() {
		return links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

}
