package com.intkr.saas.domain.type.sys;

/**
 * 
 * @author Beiden
 * @date 2011-10-17 下午4:17:26
 * @version 1.0
 */
public enum ToolType {

	Rss("rss", "RSS"), //
	Category("category", "分类目录"), //
	Function("function", "功能"), //
	Search("search", "搜索"), //
	Text("text", "文本"), //
	Archive("archive", "文章归档"), //
	Calendar("calendar", "日历"), //
	Tag("tag", "标签云"), //
	Menu("menu", "自定义菜单"), //
	RecentArticle("recentArticle", "最近文章"), //
	HotArticle("hotArticle", "热门文章"), //
	RecentComment("recentComment", "最近评论"), //
	Page("page", "页面"), //
	Error("error", "异常"); //

	private ToolType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	private String code;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static ToolType getByCode(String code) {
		ToolType[] values = values();
		for (ToolType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
