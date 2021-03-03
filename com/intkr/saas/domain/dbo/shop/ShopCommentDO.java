package com.intkr.saas.domain.dbo.shop;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午3:28:30
 * @version 1.0
 */
public class ShopCommentDO extends BaseDO {

	private Long saasId;

	// 类型
	private String type;

	// 用户ID
	private Long userId;

	// 头像
	private String avatar;

	// 用户名
	private String userName;

	// 邮箱
	private String email;

	// 网站名
	private String website;

	// 相关对象ID
	private Long relatedId;

	// 状态
	private String status;

	// 内容
	private String content;

	// 父Id
	private Long pid;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
