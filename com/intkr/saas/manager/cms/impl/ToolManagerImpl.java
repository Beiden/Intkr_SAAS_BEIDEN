package com.intkr.saas.manager.cms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.tool.ToolDAO;
import com.intkr.saas.domain.bo.tool.ToolBO;
import com.intkr.saas.domain.dbo.tool.ToolDO;
import com.intkr.saas.manager.cms.tool.ToolManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 10:58:26
 * @version 1.0
 */
@Repository("ToolManager")
public class ToolManagerImpl extends BaseManagerImpl<ToolBO, ToolDO> implements ToolManager {

	@Resource
	private ToolDAO toolDAO;

	public BaseDAO<ToolDO> getBaseDAO() {
		return toolDAO;
	}

}
