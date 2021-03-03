package com.intkr.saas.domain.bo.menu;

import java.util.ArrayList;
import java.util.List;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2011-9-30 下午7:41:02
 * @version 1.0
 */
public class FrontMenuBO extends BaseBO {

	private Long saasId;

	// 名称
	private String name;

	// 拓展字段
	private String feature;

	// 备注
	private String note;

	private List<FrontMenuDetailBO> details;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FrontMenuDetailBO> getDetails() {
		return details;
	}

	public void addDetail(FrontMenuDetailBO frontMenuDetailBO) {
		if (this.details == null) {
			this.details = new ArrayList<FrontMenuDetailBO>();
		}
		this.details.add(frontMenuDetailBO);
	}

	public void setDetails(List<FrontMenuDetailBO> details) {
		this.details = details;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}
