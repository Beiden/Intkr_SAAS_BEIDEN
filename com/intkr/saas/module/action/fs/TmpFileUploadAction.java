package com.intkr.saas.module.action.fs;

import com.intkr.saas.domain.type.fs.MediaType;
import com.intkr.saas.module.screen.common.UploadConf;

/**
 * 
 * @author Beiden
 * @date 2011-5-14 下午6:16:51
 * @version 1.0
 */
public class TmpFileUploadAction extends BaseMediaUploadAction {

	private UploadConf conf = new UploadConf();
	{
		conf.setUploadPath("/_upload/tmp/");
		conf.setMediaType(MediaType.File.getCode());
	}

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

}
