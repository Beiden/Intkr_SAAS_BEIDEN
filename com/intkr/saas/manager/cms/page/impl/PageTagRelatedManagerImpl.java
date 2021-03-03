package com.intkr.saas.manager.cms.page.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.cms.page.PageTagRelatedDAO;
import com.intkr.saas.domain.bo.page.PageTagRelatedBO;
import com.intkr.saas.domain.dbo.page.PageTagRelatedDO;
import com.intkr.saas.manager.cms.page.PageTagRelatedManager;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 17:15:34
 * @version 1.0
 */
@Repository("PageTagRelatedManager")
public class PageTagRelatedManagerImpl extends BaseManagerImpl<PageTagRelatedBO, PageTagRelatedDO> implements PageTagRelatedManager {

	@Resource
	private PageTagRelatedDAO pageTagRelatedDAO;

	public BaseDAO<PageTagRelatedDO> getBaseDAO() {
		return pageTagRelatedDAO;
	}

	public List<PageTagRelatedBO> selectByRelatedId(Long relatedId) {
		PageTagRelatedBO query = new PageTagRelatedBO();
		query.set_pageSize(1000);
		query.setRelatedId(relatedId);
		return select(query);
	}

}
