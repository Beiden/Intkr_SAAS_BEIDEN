package com.intkr.saas.domain.dbo.user;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-3-16 下午7:04:22
 * @version 1.0
 */
public class MoneyOperDO extends BaseDO {

	private Long id;

	private Long userId;

	private String account;

	private String status;

	private String type;

	private Long relatedUserId;

	private String relatedAccount;

	private Long money;

	private String feature;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRelatedUserId() {
		return relatedUserId;
	}

	public void setRelatedUserId(Long relatedUserId) {
		this.relatedUserId = relatedUserId;
	}

	public String getRelatedAccount() {
		return relatedAccount;
	}

	public void setRelatedAccount(String relatedAccount) {
		this.relatedAccount = relatedAccount;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
