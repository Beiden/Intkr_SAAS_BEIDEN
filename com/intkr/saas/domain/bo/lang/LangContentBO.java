package com.intkr.saas.domain.bo.lang;

import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 内容
 * 
 * @table lang_content
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:16
 * @version 1.0
 */
public class LangContentBO extends BaseBO<LangContentBO> {

	private static final long serialVersionUID = 1L;

	private Long saasId;

	// 语言缩写：cn
	private String langId;

	// 类型
	private String category;

	// 内容
	private String content;

	// 最后修改人
	private String gmtModifier;

	// 权重
	private Double sort;

	// 备注
	private String note;

	// 拓展字段
	private String feature;

	private List<LangContentTranslateBO> translates;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGmtModifier() {
		return gmtModifier;
	}

	public void setGmtModifier(String gmtModifier) {
		this.gmtModifier = gmtModifier;
	}

	public List<LangContentTranslateBO> getTranslates() {
		return translates;
	}

	public void setTranslates(List<LangContentTranslateBO> translates) {
		this.translates = translates;
	}

	public LangContentTranslateBO getLang(String langId) {
		if (translates == null) {
			return null;
		}
		for (LangContentTranslateBO trans : translates) {
			if (trans.getLangId().equals(langId)) {
				return trans;
			}
		}
		return null;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
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

}
