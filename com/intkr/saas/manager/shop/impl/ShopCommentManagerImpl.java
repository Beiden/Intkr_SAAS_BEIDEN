package com.intkr.saas.manager.shop.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shop.ShopCommentDAO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.domain.bo.shop.ShopCommentBO;
import com.intkr.saas.domain.dbo.shop.ShopCommentDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shop.ShopCommentManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-18 13:10:54
 * @version 1.0
 */
@Repository("ShopCommentManager")
public class ShopCommentManagerImpl extends BaseManagerImpl<ShopCommentBO, ShopCommentDO> implements ShopCommentManager {

	@Resource
	private ShopCommentDAO commentDAO;

	public BaseDAO<ShopCommentDO> getBaseDAO() {
		return commentDAO;
	}

	public Long countByRelatedId(Long relatedId) {
		ShopCommentBO query = new ShopCommentBO();
		query.setRelatedId(relatedId);
		return count(query);
	}

	public ShopBO fill(ShopBO article) {
		if (article != null) {
			ShopCommentBO query = new ShopCommentBO();
			query.setRelatedId(article.getId());
			List<ShopCommentBO> commentList = select(query);
			article.setComments(commentList);
		}
		return article;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof ShopBO) {
				fill((ShopBO) bo);
			}
		}
		return list;
	}

}
