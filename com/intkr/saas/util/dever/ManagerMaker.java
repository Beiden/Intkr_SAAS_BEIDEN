package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.Context;

public class ManagerMaker {

	public static File getFile() {
		String fileName = Context.projectDir + "src/main/java/" + Context.getClazDir() + "manager" + Context.getModuleDir() + "/" + Context.getTable().getClazName() + "Manager.java";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("tableDO", Context.getTable());
		String content = TemplateEngine.merge("template/ManagerTemplate", param);
		FileUtil.write(getFile().getAbsolutePath(), content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

}
