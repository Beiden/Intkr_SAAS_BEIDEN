package com.intkr.saas.manager.tag;

import java.util.List;

import com.intkr.saas.domain.bo.tag.TagBO;
import com.intkr.saas.domain.dbo.tag.TagDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beidenz
 * @date 2011-6-29 下午4:05:12
 * @version 1.0
 */
public interface TagManager extends BaseManager<TagBO, TagDO> {

	public List<?> fill(List<?> list);

	public TagBO getByName(String name);

}
