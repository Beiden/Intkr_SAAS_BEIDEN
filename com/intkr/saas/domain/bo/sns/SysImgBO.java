package com.intkr.saas.domain.bo.sns;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * @author Beiden
 * @date 2016-5-26 下午2:21:55
 * @version 1.0
 */
public class SysImgBO extends BaseBO {

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
