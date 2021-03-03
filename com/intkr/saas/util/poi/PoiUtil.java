package com.intkr.saas.util.poi;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 
 * @author Beiden
 * 
 * @date 2010-11-11 下午1:54:49
 * @version 1.0
 */
public class PoiUtil {

	public static List<List<String>> readXls(String fileName) {
		Integer sheetIndex = 0;
		if (fileName.endsWith(".xlsx")) {
			return readXlsx(fileName, sheetIndex);
		} else {
			return readXls(fileName, sheetIndex);
		}
	}

	public static List<List<String>> readXls(String fileName, Integer sheetIndex) {
		try {
			InputStream is = new FileInputStream(fileName);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			HSSFSheet sheet = hssfWorkbook.getSheetAt(sheetIndex);
			if (sheet == null) {
				return new ArrayList<List<String>>();
			}
			return warpSheet(sheet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<List<String>> readXlsx(String fileName, Integer sheetIndex) {
		try {
			InputStream is = new FileInputStream(fileName);
			XSSFWorkbook xSSFWorkbook = new XSSFWorkbook(is);
			XSSFSheet sheet = xSSFWorkbook.getSheetAt(sheetIndex);
			if (sheet == null) {
				return new ArrayList<List<String>>();
			}
			return warpSheet(sheet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static List<List<String>> warpSheet(HSSFSheet sheet) {
		List<List<String>> returnList = new ArrayList<List<String>>();
		HSSFRow firstRow = sheet.getRow(0);
		int lastColumnNum = firstRow.getLastCellNum();
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			HSSFRow row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			List<String> rowData = warpRow(row, lastColumnNum);
			if (rowData != null && !rowData.isEmpty()) {
				returnList.add(rowData);
			}
		}
		return returnList;
	}

	private static List<List<String>> warpSheet(XSSFSheet sheet) {
		List<List<String>> returnList = new ArrayList<List<String>>();
		XSSFRow firstRow = sheet.getRow(0);
		int lastColumnNum = firstRow.getLastCellNum();
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			XSSFRow row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			List<String> rowData = warpRow(row, lastColumnNum);
			if (rowData != null && !rowData.isEmpty()) {
				returnList.add(rowData);
			}
		}
		return returnList;
	}

	private static List<String> warpRow(HSSFRow row, int lastColumnNum) {
		List<String> rowMap = new ArrayList<String>();
		for (int i = 0; i < lastColumnNum; i++) {
			Cell cell = row.getCell(i);
			Object value = getValue(cell);
			rowMap.add(value.toString());
		}
		return rowMap;
	}

	private static List<String> warpRow(XSSFRow row, int lastColumnNum) {
		List<String> rowMap = new ArrayList<String>();
		for (int i = 0; i < lastColumnNum; i++) {
			Cell cell = row.getCell(i);
			Object value = getValue(cell);
			rowMap.add(value.toString());
		}
		return rowMap;
	}

	private static Object getValue(Cell cell) {
		try {
			if (cell == null) {
				return "";
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
