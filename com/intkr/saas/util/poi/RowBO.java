package com.intkr.saas.util.poi;

import java.util.ArrayList;
import java.util.List;

public class RowBO {

	private List<ColumnBO> columns;

	public List<ColumnBO> getColumns() {
		if (columns == null) {
			columns = new ArrayList<ColumnBO>();
		}
		return columns;
	}

	public void setColumns(List<ColumnBO> columns) {
		this.columns = columns;
	}

	public void addColumn(ColumnBO column) {
		initColumns();
		columns.add(column);
	}

	public void addColumn(String column) {
		initColumns();
		columns.add(new ColumnBO(column));
	}

	private void initColumns() {
		if (columns == null) {
			columns = new ArrayList<ColumnBO>();
		}
	}

}
