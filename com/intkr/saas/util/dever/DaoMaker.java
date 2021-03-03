package com.intkr.saas.util.dever;

import java.io.File;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

public class DaoMaker {

	public static File getFile() {
		String fileName = Context.projectDir + "src/main/java/" + Context.getClazDir() + "dao" + Context.getModuleDir() + "/" + Context.getTable().getClazName() + "DAO.java";
		File file = new File(fileName);
		return file;
	}

	public static void maker() {
		TableDO tableDO = Context.getTable();
		VelocityContext param = new VelocityContext();
		param.put("Context", Context.instance);
		param.put("tableDO", tableDO);
		String content = TemplateEngine.merge("template/DAOTemplate", param);
		FileUtil.write(getFile().getAbsolutePath(), content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

}
