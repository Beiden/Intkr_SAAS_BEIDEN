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

public class JsMaker {

	public static File getFile() {
		String fileDirectory = "src/main/webapp/IK/templates/screen/admin";
		String fileName = Context.projectDir + fileDirectory + Context.getModuleDir() + "/js/" + Context.getTable().getClazName() + "JS.vm";
		File file = new File(fileName);
		return file;
	}

	public static File getFile2() {
		String fileDirectory = "src/main/webapp/asset/adMgr";
		String fileName = Context.projectDir + fileDirectory + Context.getModuleDir() + "/" + Context.getTable().getClazName() + ".js";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		TableDO table = Context.getTable();
		String setterString = makeMapper(Context.getTable().getName());
		Map<String, String> param = new HashMap<String, String>();
		param.put("subDirectory", Context.getModuleDir());
		param.put("subPackage", Context.getModule());
		param.put("setterString", setterString);
		param.put("tableName", Context.getTable().getName());
		param.put("classNameProperty", Context.getTable().getProName());
		param.put("className", Context.getTable().getClazName());
		param.put("nowTimeString", DateUtil.formatDateTime(new Date()));
		param.put("package", Context.getClazPackage() + "module.action" + Context.getModule());
		param.put("tableDesc", table.getDesc());
		param.put("baseClassPackage", Context.getClazPackage());
		String content = TemplateUtil.merge(TemplateEngine.getContent("template/JsTemplate.vm"), param);
		// FileUtil.write(getFile().getAbsolutePath(), content);
		FileUtil.write(getFile2().getAbsolutePath(), content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

	private static String makeMapper(String tableName) {
		TableDO table = Context.getTable();
		return make(tableName, table);
	}

	private static String make(String tableName, TableDO table) {
		String string = "";
		for (ColumnDO column : table.getColumns()) {
			String name = column.getName();
			if ("id".equals(name) || "gmt_created".equals(name) || "gmt_modified".equals(name) || "is_deleted".equals(name)) {
				continue;
			}
			String tmpPropertys = "";
			if ("text".equalsIgnoreCase(column.getType()) || "varchar".equalsIgnoreCase(column.getType())) {
				tmpPropertys = "\n\t\t" + table.getProName() + "BO.set" + column.getClazName() + "(RequestUtil.getParam(request, \"" + column.getProName() + "\"));";
			} else if ("datetime".equalsIgnoreCase(column.getType())) {
				tmpPropertys = "\n\t\tif (RequestUtil.existParam(request, \"{propertyName}\")) {" + //
						"\n\t\t\t{className}BO.set{propertyUpperName}(DateUtil.parse(RequestUtil.getParam(request, \"{propertyName}\")));" + //
						"\n\t\t}";
				tmpPropertys = tmpPropertys.replace("{propertyUpperName}", column.getClazName());
				tmpPropertys = tmpPropertys.replace("{propertyName}", column.getProName());
				tmpPropertys = tmpPropertys.replace("{className}", table.getProName());
			} else if ("int".equalsIgnoreCase(column.getType())) {
				tmpPropertys = "\n\t\t" + table.getProName() + "BO.set" + column.getClazName() + "(RequestUtil.getParam(request, \"" + column.getProName() + "\" , Integer.class));";
			} else if ("bigint".equalsIgnoreCase(column.getType())) {
				tmpPropertys = "\n\t\t" + table.getProName() + "BO.set" + column.getClazName() + "(RequestUtil.getParam(request, \"" + column.getProName() + "\" , Long.class));";
			} else if ("double".equalsIgnoreCase(column.getType())) {
				tmpPropertys = "\n\t\t" + table.getProName() + "BO.set" + column.getClazName() + "(RequestUtil.getParam(request, \"" + column.getProName() + "\" , Double.class));";
			}
			string += tmpPropertys;
		}
		return string;
	}

}
