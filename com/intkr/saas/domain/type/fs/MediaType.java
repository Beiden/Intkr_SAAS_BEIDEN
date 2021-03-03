package com.intkr.saas.domain.type.fs;

/**
 * 
 * @author Beiden
 * @date 2011-10-16 上午10:38:45
 * @version 1.0
 */
public enum MediaType {

	Img("img", "图片"), // 图片
	File("file", "文件"), //
	Audio("audio", "音频"), //
	Attach("attach", "附件"), //
	Vedio("vedio", "视频"),// 视频
	Error("error", "异常");// 

	private MediaType(String code, String name) {
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
	
	public static MediaType getByCode(String code) {
		MediaType[] values = values();
		for (MediaType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
