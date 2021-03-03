package com.intkr.saas.manager.shop;

import java.util.List;

import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.dbo.shop.ShopCommentDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:10:54
 * @version 1.0
 */
public interface ShopCommentManager extends BaseManager<ShopCommentBO, ShopCommentDO> {

	public Long countByRelatedId(Long relatedId);

	public ShopBO fill(ShopBO article);

	public <T> List<T> fill(List<T> list);

}
