package com.intkr.saas.manager.fs.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.fs.CategoryRelatedDAO;
import com.intkr.saas.domain.bo.fs.MediaCategoryRelatedBO;
import com.intkr.saas.domain.dbo.fs.MediaCategoryRelatedDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.fs.MediaCategoryRelatedManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 15:38:58
 * @version 1.0
 */
@Repository("CategoryRelatedManager")
public class CategoryRelatedManagerImpl extends BaseManagerImpl<MediaCategoryRelatedBO, MediaCategoryRelatedDO> implements MediaCategoryRelatedManager {

	@Resource
	private CategoryRelatedDAO mediaCategoryRelatedDAO;

	public BaseDAO<MediaCategoryRelatedDO> getBaseDAO() {
		return mediaCategoryRelatedDAO;
	}

}
