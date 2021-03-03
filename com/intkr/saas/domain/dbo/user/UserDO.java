package com.intkr.saas.domain.dbo.user;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:30:45
 * @version 1.0
 */
public class UserDO extends BaseDO {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// super_admin:超级管理，可管理所有Saas的域名配置。 admin:管理员，可管理所属saas的域名配置。 normal:普通用户。
	private String saasRole;

	private String nickName;

	private String avatar;

	private String account;

	private String password;

	private String securePassword;

	private String email;

	private String phone;

	private Long qq;

	private Long money;

	private String status;

	private Integer hasSecureQuestion;

	private Integer isIdentity;

	private Long identityId;

	private Long weixinId;

	private String sex;

	private String country;

	private String province;

	private String city;

	private String feature;

	// 所掌管的店铺ID
	private Long shopId;

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSecurePassword() {
		return securePassword;
	}

	public void setSecurePassword(String securePassword) {
		this.securePassword = securePassword;
	}

	public Integer getHasSecureQuestion() {
		return hasSecureQuestion;
	}

	public void setHasSecureQuestion(Integer hasSecureQuestion) {
		this.hasSecureQuestion = hasSecureQuestion;
	}

	public Long getIdentityId() {
		return identityId;
	}

	public void setIdentityId(Long identityId) {
		this.identityId = identityId;
	}

	public Long getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(Long weixinId) {
		this.weixinId = weixinId;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Integer getIsIdentity() {
		return isIdentity;
	}

	public void setIsIdentity(Integer isIdentity) {
		this.isIdentity = isIdentity;
	}

	public String getSaasRole() {
		return saasRole;
	}

	public void setSaasRole(String saasRole) {
		this.saasRole = saasRole;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

}
