package com.intkr.saas.module.action.fs;

import com.intkr.saas.domain.type.fs.MediaType;
import com.intkr.saas.module.screen.common.UploadConf;

/**
 * 
 * @author Beiden
 * @date 2015-10-2 下午7:09:54
 * @version 1.0
 */
public class MediaUploadAction extends BaseMediaUploadAction {

	private UploadConf conf = new UploadConf();
	{
		conf.setUploadPath("/data/media/");
		conf.setMediaType(MediaType.Attach.getCode());
	}

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

}
