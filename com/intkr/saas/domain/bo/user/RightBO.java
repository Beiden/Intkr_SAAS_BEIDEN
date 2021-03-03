package com.intkr.saas.domain.bo.user;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:25:40
 * @version 1.0
 */
public class RightBO extends BaseBO {

	private static final long serialVersionUID = 1L;

	private String sys;

	private String code;

	private String type;

	private String name;

	private Long pid;

	private String note;
	
	private Double sort;

	private RightBO parent;

	private List<RightBO> childs;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public RightBO getParent() {
		return parent;
	}

	public void setParent(RightBO parent) {
		this.parent = parent;
	}

	public List<RightBO> getChilds() {
		return childs;
	}

	public void setChilds(List<RightBO> childs) {
		this.childs = childs;
	}

	public void addChild(RightBO child) {
		if (this.childs == null) {
			this.childs = new ArrayList<RightBO>();
		}
		this.childs.add(child);
	}

	public String toString() {
		return code;
	}

	public String getSys() {
		return sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

}
