package com.intkr.saas.manager.fs.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.fs.CategoryDAO;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.bo.fs.MediaCategoryBO;
import com.intkr.saas.domain.bo.fs.MediaCategoryRelatedBO;
import com.intkr.saas.domain.dbo.fs.MediaCategoryDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.fs.MediaCategoryManager;
import com.intkr.saas.manager.fs.MediaCategoryRelatedManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 15:38:37
 * @version 1.0
 */
@Repository("CategoryManager")
public class CategoryManagerImpl extends BaseManagerImpl<MediaCategoryBO, MediaCategoryDO> implements MediaCategoryManager {

	@Resource
	private CategoryDAO mediaCategoryDAO;

	@Resource
	private MediaCategoryRelatedManager categoryRelatedManager;

	public BaseDAO<MediaCategoryDO> getBaseDAO() {
		return mediaCategoryDAO;
	}

	public List<MediaCategoryBO> getFirstCategoryFull(Long saasId) {
		MediaCategoryBO query = new MediaCategoryBO();
		query.setSaasId(saasId);
		query.set_pageSize(10000);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		List<MediaCategoryBO> categorys = select(query);
		List<MediaCategoryBO> firstCategorys = new ArrayList<MediaCategoryBO>();
		Map<Long, MediaCategoryBO> map = new HashMap<Long, MediaCategoryBO>();
		for (MediaCategoryBO bo : categorys) {
			map.put(bo.getId(), bo);
		}
		for (MediaCategoryBO bo : categorys) {
			if (bo.getPid() != null && map.containsKey(bo.getPid())) {
				MediaCategoryBO parent = map.get(bo.getPid());
				parent.addChild(bo);
				bo.setParent(parent);
			}
		}
		for (MediaCategoryBO bo : categorys) {
			if (bo.getParent() == null) {
				firstCategorys.add(bo);
			}
		}
		return firstCategorys;
	}

	public MediaBO fill(MediaBO article) {
		if (article == null) {
			return article;
		}
		MediaCategoryRelatedBO query = new MediaCategoryRelatedBO();
		query.set_pageSize(1000);
		query.setRelatedId(article.getId());
		List<MediaCategoryRelatedBO> list = categoryRelatedManager.select(query);
		List<MediaCategoryBO> categorys = new ArrayList<MediaCategoryBO>();
		for (MediaCategoryRelatedBO bo : list) {
			MediaCategoryBO category = get(bo.getCategoryId());
			if (category != null) {
				categorys.add(category);
			}
		}
		article.setCategorys(categorys);
		return article;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (Object obj : list) {
			if (obj instanceof MediaBO) {
				fill((MediaBO) obj);
			}
		}
		return list;
	}

}
