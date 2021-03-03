package com.intkr.saas.manager.tag.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.tag.TagRelatedDAO;
import com.intkr.saas.domain.bo.tag.TagRelatedBO;
import com.intkr.saas.domain.dbo.tag.TagRelatedDO;
import com.intkr.saas.manager.tag.TagRelatedManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午6:07:21
 * @version 1.0
 */
@Repository("TagRelatedManager")
public class TagRelatedManagerImpl extends BaseManagerImpl<TagRelatedBO, TagRelatedDO> implements TagRelatedManager {

	@Resource
	private TagRelatedDAO tagRelatedDAO;

	public BaseDAO<TagRelatedDO> getBaseDAO() {
		return tagRelatedDAO;
	}

	public List<TagRelatedBO> selectByRelatedId(Long relatedId) {
		TagRelatedBO query = new TagRelatedBO();
		query.set_pageSize(1000);
		query.setRelatedId(relatedId);
		return select(query);
	}

}
