package com.intkr.saas.util.dever;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.TemplateUtil;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

public class AddUpdateVMMaker {

	public static File getFile() {
		String fileDirectory = "src/main/webapp/IK/templates/screen/admin";
		String fileName = Context.projectDir + fileDirectory + Context.getModuleDir() + "/dialog/" + Context.getTable().getProName() + "AddUpdate.vm";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		TableDO table = Context.getTable();
		String setterString = make(table);
		Map<String, String> param = new HashMap<String, String>();
		param.put("moduleDir", Context.getModuleDir().substring(1) + "/");
		param.put("setterString", setterString);
		param.put("tableClassName", Context.getTable().getClazName());
		param.put("tableProName", Context.getTable().getProName());
		String content = TemplateUtil.merge(TemplateEngine.getContent("template/AddUpdateVMTemplate.vm"), param);
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
			String desc = column.getDesc();
			if ("id".equals(name) || //
					"saas_client_id".equals(name) || //
					"gmt_created".equals(name) || //
					"gmt_modified".equals(name) || //
					"shop_id".equals(name) || //
					"feature".equals(name) || //
					"is_deleted".equals(name) || //
					desc == null || //
					"".equals(desc)) {
				continue;
			}
			String template = "\n\t<div class=\"row line-height-xlg\">" + //
					"\n\t\t<div class=\"col-xs-3 text-right\">" + //
					"\n\t\t\t{{columnDesc}}" + //
					"\n\t\t</div>" + //
					"\n\t\t<div class=\"col-xs-9\">" + //
					"\n\t\t\t<input class=\"form-control must-bg\" name=\"{{columnProName}}\" value=\"$!{{{tableProName}}.{{columnProName}}}\">" + //
					"\n\t\t</div>" + //
					"\n\t</div>";
			template = template.replace("{{columnDesc}}", column.getDesc());
			template = template.replace("{{columnProName}}", column.getProName());
			template = template.replace("{{tableProName}}", table.getProName());
			string += template;
		}
		return string;
	}

}
