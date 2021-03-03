package com.intkr.saas.manager.saas.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.saas.SaasConfigDAO;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasConfigBO;
import com.intkr.saas.domain.dbo.saas.SaasConfigDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.saas.SaasConfigManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-24 19:17:55
 * @version 1.0
 */
@Repository("AppConfigManager")
public class SaasConfigManagerImpl extends BaseManagerImpl<SaasConfigBO, SaasConfigDO> implements SaasConfigManager {

	private LoadingCache<Long, SaasConfigBO> saasConfigCache = CacheBuilder.newBuilder().//
			maximumSize(10000).//
			expireAfterWrite(10, TimeUnit.MINUTES).// 缓存10分钟
			build(new CacheLoader<Long, SaasConfigBO>() {
				public SaasConfigBO load(Long key) {
					SaasConfigBO value = getBySaasIdWithoutCache(key);
					if (value == null) {
						value = new SaasConfigBO();
					}
					return value;
				}
			});

	@Resource
	private SaasConfigDAO themeSelectDAO;

	public BaseDAO<SaasConfigDO> getBaseDAO() {
		return themeSelectDAO;
	}

	public <T> List<T> fill(List<T> list) {
		if (list == null) {
			return new ArrayList<T>();
		}
		for (T bo : list) {
			if (bo instanceof SaasClientBO) {
				fill((SaasClientBO) bo);
			}
		}
		return list;
	}

	public SaasClientBO fill(SaasClientBO saas) {
		if (saas == null) {
			return null;
		}
		SaasConfigBO config = getBySaasId(saas.getId());
		saas.setConfig(config);
		return saas;
	}

	public SaasConfigBO getBySaasId(Long saasId) {
		try {
			SaasConfigBO client = saasConfigCache.get(saasId);
			if (client != null && client.getId() != null) {
				return client;
			}
			return null;
		} catch (Exception e) {
			logger.error("appId=" + saasId, e);
			throw new RuntimeException("appId=" + saasId);
		}
	}

	public SaasConfigBO getBySaasIdWithoutCache(Long saasId) {
		if (saasId == null || saasId <= 0L) {
			return null;
		}
		SaasConfigBO query = new SaasConfigBO();
		query.setSaasId(saasId);
		query.set_pageSize(1);
		SaasConfigBO config = selectOne(query);
		return config;
	}

	public long insert(SaasConfigBO object) {
		long result = super.insert(object);
		saasConfigCache.invalidate(object.getSaasId());
		return result;
	}

}
