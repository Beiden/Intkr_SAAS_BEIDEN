package com.intkr.saas.util.poi;

import java.io.OutputStream;

/**
 * 
 * @author Beiden
 */
public class ExcelUtil {

	public static ExcelBO parse(String fileName) {
		return ExcelReader.parse(fileName);
	}

	public static void write(ExcelBO excel) {
		ExcelWriter.write(excel);
	}

	public static void write(ExcelBO excel, OutputStream fos) {
		ExcelWriter.write(excel, fos);
	}

}
