package com.intkr.saas.domain.bo.db;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @table datasource_tab
 * 
 * @author Beiden
 * @date 2020-04-02 18:59:02
 * @version 1.0
 */
public class SqlResultBO {

	private boolean result;

	private String sql;

	private List<String> headers;

	private List<List<Object>> datas;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public void addHeader(String header) {
		if (this.headers == null) {
			this.headers = new ArrayList<String>();
		}
		this.headers.add(header);
	}

	public List<List<Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<List<Object>> datas) {
		this.datas = datas;
	}

	public void addRow(List<Object> data) {
		if (this.datas == null) {
			this.datas = new ArrayList<List<Object>>();
		}
		this.datas.add(data);
	}

}
