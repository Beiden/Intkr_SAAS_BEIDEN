package com.intkr.saas.manager.opa.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.opa.OaLogDAO;
import com.intkr.saas.domain.bo.opa.OaLogBO;
import com.intkr.saas.domain.dbo.opa.OaLogDO;
import com.intkr.saas.manager.opa.OaLogManager;

/**
 * 接口日志
 * 
 * @table oa_log
 * 
 * @author Beiden
 * @date 2020-11-04 20:43:19
 * @version 1.0
 */
@Repository("OaLogManager")
public class OaLogManagerImpl extends BaseManagerImpl<OaLogBO, OaLogDO> implements OaLogManager {

	@Resource
	private OaLogDAO oaLogDAO;

	public BaseDAO<OaLogDO> getBaseDAO() {
		return oaLogDAO;
	}

}
