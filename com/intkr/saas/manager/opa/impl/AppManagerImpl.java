package com.intkr.saas.manager.opa.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.opa.AppDAO;
import com.intkr.saas.domain.bo.opa.AppBO;
import com.intkr.saas.domain.dbo.opa.AppDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.opa.AppManager;

/**
 * 
 * 
 * @table app_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
@Repository("opa.AppManager")
public class AppManagerImpl extends BaseManagerImpl<AppBO, AppDO> implements AppManager {

	@Resource
	private AppDAO appDAO;

	public BaseDAO<AppDO> getBaseDAO() {
		return appDAO;
	}

}
