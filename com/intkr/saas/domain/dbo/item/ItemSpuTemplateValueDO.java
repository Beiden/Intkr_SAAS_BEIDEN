package com.intkr.saas.domain.dbo.item;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2017-08-03 16:44:58
 * @version 1.0
 */
public class ItemSpuTemplateValueDO extends BaseDO {

	private Long saasId;

	// 类目属性ID
	private Long spuTemplateId;

	// 属性选项ID
	private Long valueId;

	public Long getSpuTemplateId() {
		return spuTemplateId;
	}

	public void setSpuTemplateId(Long spuTemplateId) {
		this.spuTemplateId = spuTemplateId;
	}

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public Long getSaasId() {
		return saasId;
	}

	public void setSaasId(Long saasId) {
		this.saasId = saasId;
	}

}