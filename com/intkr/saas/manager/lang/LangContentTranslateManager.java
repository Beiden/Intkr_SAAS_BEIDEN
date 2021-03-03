package com.intkr.saas.manager.lang;

import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.domain.bo.lang.LangContentTranslateBO;
import com.intkr.saas.domain.dbo.lang.LangContentTranslateDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
public interface LangContentTranslateManager extends BaseManager<LangContentTranslateBO, LangContentTranslateDO> {

	public LangContentBO fill(LangContentBO langContent);

	public LangContentBO fill(LangContentBO langContent, String langId);

}
