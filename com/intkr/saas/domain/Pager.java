package com.intkr.saas.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Beiden
 * @date 2011-7-19 下午7:54:32
 * @version 1.0
 */
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer _page = 1;

	private Integer _offset = 0;

	private Integer _pageSize = 20;

	private Long _count = 0l;

	private List<T> datas;

	private Map<String, Object> query;

	public void set_page(Integer page) {
		this._page = page;
		caculateOffset();
	}

	private void caculateOffset() {
		if (_page == null || _pageSize == null) {
			return;
		}
		_offset = _pageSize * (_page - 1);
	}

	public void set_pageSize(Integer pageSize) {
		this._pageSize = pageSize;
		caculateOffset();
	}

	public Long get_Count() {
		return _count;
	}

	public void set_Count(Long count) {
		this._count = count;
	}

	/**
	 * 当前页页码
	 * 
	 * @return
	 */
	public Integer getCurrentPage() {
		return this._page;
	}

	/**
	 * 上一页页码
	 * 
	 * @return
	 */
	public Integer getPrePage() {
		if (this._page > 1) {
			return this._page - 1;
		} else {
			return 1;
		}
	}

	/**
	 * 下一页页码
	 * 
	 * @return
	 */
	public Integer getNextPage() {
		if (this._page < getAllPage()) {
			return this._page + 1;
		} else {
			return this._page;
		}
	}

	/**
	 * 获得总页数
	 * 
	 * @return
	 */
	public Integer getAllPage() {
		if (_count % _pageSize == 0) {
			Long allPage = _count / _pageSize;
			return allPage.intValue();
		} else {
			Long allPage = _count / _pageSize;
			return allPage.intValue() + 1;
		}
	}

	public Integer get_offset() {
		return _offset;
	}

	public void set_offset(Integer _offset) {
		this._offset = _offset;
	}

	public Long get_count() {
		return _count;
	}

	public void set_count(Long _count) {
		this._count = _count;
	}

	public void set_count(Integer _count) {
		if (_count != null) {
			this._count = _count.longValue();
		}
	}

	public Integer get_page() {
		return _page;
	}

	public Integer get_pageSize() {
		return _pageSize;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public Map<String, Object> getQuery() {
		initQuery();
		return query;
	}

	public Object getQuery(String key) {
		initQuery();
		return query.get(key);
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	public void setQuery(String key, Object value) {
		if (query == null) {
			initQuery();
		}
		query.put(key, value);
	}

	private void initQuery() {
		if (query == null) {
			query = new HashMap<String, Object>();
		}
	}

}
