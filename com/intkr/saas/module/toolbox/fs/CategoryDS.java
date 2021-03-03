package com.intkr.saas.module.toolbox.fs;

import java.util.List;

import com.intkr.saas.domain.bo.fs.MediaCategoryBO;
import com.intkr.saas.manager.fs.MediaCategoryManager;
import com.intkr.saas.module.toolbox.BaseToolBox;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-9-20 上午9:50:56
 * @version 1.0
 */
public class CategoryDS extends BaseToolBox {

	private MediaCategoryManager categoryManager = IOC.get(MediaCategoryManager.class);

	public List<MediaCategoryBO> select(Long saasId) {
		List<MediaCategoryBO> list = categoryManager.getFirstCategoryFull(saasId);
		return list;
	}

}
