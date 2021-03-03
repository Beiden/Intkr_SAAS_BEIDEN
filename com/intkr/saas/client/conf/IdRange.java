package com.intkr.saas.client.conf;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @author Beiden
 * @date 2017-7-14 上午9:02:10
 * @version 1.0
 */
public class IdRange {

	private Long id;

	private String tableName;

	private AtomicLong now = new AtomicLong(0);

	private Long start;

	private Long end;

	public AtomicLong getNow() {
		return now;
	}

	public void setNow(AtomicLong now) {
		this.now = now;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
