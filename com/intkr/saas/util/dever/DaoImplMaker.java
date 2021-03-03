package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.Context;

public class DaoImplMaker {

	public static File getFile() {
		String fileName = Context.projectDir + "src/main/java/" + Context.getClazDir() + "dao" + Context.getModuleDir() + "/impl/" + Context.getTable().getClazName() + "DAOImpl.java";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("tableDO", Context.getTable());
		String content = TemplateEngine.merge("template/DAOImplTemplate", param);
		FileUtil.write(getFile().getAbsolutePath(), content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

}
