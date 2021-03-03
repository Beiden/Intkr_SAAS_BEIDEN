package com.intkr.saas.util.db;

import com.intkr.saas.util.StringUtil;

/**
 * 
 * @author Beiden
 * @date 2017-2-20 下午2:30:55
 * @version 1.0
 */
public class ColumnDO {

	// 数据库字段名|ES
	private String name;
	private String proName;
	private String clazName;

	private Integer columnSize;// 列大小
	private Integer nullAble;// 是否允许为null
	private String columnDef;// 默认值

	// 数据库字段类型|ES
	private String type;
	private String clazType;

	// 数据库字段描述|ES
	private String desc = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		if (Context.getColPre() != null && !"".equals(Context.getColPre()) && this.name.startsWith(Context.getColPre())) {
			String nameTmp = name.substring(Context.getColPre().length());
			this.proName = StringUtil.hump(nameTmp);
		} else {
			this.proName = StringUtil.hump(this.name);
		}
		this.clazName = StringUtil.upperCaseFirstCharater(this.proName);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if ("datetime".equalsIgnoreCase(type)//
				|| "timestamp".equalsIgnoreCase(type)//
				|| "date".equalsIgnoreCase(type)//
		) {
			this.clazType = "Date";// 日期
		} else if ("int".equalsIgnoreCase(type) //
				|| "integer".equalsIgnoreCase(type)//
				|| "tinyint".equalsIgnoreCase(type)//
				|| "smallint".equalsIgnoreCase(type)//
				|| "mediumint".equalsIgnoreCase(type)//
				|| "int unsigned".equalsIgnoreCase(type)//
				|| "SMALLINT UNSIGNED".equalsIgnoreCase(type)//
				|| "TINYINT UNSIGNED".equalsIgnoreCase(type)//
		) {
			this.clazType = "Integer";// 整形
		} else if ("bigint".equalsIgnoreCase(type) //
				|| "long".equalsIgnoreCase(type)//
				|| "bigint unsigned".equalsIgnoreCase(type)//
		) {
			this.clazType = "Long"; // 长整形
		} else if ("double".equalsIgnoreCase(type)//
		) {
			this.clazType = "Double";// 长浮点型
		} else if ("float".equalsIgnoreCase(type)//
				|| "DECIMAL".equalsIgnoreCase(type)//
				|| "DECIMAL UNSIGNED".equalsIgnoreCase(type)//
		) {
			this.clazType = "Float";// 浮点型
		} else if ("varchar".equalsIgnoreCase(type) //
				|| "tinytext".equalsIgnoreCase(type)//
				|| "text".equalsIgnoreCase(type)//
				|| "tinyblob".equalsIgnoreCase(type)//
				|| "bit".equalsIgnoreCase(type)//
				|| "VARBINARY".equalsIgnoreCase(type)//
				|| "CHAR".equalsIgnoreCase(type)//
				|| "BLOB".equalsIgnoreCase(type)//
				|| "MEDIUMBLOB".equalsIgnoreCase(type)//
				|| type.toLowerCase().endsWith("text")//
		) {
			this.clazType = "String";
		} else {
			this.clazType = "String";
			System.out.println("识别类型问题：" + type);
		}
		if ("Integer".equals(this.clazType) && "id".equalsIgnoreCase(this.name)) {
			this.clazType = "Long";
		}
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getClazName() {
		return clazName;
	}

	public void setClazName(String clazName) {
		this.clazName = clazName;
	}

	public String getClazType() {
		return clazType;
	}

	public void setClazType(String clazType) {
		this.clazType = clazType;
	}

	public Integer getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(Integer columnSize) {
		this.columnSize = columnSize;
	}

	public Integer getNullAble() {
		return nullAble;
	}

	public void setNullAble(Integer nullAble) {
		this.nullAble = nullAble;
	}

	public String getColumnDef() {
		return columnDef;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

}
