package com.intkr.saas.manager;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.domain.BaseDO;
import com.intkr.saas.domain.Transfer;
import com.intkr.saas.util.MapUtil;
import com.intkr.saas.util.db.SqlResultDO;

/**
 * 
 * 
 * @author beidenhuang
 * @datetime 2011-10-20 下午04:44:52
 * @version 1.0
 */
public abstract class BaseManagerImpl<BO extends BaseBO, DO extends BaseDO> implements BaseManager<BO, DO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public abstract BaseDAO<DO> getBaseDAO();

	public long getId() {
		return getBaseDAO().getId();
	}

	public long insert(BO object) {
		try {
			if (object == null) {
				return 0;
			}
			DO dto = (DO) Transfer.toDOByField(object);
			return getBaseDAO().insert(dto);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":object=" + MapUtil.toMap(object), e);
			throw new RuntimeException(e);
		}
	}

	public long insert(List<BO> boList) {
		if (boList == null || boList.isEmpty()) {
			return 0;
		}
		int pageSize = 500;// 分页处理
		if (boList.size() > pageSize) {
			int page = 1;
			int fromIndex = 0;
			int toIndex = 0;
			long result = 0;
			while (toIndex < boList.size()) {
				fromIndex = (page - 1) * pageSize;
				toIndex = page * pageSize > boList.size() ? boList.size() : page * pageSize;
				List<BO> subBoList = boList.subList(fromIndex, toIndex);
				result += insertReal(subBoList);
				page++;
			}
			return result;
		} else {
			return insertReal(boList);
		}
	}

	public long insertReal(List<BO> boList) {
		try {
			if (boList == null || boList.isEmpty()) {
				return 0;
			}
			boolean hasIdPre = boList.get(0).getId() != null;// 是否预先分配了ID
			List<DO> dtoList = Transfer.toDOListByField(boList);
			long result = getBaseDAO().insert(dtoList);
			boolean hasIdNext = dtoList.get(0).getId() != null;
			if (!hasIdPre && hasIdNext) {// 是否数据库自增的ID,返回ID值
				for (int index = 0; index < boList.size(); index++) {
					long id = dtoList.get(index).getId();
					boList.get(index).setId(id);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":boList=" + boList.size(), e);
			throw new RuntimeException(e);
		}
	}

	public int delete(Object primary) {
		try {
			if (primary == null) {
				return 0;
			}
			return getBaseDAO().delete(primary);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":object=" + MapUtil.toMap(primary), e);
			throw new RuntimeException(e);
		}
	}

	public void deleteAll() {
		getBaseDAO().deleteAll();
	}

	public int deleteAll(List<Object> ids) {
		try {
			if (ids == null || ids.isEmpty()) {
				return 0;
			}
			return getBaseDAO().deleteAll(ids);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":object=" + MapUtil.toMap(ids), e);
			throw new RuntimeException(e);
		}
	}

	public int update(BO object) {
		try {
			DO dto = (DO) Transfer.toDOByField(object);
			return getBaseDAO().update((DO) dto);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":object=" + MapUtil.toMap(object), e);
			throw new RuntimeException(e);
		}
	}

	public BO get(Object primary) {
		try {
			if (primary == null || "".equals(primary)) {
				return null;
			}
			DO dto = getBaseDAO().get(primary);
			BO bo = (BO) Transfer.toBOByField(dto);
			return bo;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + ":primary=" + MapUtil.toMap(primary), e);
			throw new RuntimeException(e);
		}
	}

	public List<BO> select() {
		try {
			List<DO> dtoList = getBaseDAO().select();
			List<BO> boList = Transfer.toBOListByField(dtoList);
			return boList;
		} catch (Exception e) {
			logger.error(this.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

	public List<BO> select(BO object) {
		try {
			DO dto = (DO) Transfer.toDOByField(object);
			List<DO> dtoList = getBaseDAO().select(dto);
			List<BO> boList = Transfer.toBOListByField(dtoList);
			object.setDatas(boList);
			return boList;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "object=" + MapUtil.toMap(object), e);
			throw new RuntimeException(e);
		}
	}

	public BO selectOne(BO object) {
		try {
			DO dto = (DO) Transfer.toDOByField(object);
			DO returnDto = getBaseDAO().selectOne(dto);
			return Transfer.toBOByField(returnDto);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "object=" + MapUtil.toMap(object), e);
			throw new RuntimeException(e);
		}
	}

	public long count(BO object) {
		try {
			DO dto = (DO) Transfer.toDOByField(object);
			Long count = getBaseDAO().count(dto);
			return count;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "object=" + MapUtil.toMap(object), e);
			throw new RuntimeException(e);
		}
	}

	public boolean exist(BO object) {
		return count(object) > 0;
	}

	public BO selectCount(BO query) {
		return selectAndCount(query);
	}

	public BO selectAndCount(BO query) {
		try {
			DO dto = (DO) Transfer.toDOByField(query);
			List<DO> dtoList = getBaseDAO().select(dto);
			query.set_count(getBaseDAO().count(dto));
			List<BO> boList = Transfer.toBOListByField(dtoList);
			query.setDatas(boList);
			return query;
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "query=" + MapUtil.toMap(query), e);
			throw new RuntimeException(e);
		}
	}

	public List<BO> selectAll() {
		try {
			BaseBO query = new BaseBO();
			query.set_pageSize(100000);
			query.setQuery("orderBy", "gmt_created");
			query.setQuery("order", "desc");
			DO dto = (DO) Transfer.toDOByField(query);
			List<DO> dtoList = getBaseDAO().select(dto);
			query.set_count(getBaseDAO().count(dto));
			List<BO> boList = Transfer.toBOListByField(dtoList);
			query.setDatas(boList);
			return boList;
		} catch (Exception e) {
			logger.error(this.getClass().getName(), e);
			throw new RuntimeException(e);
		}
	}

	public SqlResultDO executeSql(String sql) {
		return getBaseDAO().executeSql(sql);
	}

	public SqlResultDO executeSql(String sql, List<Object> datas) {
		return getBaseDAO().executeSql(sql, datas);
	}

}
