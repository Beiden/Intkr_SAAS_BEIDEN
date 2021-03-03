package com.intkr.saas.domain.bo.sns;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-3-22 上午10:44:52
 * @version 1.0
 */
public class MsgSignatureBO extends BaseBO {

	private Long saasId;

	private String content;

	private Integer isDefault;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
