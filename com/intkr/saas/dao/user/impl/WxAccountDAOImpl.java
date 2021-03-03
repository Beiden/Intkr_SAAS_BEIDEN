package com.intkr.saas.dao.user.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.user.WxAccountDAO;
import com.intkr.saas.domain.dbo.user.WxAccountDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-3 下午10:13:48
 * @version 1.0
 */
@Repository("WxAccountDAO")
public class WxAccountDAOImpl extends BaseDAOImpl<WxAccountDO> implements WxAccountDAO {

	public String getNameSpace() {
		return "wxAccount";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
