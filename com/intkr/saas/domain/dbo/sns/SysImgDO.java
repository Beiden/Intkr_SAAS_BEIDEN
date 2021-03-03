package com.intkr.saas.domain.dbo.sns;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * @author Beiden
 * @date 2016-5-26 下午2:22:23
 * @version 1.0
 */
public class SysImgDO extends BaseDO {

	private String type;

	private String uri;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
