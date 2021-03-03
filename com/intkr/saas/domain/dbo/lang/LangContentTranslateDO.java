package com.intkr.saas.domain.dbo.lang;

import com.intkr.saas.domain.BaseDO;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
public class LangContentTranslateDO extends BaseDO<LangContentTranslateDO> {

	private static final long serialVersionUID = 1L;

	// content_id
	private Long contentId;

	// lang_id
	private String langId;

	// 
	private String content;

	// 修改人
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
