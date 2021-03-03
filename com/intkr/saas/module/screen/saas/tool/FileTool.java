package com.intkr.saas.module.screen.saas.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.util.RequestUtil;

/**
 * 
 * @author Beiden
 * @date 2011-6-5 上午8:10:20
 * @version 1.0
 */
public class FileTool {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
//			if (RequestUtil.existParam(request, "fileName") && RequestUtil.existParam(request, "content")) {
//				String fileName = RequestUtil.getParam(request, "fileName");
//				write(request, fileName);
//				request.setAttribute("msg", "修改成功");
//			}
//			if (RequestUtil.existParam(request, "operType") && RequestUtil.getParam(request, "operType").contains("r") && RequestUtil.existParam(request, "fileName")) {
//				List<String> contents = read(request);
//				request.setAttribute("contents", contents);
//			}
		} catch (Exception e) {
			logger.error("", e);
			request.setAttribute("msg", e.getMessage());
		}
	}

	private void write(HttpServletRequest request, String fileName) {
		String content = RequestUtil.getParam(request, "content");
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(new File(fileName)));
			String[] contents = content.split("\r\n");
			for (String string : contents) {
				fw.write(string);
				fw.newLine();
			}
			fw.close();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	private List<String> read(HttpServletRequest request) {
		try {
			String fileName = RequestUtil.getParam(request, "fileName");
			BufferedReader fr = new BufferedReader(new FileReader(new File(fileName)));
			List<String> contentList = new ArrayList<String>();
			String temString = null;
			while ((temString = fr.readLine()) != null) {
				contentList.add(temString);
			}
			fr.close();
			return contentList;
		} catch (Exception e) {
			logger.error("", e);
			List<String> contentList = new ArrayList<String>();
			contentList.add(e.getMessage());
			return contentList;
		}
	}
}
