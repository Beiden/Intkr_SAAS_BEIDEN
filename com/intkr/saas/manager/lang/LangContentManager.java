package com.intkr.saas.manager.lang;

import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.domain.dbo.lang.LangContentDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 内容
 * 
 * @table lang_content
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:16
 * @version 1.0
 */
public interface LangContentManager extends BaseManager<LangContentBO, LangContentDO> {

	public LangContentBO get(String langId, String content);

}
