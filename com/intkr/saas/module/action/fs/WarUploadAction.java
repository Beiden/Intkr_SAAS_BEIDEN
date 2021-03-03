package com.intkr.saas.module.action.fs;

import com.intkr.saas.module.screen.common.UploadConf;

/**
 * 
 * @author Beiden
 * @date 2011-6-10 下午10:26:28
 * @version 1.0
 */
public class WarUploadAction extends BaseMediaUploadAction {

	private UploadConf conf = new UploadConf();
	{
		conf.setUploadPath("/_upload/war/");
		conf.setMediaType("war");
	}

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

}
