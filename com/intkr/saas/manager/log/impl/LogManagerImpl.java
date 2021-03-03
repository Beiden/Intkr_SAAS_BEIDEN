package com.intkr.saas.manager.log.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.log.LogDAO;
import com.intkr.saas.domain.bo.log.LogBO;
import com.intkr.saas.domain.dbo.log.LogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.log.LogManager;

/**
 * 
 * @author Beiden
 * @date 2014-7-4 下午10:09:30
 * @version 1.0
 */
@Repository("LogManager")
public class LogManagerImpl extends BaseManagerImpl<LogBO, LogDO> implements LogManager {

	@Resource
	private LogDAO logDAO;

	public BaseDAO<LogDO> getBaseDAO() {
		return logDAO;
	}

	public void deleteReal(Date maxGmtCreated) {
		logDAO.deleteReal(maxGmtCreated);
	}

}
