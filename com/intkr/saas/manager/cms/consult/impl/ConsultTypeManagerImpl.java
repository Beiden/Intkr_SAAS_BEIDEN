package com.intkr.saas.manager.cms.consult.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.consult.ConsultTypeDAO;
import com.intkr.saas.domain.bo.consult.ConsultTypeBO;
import com.intkr.saas.domain.dbo.consult.ConsultTypeDO;
import com.intkr.saas.manager.cms.consult.ConsultTypeManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-04-14 21:37:49
 * @version 1.0
 */
@Repository("ConsultTypeManager")
public class ConsultTypeManagerImpl extends BaseManagerImpl<ConsultTypeBO, ConsultTypeDO> implements ConsultTypeManager {

	@Resource
	private ConsultTypeDAO consultTypeDAO;

	public BaseDAO<ConsultTypeDO> getBaseDAO() {
		return consultTypeDAO;
	}

	public ConsultTypeBO getByName(String type) {
		ConsultTypeBO query = new ConsultTypeBO();
		query.setType(type);
		return selectOne(query);
	}

}
