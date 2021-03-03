package com.intkr.saas.manager.lang.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.lang.LanguageDAO;
import com.intkr.saas.domain.bo.lang.LanguageBO;
import com.intkr.saas.domain.dbo.lang.LanguageDO;
import com.intkr.saas.manager.lang.LanguageManager;

/**
 * 语言
 * 
 * @table language
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:02
 * @version 1.0
 */
@Repository("LanguageManager")
public class LanguageManagerImpl extends BaseManagerImpl<LanguageBO, LanguageDO> implements LanguageManager {

	@Resource
	private LanguageDAO languageDAO;

	public BaseDAO<LanguageDO> getBaseDAO() {
		return languageDAO;
	}

}
