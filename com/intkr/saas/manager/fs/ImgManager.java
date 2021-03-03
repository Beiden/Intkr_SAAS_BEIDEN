package com.intkr.saas.manager.fs;

import java.util.List;

import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.item.DemandBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.dbo.fs.ImgDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:02:47
 * @version 1.0
 */
public interface ImgManager extends BaseManager<ImgBO, ImgDO> {

	public DemandBO fill(DemandBO demand);

	public ArticleBO fill(ArticleBO article);

	public ShopActivityBO fill(ShopActivityBO activity);

	public PageBO fill(PageBO article);

	public ImgBO getByName(String name);

	public List<?> fill(List<?> list);

	public List<?> fills(List<?> list);

	public ItemBO fill(ItemBO item);

	public ItemBO fills(ItemBO item);

	public DemandBO fills(DemandBO item);

}
