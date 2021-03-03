package com.intkr.saas.domain.bo.user;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2011-11-16 下午4:17:54
 * @version 1.0
 */
public class RoleBO extends BaseBO {

	private static final long serialVersionUID = 1L;

	// 类型：产品角色，应用实例自定义角色
	private String type;

	private Long saasId;

	private Long pid;

	private String code;

	private String name;

	private String note;

	private Double sort;

	private List<RightBO> rights;

	private List<RoleBO> childs;

	private RoleBO parent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public List<RightBO> getRights() {
		return rights;
	}

	public void setRights(List<RightBO> rights) {
		this.rights = rights;
	}

	public boolean hasRight(String code) {
		if (rights == null || "".equals(code)) {
			return false;
		}
		for (RightBO right : rights) {
			if (right.getCode().equals(code)) {
				return true;
			}
		}
		return false;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public List<RoleBO> getChilds() {
		return childs;
	}

	public void setChilds(List<RoleBO> childs) {
		this.childs = childs;
	}

	public void addChild(RoleBO child) {
		if (this.childs == null) {
			childs = new ArrayList<RoleBO>();
		}
		childs.add(child);
	}

	public RoleBO getParent() {
		return parent;
	}

	public void setParent(RoleBO parent) {
		this.parent = parent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
