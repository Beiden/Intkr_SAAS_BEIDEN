package com.intkr.saas.manager.fs.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.fs.RelationshipDAO;
import com.intkr.saas.domain.bo.fs.MediaRelationshipBO;
import com.intkr.saas.domain.dbo.fs.MediaRelationshipDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.fs.RelationshipManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 12:30:23
 * @version 1.0
 */
@Repository("RelationshipManager")
public class RelationshipManagerImpl extends BaseManagerImpl<MediaRelationshipBO, MediaRelationshipDO> implements RelationshipManager {

	@Resource
	private RelationshipDAO mediaRelationshipDAO;

	public BaseDAO<MediaRelationshipDO> getBaseDAO() {
		return mediaRelationshipDAO;
	}

}
