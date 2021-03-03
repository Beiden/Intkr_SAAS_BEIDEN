package com.intkr.saas.dao.lang.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.lang.LangContentDAO;
import com.intkr.saas.domain.dbo.lang.LangContentDO;

/**
 * 内容
 * 
 * @table lang_content
 * 
 * @author Beiden
 * @date 2020-11-03 12:56:16
 * @version 1.0
 */
@Repository("LangContentDAO")
public class LangContentDAOImpl extends BaseDAOImpl<LangContentDO> implements LangContentDAO {

	public String getNameSpace() {
		return "langContent";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
