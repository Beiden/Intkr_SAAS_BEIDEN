package com.intkr.saas.util;

import java.io.File;

/**
 * 
 * @author Beiden
 * @date 2019-1-15
 * @version 1.0
 */
public class TomcatUtil {

	/**
	 * 获取当前运行程序的Tomcat根目录
	 * 
	 * @return
	 */
	public static File getTomcat() {
		File file = new File("text.html");
		file = file.getAbsoluteFile().getParentFile();// bin的父文件夹目录
		File webapps = FileUtil.findSubDirectory(file, "webapps");
		File serverXml = FileUtil.findSubDirectory(file, "bin");
		if (webapps == null && serverXml == null) {
			file = file.getAbsoluteFile().getParentFile();
			webapps = FileUtil.findSubDirectory(file, "webapps");
			serverXml = FileUtil.findSubDirectory(file, "bin");
		}
		if (webapps != null && serverXml != null) {
			return file;
		} else {
			return null;
		}
	}

	/**
	 * 获取Tomcat ROOT工程的目录
	 * 
	 * @return
	 */
	public static File getROOT() {
		File tomcat = getTomcat();
		if (tomcat == null) {
			return null;
		}
		return new File(tomcat.getAbsolutePath() + File.separator + "webapps/ROOT");
	}

	/**
	 * 获得Tomcat webapps目录
	 * 
	 * @return
	 */
	public static File getWebapps() {
		File file = new File("text.html");
		file = file.getAbsoluteFile().getParentFile();// bin的父文件夹目录
		File webapps = FileUtil.findDirectory(file, "webapps", 1);
		if (webapps == null) {
			file = file.getAbsoluteFile().getParentFile();
			webapps = FileUtil.findDirectory(file, "webapps", 1);
		}
		return webapps;
	}

	public static void main(String[] args) {
		System.out.println(getWebapps());
	}

}
