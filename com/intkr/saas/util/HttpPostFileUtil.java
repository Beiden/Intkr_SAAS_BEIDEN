package com.intkr.saas.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Beiden
 * @date 2011-5-25 下午7:16:47
 * @version 1.0
 */
public class HttpPostFileUtil {

	public static class Response {
		public Integer code;
		public String body;
	}

	/**
	 * 发送请求
	 * 
	 * @param url
	 *            请求地址
	 * @param filePath
	 *            文件在服务器保存路径（这里是为了自己测试方便而写，可以将该参数去掉）
	 * @return
	 * @throws IOException
	 */
	public static Response send(String url, String filePath) throws IOException {

		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			Response response = new Response();
			response.code = -1;
			return response;
		}

		/**
		 * 第一部分
		 */
		URL urlObj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		/**
		 * 设置关键值
		 */
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存

		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		// 请求正文信息

		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // ////////必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// 获得输出流

		OutputStream out = new DataOutputStream(con.getOutputStream());
		out.write(head);

		// 文件正文部分
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		Response response = new Response();
		response.code = con.getResponseCode();
		response.body = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				response.body += line;
			}
			return response;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
