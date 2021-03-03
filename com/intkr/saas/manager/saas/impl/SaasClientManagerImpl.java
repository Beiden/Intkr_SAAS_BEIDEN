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
import com.intkr.saas.dao.saas.SaasClientDAO;
import com.intkr.saas.domain.bo.saas.SaasClientBO;
import com.intkr.saas.domain.bo.saas.SaasConfigBO;
import com.intkr.saas.domain.bo.saas.SaasDomainBO;
import com.intkr.saas.domain.bo.saas.SaasThemeBO;
import com.intkr.saas.domain.dbo.saas.SaasClientDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.saas.SaasConfigManager;
import com.intkr.saas.manager.saas.SaasDomainManager;
import com.intkr.saas.manager.saas.SaasThemeManager;

/**
 * 
 * @author Beiden
 * @date 2017-03-19 22:00:49
 * @version 1.0
 */
@Repository("SaasClientManager")
public class SaasClientManagerImpl extends BaseManagerImpl<SaasClientBO, SaasClientDO> implements SaasClientManager {

	private LoadingCache<Long, SaasClientBO> saasIdBOCache = CacheBuilder.newBuilder().//
			maximumSize(10000).//
			expireAfterWrite(60 * 24, TimeUnit.MINUTES).// 缓存1天
			build(new CacheLoader<Long, SaasClientBO>() {
				public SaasClientBO load(Long key) {
					SaasClientBO value = getWithoutCache(key);
					if (value == null) {
						value = new SaasClientBO();
					}
					return value;
				}
			});

	@Resource
	private SaasClientDAO saasClientDAO;

	@Resource
	private SaasDomainManager appDomainManager;

	@Resource
	private SaasConfigManager appConfigManager;

	@Resource
	private SaasThemeManager saasThemeManager;

	public BaseDAO<SaasClientDO> getBaseDAO() {
		return saasClientDAO;
	}

	public SaasClientBO getSaas(Long saasId) {
		if (saasId == null || saasId <= 0L) {
			return null;
		}
		return get(saasId);
	}

	public String getSaasDomain(Long saasId) {
		if (saasId == null || saasId <= 0L) {
			return null;
		}
		SaasClientBO client = getFull(saasId);
		if (client == null) {
			return null;
		}
		if (client.getDomains() == null || client.getDomains().isEmpty()) {
			return null;
		}
		return client.getDomains().get(0).getDomain();
	}

	public SaasClientBO getWithoutCache(Long id) {
		if (id == null || id <= 0L) {
			return null;
		}
		return super.get(id);
	}

	public SaasClientBO get(Long id) {
		if (id == null || id <= 0L) {
			return null;
		}
		try {
			SaasClientBO client = saasIdBOCache.get(id);
			if (client != null && client.getId() != null) {
				return client;
			}
			return null;
		} catch (Exception e) {
			logger.error("Long id=" + id, e);
			throw new RuntimeException("Long id=" + id);
		}
	}

	public SaasClientBO getFullByDomain(String domain) {
		SaasDomainBO appDomain = appDomainManager.getByDomain(domain);
		if (appDomain == null) {
			throw new RuntimeException("domain[" + domain + "] do not exist!");
		}
		SaasClientBO app = get(appDomain.getSaasId());
		return full(app);
	}

	public SaasClientBO full(SaasClientBO saas) {
		if (saas == null) {
			return null;
		}
		appDomainManager.fill(saas);
		SaasConfigBO appConfig = appConfigManager.getBySaasId(saas.getId());
		if (appConfig != null) {
			saas.setConfig(appConfig);
			SaasThemeBO theme = saasThemeManager.get(appConfig.getThemeId());
			saas.setTheme(theme);
		}
		return saas;
	}

	public List<SaasClientBO> selectAndCountFull(SaasClientBO query) {
		if (query == null) {
			return new ArrayList<SaasClientBO>();
		}
		query = selectAndCount(query);
		List<SaasClientBO> list = query.getDatas();
		for (SaasClientBO app : list) {
			full(app);
		}
		return query.getDatas();
	}

	public SaasClientBO getFull(Long saasId) {
		if (saasId == null || saasId <= 0L) {
			return null;
		}
		SaasClientBO saas = get(saasId);
		full(saas);
		return saas;
	}

	public SaasClientBO getDefaultFull() {
		SaasClientBO saas = getDefault();
		full(saas);
		return saas;
	}

	public SaasClientBO getDefault() {
		SaasClientBO saas = saasIdBOCache.getIfPresent(-1l);
		if (saas != null) {
			return saas;
		}
		SaasClientBO query = new SaasClientBO();
		query.setIsDefault(1);
		saas = selectOne(query);
		saasIdBOCache.put(-1l, saas);
		return saas;
	}

	public long insert(SaasClientBO client) {
		long result = super.insert(client);
		if (client.getIsDefault() == 1) {
			saasIdBOCache.invalidate(-1l);
		}
		saasIdBOCache.invalidate(client.getId());
		return result;
	}

	public int update(SaasClientBO client) {
		int result = super.update(client);
		if (client.getIsDefault() == 1) {
			saasIdBOCache.invalidate(-1l);
		}
		saasIdBOCache.invalidate(client.getId());
		return result;
	}

}
