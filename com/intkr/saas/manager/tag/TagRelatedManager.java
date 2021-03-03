package com.intkr.saas.manager.tag;

import java.util.List;

import com.intkr.saas.domain.bo.tag.TagRelatedBO;
import com.intkr.saas.domain.dbo.tag.TagRelatedDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午6:06:53
 * @version 1.0
 */
public interface TagRelatedManager extends BaseManager<TagRelatedBO, TagRelatedDO> {

	public List<TagRelatedBO> selectByRelatedId(Long relatedId);

}
