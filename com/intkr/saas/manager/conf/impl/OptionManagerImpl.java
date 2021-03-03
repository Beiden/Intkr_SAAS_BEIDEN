package com.intkr.saas.manager.conf.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.intkr.saas.conf.CmsConf;
import com.intkr.saas.conf.Switch;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.conf.OptionDAO;
import com.intkr.saas.domain.bo.conf.OptionBO;
import com.intkr.saas.domain.dbo.conf.OptionDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.conf.OptionManager;

/**
 * 
 * @author Beiden
 * @date 2011-5-7 下午12:46:26
 * @version 1.0
 */
@Repository("OptionManager")
public class OptionManagerImpl extends BaseManagerImpl<OptionBO, OptionDO> implements OptionManager {

	@Resource
	private OptionDAO optionDAO;

	public BaseDAO<OptionDO> getBaseDAO() {
		return optionDAO;
	}

	private LoadingCache<String, OptionBO> nameValueCache = CacheBuilder.newBuilder().//
			maximumSize(10000).//
			expireAfterWrite(10, TimeUnit.MINUTES).// 缓存10分钟
			build(new CacheLoader<String, OptionBO>() {
				public OptionBO load(String key) {
					OptionBO value = getByNameWithoutCache(key);
					if (value == null) {
						value = new OptionBO();
					}
					return value;
				}
			});

	private OptionBO getByNameWithoutCache(String name) {
		String[] names = name.split(";");
		OptionBO query = new OptionBO();
		if (!"null".equals(names[0])) {
			query.setSaasId(Long.valueOf(names[0]));
		}
		query.setName(names[1]);
		List<OptionBO> list = select(query);
		if (list.size() != 0) {
			return list.get(0);
		}
		OptionBO optionBO = new OptionBO();
		optionBO.setSaasId(Long.valueOf(names[0]));
		optionBO.setName(names[1]);
		return optionBO;
	}

	public OptionBO getByName(Long saasId, String name) {
		try {
			OptionBO backendTheme = nameValueCache.get(saasId + ";" + name);
			if (backendTheme != null && backendTheme.getId() != null) {
				return backendTheme;
			}
			return null;
		} catch (Exception e) {
			String errorMsg = "saasId=" + saasId + ";name=" + name;
			logger.error(errorMsg, e);
			throw new RuntimeException(errorMsg);
		}
	}

	public OptionBO increaseCount(Long saasId, String name) {
		OptionBO bo = getByName(saasId, name);
		if (bo == null) {
			bo = new OptionBO();
			bo.setSaasId(saasId);
			bo.setName(name);
			bo.setValue("1");
			insert(bo);
		} else {
			Long l = Long.valueOf(bo.getValue());
			bo.setValue((l + 1) + "");
			update(bo);
		}
		return getByName(saasId, name);
	}

	public long insert(OptionBO object) {
		long result = super.insert(object);
		nameValueCache.invalidate(object.getSaasId() + ";" + object.getName());
		return result;
	}

	public int update(OptionBO object) {
		int result = super.update(object);
		nameValueCache.invalidate(object.getSaasId() + ";" + object.getName());
		return result;
	}

	public void updateOrInsert(Long saasId, String name, String value) {
		OptionBO config = getByName(saasId, name);
		if (config != null) {
			config.setValue(value);
			update(config);
		} else {
			config = new OptionBO();
			config.setSaasId(saasId);
			config.setName(name);
			config.setValue(value);
			insert(config);
		}
		nameValueCache.invalidate(saasId + ";" + name);
	}

	public String getAdminEmail(Long saasId) {
		return getValueByName(saasId, CmsConf.adminEmail);
	}

	public String getAdminPhone(Long saasId) {
		return getValueByName(saasId, CmsConf.adminPhone);
	}

	public String getValueByName(Long saasId, String name) {
		OptionBO backendTheme = getByName(saasId, name);
		if (backendTheme != null) {
			return backendTheme.getValue();
		}
		return null;
	}

	public boolean isCollectRight() {
		String value = getValueByName(null, Switch.autoCollectRightSwitch);
		return "1".equals(value) || "True".equalsIgnoreCase(value) || "是".equalsIgnoreCase(value);
	}

	public boolean authorityOpen(Long saasId) {
		String value = getValueByName(saasId, Switch.authoritySwitch);
		return !"2".equals(value);
	}

	public String getWebsiteTitle(Long saasId) {
		return getValueByName(saasId, CmsConf.websiteTitle);
	}

	public Integer getWaitPayOrderAutoCloseTime(Long saasId) {
		String value = getValueByName(saasId, "wait_pay_order_auto_close_time");
		if (value == null || "".equals(value)) {
			return 60 * 60 * 24 * 3;// 默认三天才关闭订单
		}
		return Integer.parseInt(value);
	}

	public boolean compareAndSet(Long id, String expectValue, String value) {
		return false;
	}

	public boolean isSysLog() {
		String value = getValueByName(null, Switch.systemLogSwitch);
		return "1".equals(value);
	}

	public void closeAuthority(Long saasId) {
		OptionBO option = getByName(saasId, Switch.authoritySwitch);
		option.setValue("2");
		update(option);
	}

	public void openAuthority(Long saasId) {
		OptionBO option = getByName(saasId, Switch.authoritySwitch);
		option.setValue("1");
		update(option);
	}

	public int delete(Object primary) {
		OptionBO option = get(primary);
		int result = super.delete(primary);
		nameValueCache.invalidate(option.getSaasId() + ";" + option.getName());
		return result;
	}

	public boolean dbMonitorOpen() {
		String value = getValueByName(-1l, Switch.dbMonitorSwitch);
		return "1".equals(value) || value == null;
	}

	public boolean domainMonitorOpen() {
		String value = getValueByName(-1l, Switch.domainMonitorSwitch);
		return "1".equals(value) || value == null;
	}

	public boolean taskOpen() {
		String value = getValueByName(-1l, Switch.taskRunnerSwitch);
		return "1".equals(value);
	}

}
