package com.intkr.saas.domain.bo.sns;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-24 上午11:00:01
 * @version 1.0
 */
public class MsgTemplateBO extends BaseBO {

	private Long saasId;

	private String code;

	private String name;

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

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
