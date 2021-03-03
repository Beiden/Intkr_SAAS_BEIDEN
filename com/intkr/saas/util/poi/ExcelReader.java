package com.intkr.saas.util.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.intkr.saas.util.DateUtil;

public class ExcelReader {

	public static ExcelBO parse(String fileName) {
		if (fileName.endsWith(".xlsx")) {
			return parseXlsx(fileName);
		} else {
			return parseXls(fileName);
		}
	}

	public static ExcelBO parseXls(String fileName) {
		try {
			ExcelBO excel = new ExcelBO();
			InputStream is = new FileInputStream(fileName);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			int sheetSize = hssfWorkbook.getNumberOfSheets();
			int sheetIndex = 0;
			while (sheetIndex < sheetSize) {
				HSSFSheet sheet = hssfWorkbook.getSheetAt(sheetIndex);
				if (sheet == null) {
					break;
				}
				sheetIndex++;
				excel.addSheet(warpSheet(sheet));
			}
			return excel;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ExcelBO parseXlsx(String fileName) {
		try {
			ExcelBO excel = new ExcelBO();
			InputStream is = new FileInputStream(fileName);
			XSSFWorkbook xSSFWorkbook = new XSSFWorkbook(is);
			int sheetSize = xSSFWorkbook.getNumberOfSheets();
			int sheetIndex = 0;
			while (sheetIndex < sheetSize) {
				XSSFSheet sheet = xSSFWorkbook.getSheetAt(sheetIndex);
				if (sheet == null) {
					break;
				}
				sheetIndex++;
				excel.addSheet(warpSheet(sheet));
			}
			return excel;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static SheetBO warpSheet(HSSFSheet hssfSheet) {
		SheetBO sheet = new SheetBO();
		HSSFRow firstRow = hssfSheet.getRow(0);
		int lastColumnNum = firstRow.getLastCellNum();
		for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow row = hssfSheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			RowBO rowData = warpRow(row, lastColumnNum);
			sheet.addRow(rowData);
		}
		return sheet;
	}

	private static SheetBO warpSheet(XSSFSheet xssfSheet) {
		SheetBO sheet = new SheetBO();
		XSSFRow firstRow = xssfSheet.getRow(0);
		if (firstRow == null) {
			return sheet;
		}
		int lastColumnNum = firstRow.getLastCellNum();
		for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			XSSFRow row = xssfSheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			RowBO rowData = warpRow(row, lastColumnNum);
			sheet.addRow(rowData);
		}
		return sheet;
	}

	private static RowBO warpRow(HSSFRow row, int lastColumnNum) {
		RowBO rowMap = new RowBO();
		for (int i = 0; i < lastColumnNum; i++) {
			Cell cell = row.getCell(i);
			Object value = getValue(cell);
			rowMap.addColumn(value.toString());
		}
		return rowMap;
	}

	private static RowBO warpRow(XSSFRow row, int lastColumnNum) {
		RowBO rowMap = new RowBO();
		for (int i = 0; i < lastColumnNum; i++) {
			Cell cell = row.getCell(i);
			Object value = getValue(cell);
			rowMap.addColumn(value.toString());
		}
		return rowMap;
	}

	private static Object getValue(Cell cell) {
		try {
			if (cell == null) {
				return "";
			} else if (cell.getCellType() != HSSFCell.CELL_TYPE_STRING && HSSFDateUtil.isCellDateFormatted(cell)) {
				double d = cell.getNumericCellValue();
				if (d == 0d) {
					return "";
				}
				Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
				String value = DateUtil.formatDateTime(date);
				return value;
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 返回布尔类型的值
				return cell.getBooleanCellValue();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 返回数值类型的值
				return cell.getNumericCellValue();
			} else {// 返回字符串类型的值
				try {
					return cell.getStringCellValue();
				} catch (Exception e) {
					return "";
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("cell=" + cell, e);
		}
	}

	public static class XSSFDateUtil extends org.apache.poi.ss.usermodel.DateUtil {
		protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
			return org.apache.poi.ss.usermodel.DateUtil.absoluteDay(cal, use1904windowing);
		}
	}

}
