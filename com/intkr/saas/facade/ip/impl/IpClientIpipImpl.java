package com.intkr.saas.facade.ip.impl;

import java.util.List;

import com.intkr.saas.util.HttpClientUtil;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2015-12-10 20:11:21
 * @version 1.0
 */
public class IpClientIpipImpl {

	public static class Response {
		public boolean result = false;
		public String country; // 国家
		public String provice; // 省分
		public String city; // 城市
		public String isp; // 服务提供商
	}

	public Response getInfo(String ip) {
		String jsonString = HttpClientUtil.getText("http://freeapi.ipip.net/" + ip);
		if (jsonString.length() > 3) {
			List<String> list = JsonUtil.toObject(jsonString, List.class);
			Response response = new Response();
			response.result = true;
			response.country = list.get(0);
			response.provice = list.get(1);
			response.city = list.get(2);
			response.isp = list.get(4);
			return response;
		} else {
			return new Response();
		}
	}

	public static void main(String[] args) {
		Response respnose = new IpClientIpipImpl().getInfo("218.107.55.254");
		System.out.println(JsonUtil.toJson(respnose));
	}

}
