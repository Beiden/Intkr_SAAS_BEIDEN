package com.intkr.saas.dao.sns.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.sns.AttentionDAO;
import com.intkr.saas.domain.dbo.sns.AttentionDO;

/**
 * 
 * @author Beiden
 * @date 2011-8-14 下午8:24:01
 * @version 1.0
 */
@Repository("AttentionDAO")
public class AttentionDAOImpl extends BaseDAOImpl<AttentionDO> implements AttentionDAO {

	public String getNameSpace() {
		return "attention";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
