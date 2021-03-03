package com.intkr.saas.manager.saas.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.saas.SaasThemeDAO;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.domain.dbo.saas.SaasThemeDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.saas.SaasThemeManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 10:45:08
 * @version 1.0
 */
@Repository("SaasThemeManager")
public class SaasThemeManagerImpl extends BaseManagerImpl<SaasThemeBO, SaasThemeDO> implements SaasThemeManager {

	private LoadingCache<Long, SaasThemeBO> idThemeCache = CacheBuilder.newBuilder().//
			maximumSize(10000).//
			expireAfterWrite(10, TimeUnit.MINUTES).// 缓存10分钟
			build(new CacheLoader<Long, SaasThemeBO>() {
				public SaasThemeBO load(Long key) {
					SaasThemeBO value = getWithoutCache(key);
					if (value == null) {
						value = new SaasThemeBO();
					}
					return value;
				}
			});

	@Resource
	private SaasThemeDAO themeDAO;

	public BaseDAO<SaasThemeDO> getBaseDAO() {
		return themeDAO;
	}

	public SaasThemeBO getByName(String name) {
		if (name == null || "".equals(name)) {
			return null;
		}
		SaasThemeBO query = new SaasThemeBO();
		query.setName(name);
		query.set_pageSize(1);
		return selectOne(query);
	}

	public SaasThemeBO get(Long primary) {
		if (primary == null || primary <= 0L) {
			return null;
		}
		try {
			SaasThemeBO client = idThemeCache.get(primary);
			if (client != null && client.getId() != null) {
				return client;
			}
			return null;
		} catch (Exception e) {
			logger.error("primary=" + primary, e);
			throw new RuntimeException("primary=" + primary);
		}
	}

	public SaasThemeBO getWithoutCache(Long primary) {
		if (primary == null || primary <= 0L) {
			return null;
		}
		return super.get(primary);
	}

}
