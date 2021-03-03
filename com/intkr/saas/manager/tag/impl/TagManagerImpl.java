package com.intkr.saas.manager.tag.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.tag.TagDAO;
import com.intkr.saas.domain.bo.tag.TagBO;
import com.intkr.saas.domain.dbo.tag.TagDO;
import com.intkr.saas.manager.tag.TagManager;
import com.intkr.saas.manager.tag.TagRelatedManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2011-6-29 下午4:05:31
 * @version 1.0
 */
@Repository
public class TagManagerImpl extends BaseManagerImpl<TagBO, TagDO> implements TagManager {

	@Resource
	private TagDAO tagDAO;

	@Resource
	private TagRelatedManager tagRelatedManager;

	public BaseDAO<TagDO> getBaseDAO() {
		return tagDAO;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof Object) {

			}
		}
		return list;
	}

	public TagBO getByName(String name) {
		TagBO query = new TagBO();
		query.setName(name);
		List<TagBO> list = select(query);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
