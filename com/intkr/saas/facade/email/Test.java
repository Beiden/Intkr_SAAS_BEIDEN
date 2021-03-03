package com.intkr.saas.facade.email;

import com.intkr.saas.util.FileUtil;

public class Test {

	public static void main(String[] args) {
		String title = "拼背景不如比创意 小贝商城2500大洋寻中国好设计";
		String content = FileUtil.getContent("D:/content.txt");

		SendObj sendObj = new SendObj();
		sendObj.setUsername("beiden@163.com");
		sendObj.setPassword("");
		sendObj.setTo("443818603@qq.com");
		sendObj.setSubject(title);
		sendObj.setContent(content);
		new Mail(sendObj).send();
	}

}
