package com.intkr.saas.manager.cms.page;

import java.util.List;

import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.page.PageCategoryBO;
import com.intkr.saas.domain.dbo.page.PageCategoryDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:12
 * @version 1.0
 */
public interface PageCategoryManager extends BaseManager<PageCategoryBO, PageCategoryDO> {

	public List<PageCategoryBO> getFirstCategoryFull(Long saasId);

	public List<?> fill(List<?> list);

	public PageBO fill(PageBO article);

}
