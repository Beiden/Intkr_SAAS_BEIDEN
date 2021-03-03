package com.intkr.saas.manager.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.PaticipateDAO;
import com.intkr.saas.domain.bo.order.PaticipateBO;
import com.intkr.saas.domain.dbo.order.PaticipateDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.PaticipateManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-14 上午8:25:56
 * @version 1.0
 */
@Repository("PaticipateManager")
public class PaticipateManagerImpl extends BaseManagerImpl<PaticipateBO, PaticipateDO> implements PaticipateManager {

	@Resource
	private PaticipateDAO paticipateDAO;

	public BaseDAO<PaticipateDO> getBaseDAO() {
		return paticipateDAO;
	}

}
