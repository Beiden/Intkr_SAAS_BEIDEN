package com.intkr.saas.facade.sms;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.JsonUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 官网： https://luosimao.com
 * 
 * @author Beiden
 * @date 2015-7-17 下午8:40:02
 * @version 1.0
 */
public class SmsFacadeLuosimaoImpl {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public boolean send(String api, String phone, String message) {
		if (message == null || "".equals(message)) {
			return false;
		}
		if (phone == null || "".equals(phone) || phone.length() != 11) {
			return false;
		}
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", api));
		WebResource webResource = client.resource("http://sms-api.luosimao.com/v1/send.json");
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		formData.add("mobile", phone);
		formData.add("message", message);
		ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
		String textEntity = response.getEntity(String.class);
		logger.info("phone=" + phone + ";message=" + message + ";result=" + textEntity);
		Map<String, Object> map = JsonUtil.toObject(textEntity, Map.class);
		Double error = (Double) map.get("error");
		if (error == 0D) {
			return true;
		}
		throw new RuntimeException(map.toString());
	}

	public static void main(String[] args) {
		boolean result = new SmsFacadeLuosimaoImpl().send("key-f892aee3e02049481461d003d26dae7e", "18368864688", "您的手机验证码为：123456，验证码30分钟内有效，请勿泄露。【银科软件】");
		System.out.println(result);
	}

}
