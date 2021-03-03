package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

public class ActionMaker {

	public static File getFile() {
		TableDO tableDO = Context.getTable();
		String fileName = Context.projectDir//
				+ "src/main/java/" //
				+ Context.getClazDir() //
				+ "module/action" //
				+ Context.getModuleDir()//
				+ "/" + tableDO.getClazName() //
				+ "Action.java";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		TableDO tableDO = Context.getTable();
		String setterString = make(tableDO);
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("tableDO", tableDO);
		param.put("setterString", setterString);
		String content = TemplateEngine.merge("template/ActionTemplate", param);
		FileUtil.write(getFile().getAbsolutePath(), content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

	private static String make(TableDO table) {
		String string = "";
		for (ColumnDO column : table.getColumns()) {
			String name = column.getName();
			if ("id".equals(name) || "gmt_created".equals(name) || "gmt_modified".equals(name) || "ctime".equals(name) || "mtime".equals(name) || "is_deleted".equals(name)) {
				continue;
			}
			String tmpPropertys = "\n\t\t" + table.getProName() + "BO.set" + column.getClazName() + "(RequestUtil.getParam(request, \"" + column.getProName() + "\" , " + column.getClazType() + ".class));";
			string += tmpPropertys;
		}
		return string;
	}

}
