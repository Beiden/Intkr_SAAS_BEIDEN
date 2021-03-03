package com.intkr.saas.manager.saas.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.domain.bo.link.LinkBO;
import com.intkr.saas.domain.dbo.link.LinkDO;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.cms.link.LinkDAO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.saas.LinkManager;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午9:49:34
 * @version 1.0
 */
@Repository("LinkManager")
public class LinkManagerImpl extends BaseManagerImpl<LinkBO, LinkDO> implements LinkManager {

	@Resource
	private LinkDAO linkDAO;

	public BaseDAO<LinkDO> getBaseDAO() {
		return linkDAO;
	}

}
