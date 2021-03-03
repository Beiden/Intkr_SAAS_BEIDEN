package com.intkr.saas.dao.lang.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.lang.LangContentTranslateDAO;
import com.intkr.saas.domain.dbo.lang.LangContentTranslateDO;

/**
 * 内容翻译
 * 
 * @table lang_content_translate
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:30
 * @version 1.0
 */
@Repository("LangContentTranslateDAO")
public class LangContentTranslateDAOImpl extends BaseDAOImpl<LangContentTranslateDO> implements LangContentTranslateDAO {

	public String getNameSpace() {
		return "langContentTranslate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
