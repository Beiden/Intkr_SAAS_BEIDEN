package com.intkr.saas.manager.shop.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.shop.ShopActivityDAO;
import com.intkr.saas.domain.bo.shop.ShopActivityBO;
import com.intkr.saas.domain.bo.sns.AttentionBO;
import com.intkr.saas.domain.bo.sns.MsgBO;
import com.intkr.saas.domain.dbo.shop.ShopActivityDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.shop.ShopActivityManager;

/**
 * 
 * @author Beiden
 * @date 2016-4-10 上午10:12:39
 * @version 1.0
 */
@Repository("ActivityManager")
public class ShopActivityManagerImpl extends BaseManagerImpl<ShopActivityBO, ShopActivityDO> implements ShopActivityManager {

	@Resource
	private ShopActivityDAO activityDAO;

	public BaseDAO<ShopActivityDO> getBaseDAO() {
		return activityDAO;
	}

	public AttentionBO fill(AttentionBO attention) {
		if (attention == null) {
			return attention;
		}
		if (attention != null && IdEngine.isValidate(attention.getRelatedId())) {
			ShopActivityBO article = get(attention.getRelatedId());
			attention.setRelatedObject(article);
		}
		return attention;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<MsgBO>();
		}
		for (Object obj : list) {
			if (obj instanceof AttentionBO) {
				fill((AttentionBO) obj);
			} else if (obj instanceof AttentionBO) {
				fill((AttentionBO) obj);
			}
		}
		return list;
	}

}
