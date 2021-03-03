package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.Context;

public class DOMaker {

	public static File getFile() {
		String fileName = Context.projectDir //
				+ "src/main/java/" //
				+ Context.getClazPackage().replace(".", "/") //
				+ "domain/dbo" + Context.getModuleDir() + "/" //
				+ Context.getTable().getClazName() + "DO.java";
		return new File(fileName);
	}

	public static void maker() {
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("tableDO", Context.getTable());
		String content = TemplateEngine.merge("template/DOTemplate", param);
		String fileName = getFile().getAbsolutePath();
		FileUtil.write(fileName, content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

}
