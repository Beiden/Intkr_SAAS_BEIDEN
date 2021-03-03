package com.intkr.saas.manager.cms.page;

import java.util.List;

import com.intkr.saas.domain.bo.page.PageTagRelatedBO;
import com.intkr.saas.domain.dbo.page.PageTagRelatedDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:34
 * @version 1.0
 */
public interface PageTagRelatedManager extends BaseManager<PageTagRelatedBO, PageTagRelatedDO> {

	public List<PageTagRelatedBO> selectByRelatedId(Long relatedId);

}
