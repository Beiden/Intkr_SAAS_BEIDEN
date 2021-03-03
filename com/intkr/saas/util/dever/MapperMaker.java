package com.intkr.saas.util.dever;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.intkr.saas.util.FileUtil;
import com.intkr.saas.util.db.ColumnDO;
import com.intkr.saas.util.db.Context;
import com.intkr.saas.util.db.TableDO;

/**
 * 
 * @author Beiden
 * @date 2017-11-17
 * @version 1.0
 */
public class MapperMaker {

	public static File getFile() {
		TableDO table = Context.getTable();
		String mapperDirtory = Context.projectDir + "src/main/resources/mapper" + Context.getModuleDir() + "/";
		String mapperFile = mapperDirtory + table.getClazName() + "Mapper.xml";
		File file = new File(mapperFile);
		return file;
	}

	public static void maker() {
		TableDO table = Context.getTable();
		maker(table);
	}

	public static void maker(TableDO table) {
		String classPackage = Context.getClazPackage() + "domain.dbo" + Context.getModule();
		String className = classPackage + "." + table.getClazName() + "DO";

		String content = makeMapper(Context.getTable().getName(), className);
		FileUtil.write(getFile().getAbsolutePath(), content);
	}

	public static void delete() {
		File file = getFile();
		file.delete();
	}

	private static String makeMapper(String tableName, String className) {
		List<ColumnDO> columns = new ArrayList<ColumnDO>();
		TableDO table = Context.getTable();
		for (ColumnDO col : table.getColumns()) {
			String name = col.getName();
			if (name.equals("id")) {
				continue;
			}
			if (name.equals("gmt_created")) {
				continue;
			}
			if (name.equals("gmt_modified")) {
				continue;
			}
			if (name.equals("is_deleted")) {
				continue;
			}
			columns.add(col);
		}
		return make(tableName, className, columns, table);
	}

	private static String make(String tableName, String className, List<ColumnDO> columns, TableDO tableDO) {
		String resultMapObjColumns = "";
		String insertNames = "";
		String insertValues = "";
		String updateSets = "";
		String selectWheres = "";
		for (ColumnDO column : columns) {
			resultMapObjColumns += "\n\t\t<result property=\"" + column.getProName() + "\" column=\"" + column.getName() + "\"/> ";
			insertNames += " " + column.getName() + " ,";
			insertValues += " #{" + column.getProName() + "} ,";

			String tmpUpdateSets = "\n\t\t\t<if test=\"{columnValue} != null\">  " + //
					"\n\t\t\t\t{column} = #{{columnValue}} , " + //
					"\n\t\t\t</if> ";
			tmpUpdateSets = tmpUpdateSets.replace("{column}", column.getName());
			tmpUpdateSets = tmpUpdateSets.replace("{columnValue}", column.getProName());
			updateSets += tmpUpdateSets;

			String tmpSelectWheres = "\n\t\t\t<if test=\"{columnValue} != null and {columnValue} != ''\">  " + //
					"\n\t\t\t\tand {column} = #{{columnValue}} " + //
					"\n\t\t\t</if>  ";
			tmpSelectWheres = tmpSelectWheres.replace("{column}", column.getName());
			tmpSelectWheres = tmpSelectWheres.replace("{columnValue}", column.getProName());
			selectWheres += tmpSelectWheres;
		}

		VelocityContext param = new VelocityContext();
		param.put("tableDO", tableDO);
		param.put("className", className);
		param.put("resultMapObjColumns", resultMapObjColumns);
		param.put("insertNames", insertNames);
		param.put("insertValues", insertValues);
		param.put("updateSets", updateSets);
		param.put("selectWheres", selectWheres);
		String content = TemplateEngine.merge("template/MapperDemo.xml", param);
		return content;
	}

}
