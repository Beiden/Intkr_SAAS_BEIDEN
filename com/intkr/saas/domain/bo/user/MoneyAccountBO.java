package com.intkr.saas.domain.bo.user;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2017-10-02 17:59:27
 * @version 1.0
 */
public class MoneyAccountBO extends BaseBO {

	private Long saasId;

	// 类型：店铺帐号，个人帐号
	private String accountType;

	// 关联帐号ID
	private Long userId;

	// 编码
	private String code;

	// 名称
	private String name;

	//
	private String feature;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
