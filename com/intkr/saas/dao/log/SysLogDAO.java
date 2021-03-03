package com.intkr.saas.dao.log;

import java.util.Date;
import java.util.Map;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.log.SysLogDO;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:17:03
 * @version 1.0
 */
public interface SysLogDAO extends BaseDAO<SysLogDO> {

	public Long count(Map<String, Object> map);

	public void deleteReal(Date maxGmtCreated);

}
