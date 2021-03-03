package com.intkr.saas.manager.opa.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.opa.OaClientDAO;
import com.intkr.saas.domain.bo.opa.OaClientBO;
import com.intkr.saas.domain.dbo.opa.OaClientDO;
import com.intkr.saas.manager.opa.OaClientManager;

/**
 * 客户端
 * 
 * @table oa_client
 * 
 * @author Beiden
 * @date 2020-11-04 20:18:06
 * @version 1.0
 */
@Repository("OaClientManager")
public class OaClientManagerImpl extends BaseManagerImpl<OaClientBO, OaClientDO> implements OaClientManager {

	@Resource
	private OaClientDAO oaClientDAO;

	public BaseDAO<OaClientDO> getBaseDAO() {
		return oaClientDAO;
	}

}
