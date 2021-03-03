package com.intkr.saas.manager.cms.consult.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.consult.ConsultDAO;
import com.intkr.saas.domain.bo.consult.ConsultBO;
import com.intkr.saas.domain.dbo.consult.ConsultDO;
import com.intkr.saas.manager.cms.consult.ConsultManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-04-14 21:37:40
 * @version 1.0
 */
@Repository("ConsultManager")
public class ConsultManagerImpl extends BaseManagerImpl<ConsultBO, ConsultDO> implements ConsultManager {

	@Resource
	private ConsultDAO consultDAO;

	public BaseDAO<ConsultDO> getBaseDAO() {
		return consultDAO;
	}

}
