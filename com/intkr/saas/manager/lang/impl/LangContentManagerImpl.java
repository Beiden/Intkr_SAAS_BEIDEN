package com.intkr.saas.manager.lang.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.lang.LangContentDAO;
import com.intkr.saas.domain.bo.lang.LangContentBO;
import com.intkr.saas.domain.dbo.lang.LangContentDO;
import com.intkr.saas.manager.lang.LangContentManager;

/**
 * 内容
 * 
 * @table lang_content
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:16
 * @version 1.0
 */
@Repository("LangContentManager")
public class LangContentManagerImpl extends BaseManagerImpl<LangContentBO, LangContentDO> implements LangContentManager {

	@Resource
	private LangContentDAO langContentDAO;

	public BaseDAO<LangContentDO> getBaseDAO() {
		return langContentDAO;
	}

	public LangContentBO get(String langId, String content) {
		return null;
	}

}
