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
import com.intkr.saas.dao.saas.SaasDomainDAO;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.domain.dbo.saas.SaasDomainDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.saas.SaasDomainManager;

/**
 * 
 * @author Beiden
 * @date 2017-05-25 22:55:26
 * @version 1.0
 */
@Repository("AppDomainManager")
public class SaasDomainManagerImpl extends BaseManagerImpl<SaasDomainBO, SaasDomainDO> implements SaasDomainManager {

	private LoadingCache<String, SaasDomainBO> domainBOCache = CacheBuilder.newBuilder().//
			maximumSize(10000).//
			expireAfterWrite(10, TimeUnit.MINUTES).// 缓存10分钟
			build(new CacheLoader<String, SaasDomainBO>() {
				public SaasDomainBO load(String key) {
					SaasDomainBO value = getByDomainWithoutCache(key);
					if (value == null) {
						value = new SaasDomainBO();
					}
					return value;
				}
			});

	private LoadingCache<Long, List<SaasDomainBO>> saasIdappDomainListCache = CacheBuilder.newBuilder().//
			maximumSize(10000).//
			expireAfterWrite(10, TimeUnit.MINUTES).// 缓存10分钟
			build(new CacheLoader<Long, List<SaasDomainBO>>() {
				public List<SaasDomainBO> load(Long key) {
					List<SaasDomainBO> value = getBySaasIdWithoutCache(key);
					return value;
				}
			});

	@Resource
	private SaasDomainDAO websiteDomainConfigDAO;

	public BaseDAO<SaasDomainDO> getBaseDAO() {
		return websiteDomainConfigDAO;
	}

	public SaasDomainBO getByDomain(String domain) {
		try {
			SaasDomainBO client = domainBOCache.get(domain);
			if (client != null && client.getId() != null) {
				return client;
			}
			return null;
		} catch (Exception e) {
			logger.error("domain=" + domain, e);
			throw new RuntimeException("domain=" + domain);
		}
	}

	public SaasDomainBO getByDomainWithoutCache(String domain) {
		SaasDomainBO query = new SaasDomainBO();
		query.setDomain(domain);
		SaasDomainBO data = selectOne(query);
		return data;
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
		List<SaasDomainBO> list = getBySaasId(saas.getId());
		saas.setDomains(list);
		return saas;
	}

	public List<SaasDomainBO> getBySaasId(Long saasId) {
		try {
			List<SaasDomainBO> list = saasIdappDomainListCache.get(saasId);
			return list;
		} catch (Exception e) {
			logger.error("appId=" + saasId, e);
			throw new RuntimeException("appId=" + saasId);
		}
	}

	public List<SaasDomainBO> getBySaasIdWithoutCache(Long saasId) {
		if (saasId == null || saasId <= 0L) {
			return new ArrayList<SaasDomainBO>();
		}
		SaasDomainBO query = new SaasDomainBO();
		query.setSaasId(saasId);
		query.set_pageSize(10000);
		List<SaasDomainBO> list = select(query);
		return list;
	}

	public long insert(SaasDomainBO domain) {
		long result = super.insert(domain);
		domainBOCache.invalidate(domain.getDomain());
		saasIdappDomainListCache.invalidate(domain.getSaasId());
		return result;
	}

	public int delete(Object id) {
		SaasDomainBO domain = get(id);
		if (domain == null) {
			return 0;
		}
		int result = super.delete(id);
		domainBOCache.invalidate(domain.getDomain());
		saasIdappDomainListCache.invalidate(domain.getSaasId());
		return result;
	}

	public int update(SaasDomainBO domain) {
		int result = super.update(domain);
		domainBOCache.invalidate(domain.getDomain());
		saasIdappDomainListCache.invalidate(domain.getSaasId());
		return result;
	}

	public void invalidateCache(String domain) {
		domainBOCache.invalidate(domain);
	}

}
