package com.intkr.saas.domain.dbo.ad;

import com.intkr.saas.domain.BaseDO;

/**
 * 广告位
 * 
 * @table advertisement_position
 * 
 * @author Beiden
 * @date 2020-11-04 09:37:22
 * @version 1.0
 */
public class AdvertisementPositionDO extends BaseDO<AdvertisementPositionDO> {

	private static final long serialVersionUID = 1L;

	// Saas
	private Long saasId;

	// type
	private String type;

	// 
	private String name;

	// 
	private String feature;
	
	private String note;

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

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

}
