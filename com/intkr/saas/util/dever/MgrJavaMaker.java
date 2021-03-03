package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

public class MgrJavaMaker {

	public static File getFile() {
		String fileDirectory = "src/main/java/" + Context.getClazPackage().replace(".", "/") + "module/screen/admin";
		String fileName = Context.projectDir + fileDirectory + Context.getModuleDir() + "/" + Context.getTable().getClazName() + "Mgr.java";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("setterString", make());
		param.put("tableDO", Context.getTable());
		String content = TemplateEngine.merge("template/MgrJavaTemplate.vm", param);
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
