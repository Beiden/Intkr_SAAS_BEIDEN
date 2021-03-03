package com.intkr.saas.manager.sys.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.sys.MonitorDAO;
import com.intkr.saas.domain.bo.sys.MonitorBO;
import com.intkr.saas.domain.dbo.sys.MonitorDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.sys.MonitorManager;

/**
 * 
 * @author Administrator
 * 
 */
@Repository("MonitorManager")
public class MonitorManagerImpl extends BaseManagerImpl<MonitorBO, MonitorDO> implements MonitorManager {

	@Resource
	private MonitorDAO monitorDAO;

	public BaseDAO<MonitorDO> getBaseDAO() {
		return monitorDAO;
	}

	public void deleteReal(Date maxGmtCreated) {
		monitorDAO.deleteReal(maxGmtCreated);
	}

}
