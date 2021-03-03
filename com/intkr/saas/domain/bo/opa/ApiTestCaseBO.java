package com.intkr.saas.domain.bo.opa;

import com.intkr.saas.domain.BaseBO;

/**
 * 
 * 
 * @table api_test_case_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:33
 * @version 1.0
 */
public class ApiTestCaseBO extends BaseBO<ApiTestCaseBO> {

	private static final long serialVersionUID = 1L;

	// api_id
	private Long apiId;

	// target_api_id
	private Long targetApiId;

	// name
	private String name;

	// api_method
	private String apiMethod;

	// uri
	private String uri;

	// 
	private String preReq;

	// 
	private String req;

	// 
	private String rsp;

	// 
	private String result;

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public Long getTargetApiId() {
		return targetApiId;
	}

	public void setTargetApiId(Long targetApiId) {
		this.targetApiId = targetApiId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiMethod() {
		return apiMethod;
	}

	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPreReq() {
		return preReq;
	}

	public void setPreReq(String preReq) {
		this.preReq = preReq;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getRsp() {
		return rsp;
	}

	public void setRsp(String rsp) {
		this.rsp = rsp;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
