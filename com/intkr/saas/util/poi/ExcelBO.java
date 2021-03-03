package com.intkr.saas.util.poi;

import java.util.ArrayList;
import java.util.List;

public class ExcelBO {

	// 文件路径
	private String path;

	private List<SheetBO> sheets;

	public List<SheetBO> getSheets() {
		return sheets;
	}

	public void setSheets(List<SheetBO> sheets) {
		this.sheets = sheets;
	}

	public void addSheet(SheetBO sheet) {
		if (sheets == null) {
			sheets = new ArrayList<SheetBO>();
		}
		sheets.add(sheet);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public SheetBO createSheet(String sheetName) {
		SheetBO sheet = new SheetBO();
		sheet.setName(sheetName);
		addSheet(sheet);
		return sheet;
	}

}
