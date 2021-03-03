package com.intkr.saas.dao.lang.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.lang.LanguageDAO;
import com.intkr.saas.domain.dbo.lang.LanguageDO;

/**
 * 语言
 * 
 * @table language
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:02
 * @version 1.0
 */
@Repository("LanguageDAO")
public class LanguageDAOImpl extends BaseDAOImpl<LanguageDO> implements LanguageDAO {

	public String getNameSpace() {
		return "language";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
