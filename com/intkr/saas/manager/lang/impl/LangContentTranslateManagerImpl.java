package com.intkr.saas.manager.lang.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.lang.LangContentTranslateDAO;
import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.domain.bo.lang.LangContentTranslateBO;
import com.intkr.saas.domain.dbo.lang.LangContentTranslateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.lang.LangContentTranslateManager;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
@Repository("LangContentTranslateManager")
public class LangContentTranslateManagerImpl extends BaseManagerImpl<LangContentTranslateBO, LangContentTranslateDO> implements LangContentTranslateManager {

	@Resource
	private LangContentTranslateDAO langContentTranslateDAO;

	public BaseDAO<LangContentTranslateDO> getBaseDAO() {
		return langContentTranslateDAO;
	}

	public LangContentBO fill(LangContentBO langContent) {
		if (langContent == null) {
			return langContent;
		}
		LangContentTranslateBO query = new LangContentTranslateBO();
		query.set_pageSize(1000);
		query.setContentId(langContent.getId());
		langContent.setTranslates(select(query));
		return langContent;
	}

	public LangContentBO fill(LangContentBO langContent, String langId) {
		if (langContent == null || langId == null) {
			return langContent;
		}
		LangContentTranslateBO query = new LangContentTranslateBO();
		query.set_pageSize(1000);
		query.setContentId(langContent.getId());
		query.setLangId(langId);
		langContent.setProperty("translate", selectOne(query));
		return langContent;
	}

}
