package com.intkr.saas.manager.fs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.fs.MediaDAO;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.dbo.fs.MediaDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.fs.MediaManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:02:47
 * @version 1.0
 */
@Repository("MediaManager")
public class MediaManagerImpl extends BaseManagerImpl<MediaBO, MediaDO> implements MediaManager {

	@Resource
	private MediaDAO mediaDAO;

	public BaseDAO<MediaDO> getBaseDAO() {
		return mediaDAO;
	}

	public MediaBO getByName(String name) {
		MediaBO query = new MediaBO();
		query.setName(name);
		List<MediaBO> list = select(query);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
