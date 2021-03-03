package com.intkr.saas.facade.ip;

import java.io.Serializable;

/**
 * 
 * @author Beiden
 * @date 2017-11-18
 * @version 1.0
 */
public interface IpClient {

	public IpClient.Response getInfo(String ip);

	public static class Response implements Serializable {
		private static final long serialVersionUID = 1L;
		public boolean result = false;
		public String country; // 国家
		public String countryId;
		public String area; // 区域
		public String areaId;
		public String region;// 地区
		public String regionId;
		public String city; // 城市
		public String cityId;
		public String county;
		public String countyId;
		public String isp; // 服务提供商
		public String ispId;
		public String ip; // ip地址
	}

}
