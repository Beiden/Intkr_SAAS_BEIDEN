package com.intkr.saas.domain.bo.user;

import java.util.HashMap;
import java.util.Map;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.util.JsonUtil;
import com.intkr.saas.util.MD5Util;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-24 下午03:31:37
 * @version 1.0
 */
public class UserBO extends BaseBO {

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

	// 是否有密保问题
	private Integer hasSecureQuestion;

	private Integer isIdentity;

	// 认证流程的ID
	private Long identityId;

	private Long weixinId;

	private String sex;

	private String country;

	private String province;

	private String city;

	private String feature;

	// 所掌管的店铺ID
	private Long shopId;

	private ShopBO shop;

	private AuthorityBO authority;

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

	public void encryptPassword() {
		String randomString = MD5Util.getRandomAdd();
		String enPassword = MD5Util.encrypt(password, randomString);
		this.password = enPassword + "|" + randomString;
	}

	public void encryptSecurePassword() {
		String randomString = MD5Util.getRandomAdd();
		String enPassword = MD5Util.encrypt(securePassword, randomString);
		this.securePassword = enPassword + "|" + randomString;
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

	/**
	 * 密码是否一致
	 * 
	 * @param password
	 * @return
	 */
	public boolean isPasswordEquals(String password) {
		String[] strings = this.password.split("\\|");
		return MD5Util.verify(password, strings[1], strings[0]);
	}

	/**
	 * 密码是否一致
	 * 
	 * @param password
	 * @return
	 */
	public boolean isSecurePasswordEquals(String password) {
		if (securePassword == null) {
			return false;
		}
		String[] strings = this.securePassword.split("\\|");
		return MD5Util.verify(password, strings[1], strings[0]);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AuthorityBO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityBO authority) {
		this.authority = authority;
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

	public Object getFeature(String key) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public void setFeature(String key, Object value) {
		Map<String, Object> map = JsonUtil.toObject(feature, Map.class);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put(key, value);
		this.feature = JsonUtil.toJson(map);
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

	public static void main(String[] args) {
		UserBO account = new UserBO();
		account.setPassword("12345678");
		account.encryptPassword();
		System.out.println(account.getPassword());

		System.out.println(account.isPasswordEquals("12345678"));
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public ShopBO getShop() {
		return shop;
	}

	public void setShop(ShopBO shop) {
		this.shop = shop;
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
