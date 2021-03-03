package com.intkr.saas.manager.fs;

import java.util.List;

import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.domain.bo.fs.MediaBO;
import com.intkr.saas.domain.bo.fs.MediaCategoryBO;
import com.intkr.saas.domain.dbo.fs.MediaCategoryDO;

/**
 * 
 * @author Beiden
 * @date 2017-07-20 15:38:37
 * @version 1.0
 */
public interface MediaCategoryManager extends BaseManager<MediaCategoryBO, MediaCategoryDO> {

	public List<MediaCategoryBO> getFirstCategoryFull(Long saasId);

	public <T> List<T> fill(List<T> list);

	public MediaBO fill(MediaBO article);

}
