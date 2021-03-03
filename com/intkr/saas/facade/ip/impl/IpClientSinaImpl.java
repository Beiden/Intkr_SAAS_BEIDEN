package com.intkr.saas.facade.ip.impl;

import java.util.Map;

import com.intkr.saas.util.HttpClientUtil;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2015-12-10 20:11:21
 * @version 1.0
 */
public class IpClientSinaImpl {

	public static class Response {
		public boolean result = false;
		public Double ret;
		public Double start;
		public Double end;
		public String country;// 国家
		public String province;// 省份
		public String city; // 城市
		public String district;
		public String isp;// 服务提供商
		public String type;
		public String desc;
	}

	public Response getInfo(String ip) {
		String jsonString = HttpClientUtil.getText("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + ip);
		if (jsonString.length() > 21) {
			jsonString = jsonString.substring(21, jsonString.length() - 1);
			Map<String, Object> map = JsonUtil.toObject(jsonString, Map.class);
			Response response = new Response();
			response.result = true;
			response.ret = (Double) map.get("ret");
			response.start = (Double) map.get("start");
			response.end = (Double) map.get("end");
			response.country = (String) map.get("country");
			response.province = (String) map.get("province");
			response.city = (String) map.get("city");
			response.district = (String) map.get("district");
			response.isp = (String) map.get("isp");
			response.type = (String) map.get("type");
			response.desc = (String) map.get("desc");
			return response;
		} else {
			return new Response();
		}
	}

	public static void main(String[] args) {
		Response respnose = new IpClientSinaImpl().getInfo("218.107.55.254");
		System.out.println(JsonUtil.toJson(respnose));
	}

}
