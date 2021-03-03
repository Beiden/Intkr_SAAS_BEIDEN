package com.intkr.saas.util.poi;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	public static void write(ExcelBO excel) {
		try {
			Workbook workbook = createExcel(excel);
			FileOutputStream fos = new FileOutputStream(excel.getPath());
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	public static void write(ExcelBO excel, OutputStream fos) {
		try {
			Workbook workbook = createExcel(excel);
			workbook.write(fos);
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	private static Workbook createExcel(ExcelBO excel) {
		Workbook workbook = null;
		if (excel.getPath().toLowerCase().endsWith("xls")) {// 2003
			workbook = new XSSFWorkbook();
		} else if (excel.getPath().toLowerCase().endsWith("xlsx")) {// 2007
			workbook = new HSSFWorkbook();
		} else {
			// logger.debug("invalid file name,should be xls or xlsx");
		}
		for (SheetBO sheetBO : excel.getSheets()) {
			Sheet sheet = workbook.createSheet(sheetBO.getName());
			int rowIndex = 0;// 标识位，用于标识sheet的行号
			// 循环写入主表数据
			for (RowBO rowBO : sheetBO.getRows()) {
				// create sheet row
				Row row = sheet.createRow(rowIndex);
				for (int columnIndex = 0; columnIndex < rowBO.getColumns().size(); columnIndex++) {
					ColumnBO column = rowBO.getColumns().get(columnIndex);
					Cell cell = row.createCell(columnIndex);
					cell.setCellValue(column.getString());
				}
				rowIndex++;
			}
		}
		return workbook;
	}

	// 主函数
	public static void main(String[] args) {
		ExcelBO excel = new ExcelBO();
		excel.setPath("D:/text.xls");
		SheetBO sheet = excel.createSheet("sheetName");
		RowBO row = sheet.createRow();
		row.addColumn(new ColumnBO("1"));
		row.addColumn(new ColumnBO("2"));
		row.addColumn(new ColumnBO("3"));
		write(excel);
	}

}
