package com.intkr.saas.manager.cms.page;

import java.util.List;

import com.intkr.saas.domain.bo.page.PageCategoryRelatedBO;
import com.intkr.saas.domain.dbo.page.PageCategoryRelatedDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:22
 * @version 1.0
 */
public interface PageCategoryRelatedManager extends BaseManager<PageCategoryRelatedBO, PageCategoryRelatedDO> {

	public List<PageCategoryRelatedBO> selectByPageId(Long pageId);

}
