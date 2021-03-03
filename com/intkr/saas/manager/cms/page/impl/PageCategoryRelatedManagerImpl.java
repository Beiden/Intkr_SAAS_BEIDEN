package com.intkr.saas.manager.cms.page.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageCategoryRelatedDAO;
import com.intkr.saas.domain.bo.page.PageCategoryRelatedBO;
import com.intkr.saas.domain.dbo.page.PageCategoryRelatedDO;
import com.intkr.saas.manager.cms.page.PageCategoryRelatedManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:22
 * @version 1.0
 */
@Repository("PageCategoryRelatedManager")
public class PageCategoryRelatedManagerImpl extends BaseManagerImpl<PageCategoryRelatedBO, PageCategoryRelatedDO> implements PageCategoryRelatedManager {

	@Resource
	private PageCategoryRelatedDAO pageCategoryRelatedDAO;

	public BaseDAO<PageCategoryRelatedDO> getBaseDAO() {
		return pageCategoryRelatedDAO;
	}

	public List<PageCategoryRelatedBO> selectByPageId(Long pageId) {
		PageCategoryRelatedBO query = new PageCategoryRelatedBO();
		query.set_pageSize(1000);
		query.setPageId(pageId);
		return select(query);
	}

}
