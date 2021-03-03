package com.intkr.saas.domain.dbo.opa;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @table api_ext_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiExtDO extends BaseDO<ApiExtDO> {

	private static final long serialVersionUID = 1L;

	// api_id
	private Long apiId;

	// 
	private String responseExample;

	// 
	private String otherContent;

	// 
	private String responseType;

	// 
	private String preRequestScript;

	// 
	private String other;

	// 
	private String otherType;

	// 
	private String header;

	// 
	private String body;

	// 
	private String note;

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public String getResponseExample() {
		return responseExample;
	}

	public void setResponseExample(String responseExample) {
		this.responseExample = responseExample;
	}

	public String getOtherContent() {
		return otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getPreRequestScript() {
		return preRequestScript;
	}

	public void setPreRequestScript(String preRequestScript) {
		this.preRequestScript = preRequestScript;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getOtherType() {
		return otherType;
	}

	public void setOtherType(String otherType) {
		this.otherType = otherType;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
