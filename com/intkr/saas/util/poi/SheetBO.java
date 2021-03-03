package com.intkr.saas.util.poi;

import java.util.ArrayList;
import java.util.List;

public class SheetBO {

	// sheet名称
	private String name;

	private List<RowBO> rows;

	public List<RowBO> getRows() {
		if (rows == null) {
			rows = new ArrayList<RowBO>();
		}
		return rows;
	}

	public void setRows(List<RowBO> rows) {
		this.rows = rows;
	}

	public void addRow(RowBO row) {
		if (rows == null) {
			rows = new ArrayList<RowBO>();
		}
		rows.add(row);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RowBO createRow() {
		RowBO row = new RowBO();
		addRow(row);
		return row;
	}

}
