package com.intkr.saas.module.screen.admin.db;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.intkr.saas.domain.bo.db.TableBO;
import com.intkr.saas.manager.db.DatabaseManager;
import com.intkr.saas.manager.db.TableManager;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;
import com.intkr.saas.util.poi.ColumnBO;
import com.intkr.saas.util.poi.ExcelBO;
import com.intkr.saas.util.poi.ExcelUtil;
import com.intkr.saas.util.poi.RowBO;
import com.intkr.saas.util.poi.SheetBO;

/**
 * 
 * @author Beiden
 * @date 2017-1-25 下午2:37:29
 * @version 1.0
 */
public class DatabaseDownload {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private BufferedRequestContext brc;

	private DatabaseManager databaseManager = IOC.get(DatabaseManager.class);

	private TableManager tableManager = IOC.get(TableManager.class);

	public void execute() throws Exception {
		try {
			brc.setBuffering(false);// 为了节省内存，关闭buffering

			Long databaseId = RequestUtil.getParam(request, "databaseId", Long.class);
			List<TableBO> tables = databaseManager.getMenu(databaseId);

			ExcelBO excel = new ExcelBO();
			excel.setPath("database.xls");
			writeDirectory(tables, excel);
			for (TableBO table : tables) {
				writeTable(excel, table);
			}

			OutputStream out = response.getOutputStream();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setHeader("Content-Disposition", "attachment;filename=database.xls");
			response.setDateHeader("Expires", 0);
			response.setContentType("application/vnd.ms-excel");

			ExcelUtil.write(excel, out);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	private void writeDirectory(List<TableBO> tables, ExcelBO excel) {
		SheetBO sheet = excel.createSheet("目录");
		for (TableBO table : tables) {
			writeDirectoryRow(sheet, table);
		}
	}

	private void writeDirectoryRow(SheetBO sheet, TableBO table) {
		RowBO row = sheet.createRow();
		TableBO parent = table.getParent();
		while (parent != null) {
			row.addColumn(new ColumnBO("-"));
			parent = parent.getParent();
		}
		row.addColumn(new ColumnBO(table.getName()));
		row.addColumn(new ColumnBO(table.getDbName()));
		if (table.getChilds() != null) {
			for (TableBO child : table.getChilds()) {
				writeDirectoryRow(sheet, child);
			}
		}
	}

	private void writeTable(ExcelBO excel, TableBO table) {
		if (!"directory".equalsIgnoreCase(table.getType())) {
			TableBO tableFull = tableManager.getFull(table.getId());
			TableDownload.writeTab(excel, tableFull);
		}
		if (table.getChilds() != null) {
			for (TableBO child : table.getChilds()) {
				writeTable(excel, child);
			}
		}
	}

}
