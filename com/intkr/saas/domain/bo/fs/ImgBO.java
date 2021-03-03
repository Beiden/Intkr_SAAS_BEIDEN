package com.intkr.saas.domain.bo.fs;

import java.util.List;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.bo.user.UserBO;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:02:47
 * @version 1.0
 */
public class ImgBO extends BaseBO {

	private Long saasId;

	// 类目ID
	private Long categoryId;

	// 店铺ID
	private Long shopId;

	// 用户ID
	private Long userId;

	// 名称
	private String name;

	// 上传时的真正名称
	private String realname;

	//
	private String uri;

	// 备注
	private String note;

	// 权重
	private Double sort;

	// 拓展字段
	private String feature;

	private UserBO user;

	private List<MediaCategoryBO> categorys;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}

	public List<MediaCategoryBO> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<MediaCategoryBO> categorys) {
		this.categorys = categorys;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public boolean isSearchLucene() {
		return "lucene".equalsIgnoreCase(getQuery("searchData") + "");
	}

	public void searchLucene() {
		setQuery("searchData", "lucene");
	}

	public boolean isSearchDb() {
		return "db".equalsIgnoreCase(getQuery("searchData") + "");
	}

	public void searchDb() {
		setQuery("searchData", "db");
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

}
