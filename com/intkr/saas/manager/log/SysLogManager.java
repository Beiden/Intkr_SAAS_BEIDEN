package com.intkr.saas.manager.log;

import java.util.Date;
import java.util.Map;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.log.SysLogBO;
import com.intkr.saas.domain.dbo.log.SysLogDO;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:17:54
 * @version 1.0
 */
public interface SysLogManager extends BaseManager<SysLogBO, SysLogDO> {

	public Long count(Map<String, Object> map);

	public void deleteReal(Date maxGmtCreated);

}
