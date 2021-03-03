package com.intkr.saas.facade.ip.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.intkr.saas.facade.ip.IpClient;
import com.intkr.saas.util.HttpClientUtil;
import com.intkr.saas.util.JsonUtil;

/**
 * 
 * @author Beiden
 * @date 2015-12-10 20:11:21
 * @version 1.0
 */
@Repository("IpClient")
public class IpClientTaobaoImpl implements IpClient {

	public IpClient.Response getInfo(String ip) {
		if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			IpClient.Response response = new IpClient.Response();
			response.result = true;
			response.county = "未分配或者内网IP";
			return response;
		}
		String jsonString = HttpClientUtil.getText("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
		Map<String, Object> map = JsonUtil.toObject(jsonString, Map.class);
		if (map.get("code").equals(0D)) {
			IpClient.Response response = new IpClient.Response();
			Map<String, Object> data = (Map<String, Object>) map.get("data");
			response.result = true;
			response.country = (String) data.get("country");
			response.countryId = (String) data.get("country_id");
			response.area = (String) data.get("area");
			response.areaId = (String) data.get("area_id");
			response.region = (String) data.get("region");
			response.regionId = (String) data.get("region_id");
			response.city = (String) data.get("city");
			response.cityId = (String) data.get("city_id");
			response.county = (String) data.get("county");
			response.countyId = (String) data.get("county_id");
			response.isp = (String) data.get("isp");
			response.ispId = (String) data.get("isp_id");
			response.ip = (String) data.get("ip");
			return response;
		} else {
			return new IpClient.Response();
		}
	}

	public static void main(String[] args) {
		IpClient.Response respnose = new IpClientTaobaoImpl().getInfo("218.107.55.254");
		System.out.println(JsonUtil.toJson(respnose));
	}

}
