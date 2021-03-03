package com.intkr.saas.module.toolbox.util;

import com.intkr.saas.module.toolbox.BaseToolBox;

public class Util extends BaseToolBox {

	public static String getSummary(String content, Integer length) {
		if (content.length() < length) {
			return content;
		} else {
			return content.substring(0, length);
		}
	}

}
