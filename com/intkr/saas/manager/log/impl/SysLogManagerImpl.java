package com.intkr.saas.manager.log.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.log.SysLogDAO;
import com.intkr.saas.domain.bo.log.SysLogBO;
import com.intkr.saas.domain.dbo.log.SysLogDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.log.SysLogManager;

/**
 * 
 * @author Beiden
 * @date 2011-5-24 上午10:18:08
 * @version 1.0
 */
@Repository("SysLogManager")
public class SysLogManagerImpl extends BaseManagerImpl<SysLogBO, SysLogDO> implements SysLogManager {

	@Resource
	private SysLogDAO sysLogDAO;

	public BaseDAO<SysLogDO> getBaseDAO() {
		return sysLogDAO;
	}

	public Long count(Map<String, Object> map) {
		return sysLogDAO.count(map);
	}

	public void deleteReal(Date maxGmtCreated) {
		sysLogDAO.deleteReal(maxGmtCreated);
	}

}
