package com.intkr.saas.util.dever;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.TemplateUtil;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

public class MgrVMMaker2 {

	public static void maker() {
		TableDO table = Context.getTable();
		Map<String, String> param = new HashMap<String, String>();
		param.put("moduleDir", Context.getModuleDir().substring(1) + "/");
		param.put("subDirectory", Context.getModuleDir());
		param.put("subPackage", Context.getModule());
		param.put("searchForm", makeSearchForm(table));
		param.put("tableHeader", makeTableHeader(table));
		param.put("tableDom", makeTable(table));
		param.put("tableName", Context.getTable().getName());
		param.put("classNameProperty", Context.getTable().getProName());
		param.put("className", Context.getTable().getClazName());
		param.put("nowTimeString", DateUtil.formatDateTime(new Date()));
		param.put("package", Context.getClazPackage() + "module.action" + Context.getModule());
		param.put("tableDesc", table.getDesc());
		param.put("baseClassPackage", Context.getClazPackage());
		String content = TemplateUtil.merge(TemplateEngine.getContent("template/MgrVMTemplate2.vm"), param);
		String fileDirectory = "src/main/webapp/IK/templates/screen";
		String fileName = Context.projectDir + fileDirectory + Context.getModuleDir() + "/" + Context.getTable().getProName() + "Mgr.vm";
		FileUtil.write(fileName, content);
	}

	public static void delete() {
		String fileDirectory = "src/main/webapp/IK/templates/screen";
		String fileName = Context.projectDir + fileDirectory + Context.getModuleDir() + "/" + Context.getTable().getProName() + "Mgr.vm";
		File file = new File(fileName);
		file.delete();
	}

	private static String makeSearchForm(TableDO table) {
		String string = "";
		int i = 0;
		for (ColumnDO column : table.getColumns()) {
			String name = column.getName();
			if ("saas_client_id".equals(name) || //
					"gmt_created".equals(name) || //
					"gmt_modified".equals(name) || //
					"feature".equals(name) || //
					"is_deleted".equals(name)) {
				continue;
			}
			String template = "\n\t\t\t<td width=\"80\" align=\"right\">" + //
					"\n\t\t\t\t{{desc}}" + //
					"\n\t\t\t</td>" + //
					"\n\t\t\t<td>" + //
					"\n\t\t\t\t<input class=\"form-control\" name=\"{{name}}\" value=\"$!{query.{{name}}}\">" + //
					"\n\t\t\t</td>";
			i++;
			if (i > 0 && i % 3 == 0) {
				template += "\n\t\t</tr>\n\t\t<tr>";
			}
			template = template.replace("{{desc}}", column.getProName());
			template = template.replace("{{name}}", column.getProName());
			template = template.replace("{{dtoName}}", column.getProName());
			string += template;
		}
		return string;
	}

	private static String makeTableHeader(TableDO table) {
		String string = "\n\t\t\t<th order-field=\"Column\">propertes</th>";
		return string;
	}

	private static String makeTable(TableDO table) {
		String string = "\n\t\t\t<td>";
		for (ColumnDO column : table.getColumns()) {
			String name = column.getName();
			if ("gmt_created".equals(name) || //
					"gmt_modified".equals(name) || //
					"saas_client_id".equals(name) || //
					"feature".equals(name) || //
					"is_deleted".equals(name)) {
				continue;
			}
			String template = "\n\t\t\t\t{{name}} : $!dto.{{name}}<br/>";
			if ("ctime".equals(column.getProName())  || "mtime".equals(column.getProName())) {
				template = "\n\t\t\t\t{{name}} : $!DateUtil.formatDateTime($!dto.{{name}})<br/>";
			}
			template = template.replace("{{desc}}", column.getProName());
			template = template.replace("{{name}}", column.getProName());
			template = template.replace("{{dtoName}}", column.getProName());
			string += template;
		}
		string += "\n\t\t\t</td>";
		return string;
	}

}
