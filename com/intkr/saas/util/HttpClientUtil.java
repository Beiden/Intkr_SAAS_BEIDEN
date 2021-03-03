package com.intkr.saas.util;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 
 * @author Beiden
 * 
 * @date 2010-11-11 下午10:10:54
 * @version 1.0
 */
public class HttpClientUtil {

	public static void get2() throws Exception {
		HttpHost proxy = new HttpHost("proxy", 8080);
		BasicScheme proxyAuth = new BasicScheme();
		// Make client believe the challenge came form a proxy
		proxyAuth.processChallenge(new BasicHeader(AUTH.PROXY_AUTH, "BASIC realm=default"));
		BasicAuthCache authCache = new BasicAuthCache();
		authCache.put(proxy, proxyAuth);
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("username", "password"));
		HttpClientContext context = HttpClientContext.create();
		context.setAuthCache(authCache);
		context.setCredentialsProvider(credsProvider);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpclient.execute(new HttpGet("/stuff"), context);
			try {
				// ...
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
	}

	public static void getHttpClient() {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		// 依次是目标请求地址，端口号,协议类型
		HttpHost target = new HttpHost("10.10.100.102:8080/mytest", 8080, "http");
		// 依次是代理地址，代理端口号，协议类型
		HttpHost proxy = new HttpHost("yourproxy", 8080, "http");

		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

		// 请求地址
		HttpPost httpPost = new HttpPost("http://10.10.100.102:8080/mytest");
		httpPost.setConfig(config);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		// 参数名为pid，值是2
		formparams.add(new BasicNameValuePair("pid", "2"));

		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = closeableHttpClient.execute(target, httpPost);
			// getEntity()
			HttpEntity httpEntity = response.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				System.out.println("response:" + EntityUtils.toString(httpEntity, "UTF-8"));
			}
			// 释放资源
			closeableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getText(String url) {
		HttpClient client = new HttpClient();
		GetMethod httpGet = new GetMethod(url);
		try {
			client.executeMethod(httpGet);
			return httpGet.getResponseBodyAsString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			httpGet.releaseConnection();
		}
	}

	public static String getText(String url, String chartSet) {
		HttpClient client = new HttpClient();
		GetMethod httpGet = new GetMethod(url);
		try {
			HttpMethodParams params = httpGet.getParams();
			params.setContentCharset(chartSet);
			httpGet.addRequestHeader("Content-Type", "text/html;charset=" + chartSet);
			client.executeMethod(httpGet);
			return httpGet.getResponseBodyAsString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			httpGet.releaseConnection();
		}
	}

	public static String post(String url, Map<String, String> param) {
		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		try {
			if (param != null) {
				for (String key : param.keySet()) {
					httpPost.setParameter(key, param.get(key));
				}
			}
			client.executeMethod(httpPost);
			return httpPost.getResponseBodyAsString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			httpPost.releaseConnection();
		}
	}

	public static String getContentByGet(String url) {
		return getText(url);
	}

	static X509TrustManager xtm = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	};

	public static String postJson(String apiUrl, Map<String, String> headers, String json) {
		CloseableHttpResponse response = null;
		String httpStr = null;
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[] { xtm }, null);
			SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
			RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).setExpectContinueEnabled(true).setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST)).setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC)).build();
			Registry<ConnectionSocketFactory> sfr = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", scsf).build();
			PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager(sfr);
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(pcm).setDefaultRequestConfig(defaultConfig).build();
			HttpPost httpPost = new HttpPost(apiUrl);
			if (headers != null) {
				for (String key : headers.keySet()) {
					httpPost.setHeader(key, headers.get(key));
				}
			}
			httpPost.setConfig(defaultConfig);
			StringEntity stringEntity = new StringEntity(json, "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			if (e.toString().contains("Connection timed out")) {
				return postJson(apiUrl, headers, json);
			}
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

}
