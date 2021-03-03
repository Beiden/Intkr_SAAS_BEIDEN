package com.intkr.saas.domain.bo.sns;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午10:43:12
 * @version 1.0
 */
public class EmailTemplateBO extends BaseBO {

	private String code;

	private String name;

	private String title;

	private String content;

	private String feature;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
