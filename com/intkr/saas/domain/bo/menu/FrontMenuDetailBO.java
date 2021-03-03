package com.intkr.saas.domain.bo.menu;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午8:10:36
 * @version 1.0
 */
public class FrontMenuDetailBO extends BaseBO {

	private Long saasId;

	// 菜单ID
	private Long frontMenuId;

	// 层级
	private Integer level;

	// 父id
	private Long pid;

	// 类型
	private String type;

	// 名称
	private String name;

	// url
	private String url;

	// 标题
	private String title;

	// 相关ID
	private Long relatedId;

	// 权重
	private Double sort;

	private FrontMenuDetailBO parent;

	private List<FrontMenuDetailBO> childs;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Long getFrontMenuId() {
		return frontMenuId;
	}

	public void setFrontMenuId(Long frontMenuId) {
		this.frontMenuId = frontMenuId;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public FrontMenuDetailBO getParent() {
		return parent;
	}

	public void setParent(FrontMenuDetailBO parent) {
		this.parent = parent;
	}

	public List<FrontMenuDetailBO> getChilds() {
		return childs;
	}

	public void setChilds(List<FrontMenuDetailBO> childs) {
		this.childs = childs;
	}

	public void addChild(FrontMenuDetailBO child) {
		if (this.childs == null) {
			this.childs = new ArrayList<FrontMenuDetailBO>();
		}
		this.childs.add(child);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

	public Integer calculateLevel() {
		FrontMenuDetailBO parentTmp = parent;
		int level = 1;
		while (parentTmp != null) {
			level++;
			parentTmp = parentTmp.getParent();
		}
		return level;
	}

}
