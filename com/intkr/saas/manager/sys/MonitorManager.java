package com.intkr.saas.manager.sys;

import java.util.Date;

import com.intkr.saas.domain.bo.sys.MonitorBO;
import com.intkr.saas.domain.dbo.sys.MonitorDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Administrator
 * 
 */
public interface MonitorManager extends BaseManager<MonitorBO, MonitorDO> {

	public void deleteReal(Date maxGmtCreated);

}
