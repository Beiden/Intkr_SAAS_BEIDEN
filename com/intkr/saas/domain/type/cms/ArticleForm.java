package com.intkr.saas.domain.type.cms;

/**
 * 
 * @author Beiden
 * @date 2011-10-18 下午2:30:44
 * @version 1.0
 */
public enum ArticleForm {

	Normal("normal", "标准"), //
	Log("log", "日志"), //
	Album("album", "相册"), //
	Link("link", "链接"), //
	Image("image", "图像"), //
	Quote("quote", "引语"), //
	Status("status", "状态"), //
	Video("video", "视频"), //
	Voice("voice", "音频"), //
	Chat("chat", "聊天"), //
	Error("error", "异常"); //

	private ArticleForm(String code, String name) {
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
	
	public static ArticleForm getByCode(String code) {
		ArticleForm[] values = values();
		for (ArticleForm value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
