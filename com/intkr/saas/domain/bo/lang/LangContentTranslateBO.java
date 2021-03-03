package com.intkr.saas.domain.bo.lang;

import com.intkr.saas.domain.BaseBO;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
public class LangContentTranslateBO extends BaseBO<LangContentTranslateBO> {

	private static final long serialVersionUID = 1L;

	// 内容ID
	private Long contentId;

	// 语言缩写
	private String langId;

	// 内容
	private String content;

	// 最后修改人
	private String gmtModifier;

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
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

}
