package com.intkr.saas.manager.cms.menu.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.menu.FrontMenuDAO;
import com.intkr.saas.domain.bo.menu.FrontMenuBO;
import com.intkr.saas.domain.dbo.menu.FrontMenuDO;
import com.intkr.saas.manager.cms.menu.FrontMenuManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午7:44:06
 * @version 1.0
 */
@Repository("FrontMenuManager")
public class FrontMenuManagerImpl extends BaseManagerImpl<FrontMenuBO, FrontMenuDO> implements FrontMenuManager {

	@Resource
	private FrontMenuDAO frontMenuDAO;

	public BaseDAO<FrontMenuDO> getBaseDAO() {
		return frontMenuDAO;
	}

}
