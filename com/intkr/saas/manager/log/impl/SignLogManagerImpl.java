package com.intkr.saas.manager.log.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.log.SignLogDAO;
import com.intkr.saas.domain.bo.log.SignLogBO;
import com.intkr.saas.domain.dbo.log.SignLogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.log.SignLogManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午11:43:41
 * @version 1.0
 */
@Repository("SignLogManager")
public class SignLogManagerImpl extends BaseManagerImpl<SignLogBO, SignLogDO> implements SignLogManager {

	@Resource
	private SignLogDAO signLogDAO;

	public BaseDAO<SignLogDO> getBaseDAO() {
		return signLogDAO;
	}
	
	public void deleteReal(Date maxGmtCreated) {
		signLogDAO.deleteReal(maxGmtCreated);
	}

}
