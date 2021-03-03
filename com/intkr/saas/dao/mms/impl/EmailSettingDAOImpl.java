package com.intkr.saas.dao.mms.impl;


import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.mms.EmailSettingDAO;
import com.intkr.saas.domain.dbo.mms.EmailSettingDO;

/**
 * 
 * @author Beiden
 * @date 2017-03-13 18:53:32
 * @version 1.0
 */
@Repository("EmailSettingDAO")
public class EmailSettingDAOImpl extends BaseDAOImpl<EmailSettingDO> implements EmailSettingDAO {

	public String getNameSpace() {
		return "emailSetting";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
