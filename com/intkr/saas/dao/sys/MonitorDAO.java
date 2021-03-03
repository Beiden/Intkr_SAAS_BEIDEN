package com.intkr.saas.dao.sys;


import java.util.Date;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.sys.MonitorDO;

/**
 * 
 * @author Administrator
 *
 */
public interface MonitorDAO extends BaseDAO<MonitorDO> {

	public void deleteReal(Date maxGmtCreated);
	
}
