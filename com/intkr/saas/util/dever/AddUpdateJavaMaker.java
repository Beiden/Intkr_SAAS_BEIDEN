package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

public class AddUpdateJavaMaker {

	public static File getFile() {
		TableDO tableDO = Context.getTable();
		String fileDirectory = "src/main/java/" + Context.getClazDir() + "module/screen/admin";
		String fileName = Context.projectDir + fileDirectory + Context.getModuleDir() + "/dialog/" + tableDO.getClazName() + "AddUpdate.java";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		String setterString = make();
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("setterString", setterString);
		param.put("tableDO", Context.getTable());
		String content = TemplateEngine.merge("template/AddUpdateJavaTemplate.vm", param);
		FileUtil.write(getFile().getAbsolutePath(), content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

	private static String make() {
		TableDO table = Context.getTable();
		String string = "";
		for (ColumnDO column : table.getColumns()) {
			String name = column.getName();
			if ("id".equals(name) || "gmt_created".equals(name) || "gmt_modified".equals(name) || "is_deleted".equals(name)) {
				continue;
			}
			String template = "<div class=\"row line-height-xlg\">" + //
					"<div class=\"col-xs-3 text-right\">" + //
					"{{desc}}" + //
					"</div>" + //
					"<div class=\"col-xs-9\">" + //
					"<input class=\"form-control must-bg\" name=\"{{name}}\" value=\"$!{{{dtoName}}.name}\">" + //
					"</div>" + //
					"</div>";
			template = template.replace("{{desc}}", column.getDesc());
			template = template.replace("{{name}}", column.getProName());
			template = template.replace("{{dtoName}}", column.getProName());
			string += template;
		}
		return string;
	}

}
