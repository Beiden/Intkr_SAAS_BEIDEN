package com.intkr.saas.domain.dbo.opa;

import com.intkr.saas.domain.BaseDO;

/**
 * 
 * 
 * @table api_invoke_log_tab
 * 
 * @author Beiden
 * @date 2020-07-23 22:57:32
 * @version 1.0
 */
public class ApiInvokeLogDO extends BaseDO<ApiInvokeLogDO> {

	private static final long serialVersionUID = 1L;

	// api_id
	private Long apiId;

	// api_method
	private String apiMethod;

	// name
	private String name;

	// type
	private String type;

	// req
	private String req;

	// rsp
	private String rsp;

	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public String getApiMethod() {
		return apiMethod;
	}

	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
