package com.intkr.saas.manager.fs.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.fs.ImgDAO;
import com.intkr.saas.distributed.search.client.ImgSearchClient;
import com.intkr.saas.domain.bo.article.ArticleBO;
import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.item.DemandBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.page.PageBO;
import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.dbo.fs.ImgDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.fs.ImgManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:02:47
 * @version 1.0
 */
@Repository("ImgManager")
public class ImgManagerImpl extends BaseManagerImpl<ImgBO, ImgDO> implements ImgManager {

	@Resource
	private ImgDAO imgDAO;

	@Resource
	private ImgSearchClient imgSearchClient;

	public BaseDAO<ImgDO> getBaseDAO() {
		return imgDAO;
	}

	public ArticleBO fill(ArticleBO article) {
		if (article == null || !IdEngine.isValidate(article.getSpeImgId())) {
			return null;
		}
		ImgBO img = get(article.getSpeImgId());
		article.setSpeImg(img);
		return article;
	}

	public ShopActivityBO fill(ShopActivityBO article) {
		if (article == null || !IdEngine.isValidate(article.getCoverImgId())) {
			return null;
		}
		ImgBO img = get(article.getCoverImgId());
		article.setCoverImg(img);
		return article;
	}

	public PageBO fill(PageBO article) {
		if (article == null || !IdEngine.isValidate(article.getSpeImgId())) {
			return null;
		}
		ImgBO img = get(article.getSpeImgId());
		article.setSpeImg(img);
		return article;
	}

	public ImgBO getByName(String name) {
		ImgBO query = new ImgBO();
		query.setName(name);
		List<ImgBO> list = select(query);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public List<?> fills(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ArticleBO) {
				fill((ArticleBO) obj);
			} else if (obj instanceof PageBO) {
				fill((PageBO) obj);
			} else if (obj instanceof ItemBO) {
				fills((ItemBO) obj);
			}
		}
		return list;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ArticleBO) {
				fill((ArticleBO) obj);
			} else if (obj instanceof PageBO) {
				fill((PageBO) obj);
			} else if (obj instanceof ItemBO) {
				fill((ItemBO) obj);
			}
		}
		return list;
	}

	public List<ImgBO> select(ImgBO query) {
		try {
			if (!query.isSearchDb()) {
				query = imgSearchClient.search(query);
				return query.getDatas();
			}
		} catch (Exception e) {
			return super.select(query);
		}
		return super.select(query);
	}

	public ImgBO selectAndCount(ImgBO query) {
		try {
			if (!query.isSearchDb()) {
				return imgSearchClient.search(query);
			}
		} catch (Exception e) {
			return super.selectAndCount(query);
		}
		return super.selectAndCount(query);
	}

	public long insert(ImgBO img) {
		long result = super.insert(img);
		imgSearchClient.dump(img.getId());
		return result;
	}

	public int update(ImgBO img) {
		int result = super.update(img);
		imgSearchClient.dump(img.getId());
		return result;
	}

	public ItemBO fill(ItemBO item) {
		if (item == null || item.getImgIds() == null || "".equals(item.getImgIds())) {
			return item;
		}
		String[] imgIds = item.getImgIds().split(";");
		List<ImgBO> imgs = new ArrayList<ImgBO>();
		for (String imgId : imgIds) {
			ImgBO bo = get(imgId);
			if (bo != null) {
				imgs.add(bo);
				break;
			}
		}
		item.setImgs(imgs);
		return item;
	}

	public DemandBO fill(DemandBO item) {
		if (item == null || item.getImgIds() == null || "".equals(item.getImgIds())) {
			return item;
		}
		String[] imgIds = item.getImgIds().split(";");
		List<ImgBO> imgs = new ArrayList<ImgBO>();
		for (String imgId : imgIds) {
			ImgBO bo = get(imgId);
			if (bo != null) {
				imgs.add(bo);
				break;
			}
		}
		item.setImgs(imgs);
		return item;
	}

	public ItemBO fills(ItemBO item) {
		if (item == null || item.getImgIds() == null || "".equals(item.getImgIds())) {
			return item;
		}
		String[] imgIds = item.getImgIds().split(";");
		List<ImgBO> imgs = new ArrayList<ImgBO>();
		for (String imgId : imgIds) {
			ImgBO bo = get(imgId);
			if (bo != null) {
				imgs.add(bo);
			}
		}
		item.setImgs(imgs);
		return item;
	}

	public DemandBO fills(DemandBO item) {
		if (item == null || item.getImgIds() == null || "".equals(item.getImgIds())) {
			return item;
		}
		String[] imgIds = item.getImgIds().split(";");
		List<ImgBO> imgs = new ArrayList<ImgBO>();
		for (String imgId : imgIds) {
			ImgBO bo = get(imgId);
			if (bo != null) {
				imgs.add(bo);
			}
		}
		item.setImgs(imgs);
		return item;
	}

	public int delete(Object imgIdObj) {
		if (imgIdObj == null) {
			return -1;
		}
		Long imgId = Long.valueOf(imgIdObj.toString());
		int result = super.delete(imgId);
		imgSearchClient.delete(imgId);
		return result;
	}

}
