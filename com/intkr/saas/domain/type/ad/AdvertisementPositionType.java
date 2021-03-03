package com.intkr.saas.domain.type.ad;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午11:04:00
 * @version 1.0
 */
public enum AdvertisementPositionType {

	Picture("picture", "单图"), //
	Pictures("pictures", "多图"), //
	Text("text", "广告语"), //
	Texts("texts", "多行广告语"), //
	Item("item", "商品"), //
	Items("items", "多商品"), //
	Article("article", "文章"), //
	Articles("articles", "多文章"), //
	Page("page", "页面"), //
	Pages("pages", "多页面"), //
	Error("error", "异常"); //

	private AdvertisementPositionType(String code, String name) {
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
	
	public static AdvertisementPositionType getByCode(String code) {
		AdvertisementPositionType[] values = values();
		for (AdvertisementPositionType value : values) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		return Error;
	}

}
