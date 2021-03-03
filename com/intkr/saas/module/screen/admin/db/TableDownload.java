package com.intkr.saas.module.screen.admin.db;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.buffered.BufferedRequestContext;
import com.intkr.saas.domain.bo.db.FieldBO;
import com.intkr.saas.domain.bo.db.TableBO;
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
public class TableDownload {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private BufferedRequestContext brc;

	private TableManager tableManager = IOC.get(TableManager.class);

	public void execute() throws Exception {
		try {
			// 为了节省内存，关闭buffering。
			brc.setBuffering(false);

			Long tableId = RequestUtil.getParam(request, "tableId", Long.class);
			TableBO table = tableManager.getFull(tableId);
			if (table == null) {
				return;
			}
			ExcelBO excel = new ExcelBO();
			excel.setPath(table.getName() + ".xls");
			writeTab(excel, table);
			OutputStream out = response.getOutputStream();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setHeader("Content-Disposition", "attachment;filename=" + table.getDbName() + ".xls");
			response.setDateHeader("Expires", 0);
			response.setContentType("application/vnd.ms-excel");

			ExcelUtil.write(excel, out);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static void writeTab(ExcelBO excel, TableBO tableFull) {
		String sheetName = null;
		if (tableFull.getDbName() != null && tableFull.getDbName().equals(tableFull.getName())) {
			sheetName = tableFull.getName() + "-" + tableFull.getDbName();
		} else {
			sheetName = tableFull.getName();
		}
		SheetBO sheet = excel.createSheet(sheetName);
		{// header
			RowBO row = sheet.createRow();
			row.addColumn(new ColumnBO("中文名"));
			row.addColumn(new ColumnBO("字段名"));
			row.addColumn(new ColumnBO("类型"));
			row.addColumn(new ColumnBO("长度"));
			row.addColumn(new ColumnBO("is null"));
			row.addColumn(new ColumnBO("备注"));
		}
		for (FieldBO field : tableFull.getFields()) {
			RowBO row = sheet.createRow();
			row.addColumn(new ColumnBO(field.getName()));
			row.addColumn(new ColumnBO(field.getDbName()));
			row.addColumn(new ColumnBO(field.getDbType()));
			row.addColumn(new ColumnBO(field.getDbLength()));
			if (field.getAllowNull() != null && field.getAllowNull() == 1) {
				row.addColumn(new ColumnBO("Y"));
			} else {
				row.addColumn(new ColumnBO("N"));
			}
			row.addColumn(new ColumnBO(field.getNote()));
		}
		sheet.createRow();
		sheet.createRow();
		RowBO row = sheet.createRow();
		String indexDesc = "";
		if (tableFull.getIndexDesc() != null && tableFull.getIndexDesc().startsWith("<pre>")) {
			indexDesc = tableFull.getIndexDesc();
			indexDesc = indexDesc.substring(5, indexDesc.length() - 6);
		} else {
			indexDesc = tableFull.getIndexDesc();
		}
		row.addColumn(new ColumnBO(indexDesc));
	}

}
