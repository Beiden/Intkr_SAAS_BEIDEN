package com.intkr.saas.dao.log;

import java.util.Date;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.dbo.log.SignLogDO;

/**
 * 
 * @author Beiden
 * @date 2016-4-24 上午11:42:35
 * @version 1.0
 */
public interface SignLogDAO extends BaseDAO<SignLogDO> {

	public void deleteReal(Date maxGmtCreated);

}
