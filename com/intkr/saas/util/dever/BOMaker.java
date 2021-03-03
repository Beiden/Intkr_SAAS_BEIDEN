package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

public class BOMaker {

	public static File getFile() {
		String fileName = Context.projectDir + "src/main/java/" + Context.getClazDir() + "domain/bo" + Context.getModuleDir() + "/" + Context.getTable().getClazName() + "BO.java";
		return new File(fileName);
	}

	public static void maker() {
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("property", make());
		param.put("tableDO", Context.getTable());
		String content = TemplateEngine.merge("template/BOTemplate", param);
		String fileName = getFile().getAbsolutePath();
		FileUtil.write(fileName, content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

	private static String make() {
		TableDO table = Context.getTable();
		String string = "";
		String getSeter = "";
		for (ColumnDO column : table.getColumns()) {
			String name = column.getName();
			if ("gmt_created".equals(name) //
					|| "gmtCreated".equals(name) //
					|| "gmt_modified".equals(name) //
					|| "gmtModified".equals(name) //
					|| "ctime".equals(name) //
					|| "mtime".equals(name) //
					|| "is_deleted".equals(name) //
					|| "isDeleted".equals(name)) {
				continue;
			}
			String tmpPropertys = "\n\t// " + column.getDesc() + //
					"\n\tprivate {type} {columnValue};\n";
			String type = column.getClazType();
			tmpPropertys = tmpPropertys.replace("{type}", type);
			tmpPropertys = tmpPropertys.replace("{columnValue}", column.getProName());
			string += tmpPropertys;
			String tmpGetSeter = "\n\tpublic {type} get{upperName}() {" + //
					"\n\t\treturn {name};" + //
					"\n\t}" + //
					"\n\n\tpublic void set{upperName}({type} {name}) {" + //
					"\n\t\tthis.{name} = {name};" + //
					"\n\t}\n";
			tmpGetSeter = tmpGetSeter.replace("{type}", type);
			tmpGetSeter = tmpGetSeter.replace("{name}", column.getProName());
			tmpGetSeter = tmpGetSeter.replace("{upperName}", column.getClazName());
			getSeter += tmpGetSeter;
		}
		return string + getSeter;
	}

}
