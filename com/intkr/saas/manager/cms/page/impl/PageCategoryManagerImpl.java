package com.intkr.saas.manager.cms.page.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageCategoryDAO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.page.PageCategoryBO;
import com.intkr.saas.domain.bo.page.PageCategoryRelatedBO;
import com.intkr.saas.domain.dbo.page.PageCategoryDO;
import com.intkr.saas.manager.cms.page.PageCategoryManager;
import com.intkr.saas.manager.cms.page.PageCategoryRelatedManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:12
 * @version 1.0
 */
@Repository("PageCategoryManager")
public class PageCategoryManagerImpl extends BaseManagerImpl<PageCategoryBO, PageCategoryDO> implements PageCategoryManager {

	@Resource
	private PageCategoryDAO pageCategoryDAO;

	@Resource
	private PageCategoryRelatedManager categoryRelatedManager;

	public BaseDAO<PageCategoryDO> getBaseDAO() {
		return pageCategoryDAO;
	}

	public List<PageCategoryBO> getFirstCategoryFull(Long saasId) {
		PageCategoryBO query = new PageCategoryBO();
		query.setSaasId(saasId);
		query.set_pageSize(10000);
		query.setQuery("orderBy", "sort");
		query.setQuery("order", "desc");
		List<PageCategoryBO> categorys = select(query);
		List<PageCategoryBO> firstCategorys = new ArrayList<PageCategoryBO>();
		Map<Long, PageCategoryBO> map = new HashMap<Long, PageCategoryBO>();
		for (PageCategoryBO bo : categorys) {
			map.put(bo.getId(), bo);
		}
		for (PageCategoryBO bo : categorys) {
			if (bo.getPid() != null && map.containsKey(bo.getPid())) {
				PageCategoryBO parent = map.get(bo.getPid());
				parent.addChild(bo);
				bo.setParent(parent);
			}
		}
		for (PageCategoryBO bo : categorys) {
			if (bo.getParent() == null) {
				firstCategorys.add(bo);
			}
		}
		return firstCategorys;
	}

	public PageBO fill(PageBO page) {
		if (page == null) {
			return page;
		}
		PageCategoryRelatedBO query = new PageCategoryRelatedBO();
		query.set_pageSize(1000);
		query.setPageId(page.getId());
		List<PageCategoryRelatedBO> list = categoryRelatedManager.select(query);
		List<PageCategoryBO> categorys = new ArrayList<PageCategoryBO>();
		for (PageCategoryRelatedBO bo : list) {
			PageCategoryBO category = get(bo.getCategoryId());
			if (category != null) {
				categorys.add(category);
			}
		}
		page.setCategorys(categorys);
		return page;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof PageBO) {
				fill((PageBO) obj);
			}
		}
		return list;
	}

}
