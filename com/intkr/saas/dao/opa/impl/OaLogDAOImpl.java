package com.intkr.saas.dao.opa.impl;

import com.intkr.saas.dao.BaseDAOImpl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.opa.OaLogDAO;
import com.intkr.saas.domain.dbo.opa.OaLogDO;

/**
 * 接口日志
 * 
 * @table oa_log
 * 
 * @author Beiden
 * @date 2020-11-04 20:43:19
 * @version 1.0
 */
@Repository("OaLogDAO")
public class OaLogDAOImpl extends BaseDAOImpl<OaLogDO> implements OaLogDAO {

	public String getNameSpace() {
		return "oaLog";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
