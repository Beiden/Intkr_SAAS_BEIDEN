package com.intkr.saas.module.action.fs;

import com.intkr.saas.domain.type.fs.MediaType;
import com.intkr.saas.module.screen.common.UploadConf;

/**
 * 
 * @author Beiden
 * @date 2011-10-2 下午7:09:54
 * @version 1.0
 */
public class VideoMediaUploadAction extends BaseMediaUploadAction {

	private UploadConf conf = new UploadConf();
	{
		conf.setUploadPath("/_upload/media/img/");
		conf.setMediaType(MediaType.Vedio.getCode());
	}

	public UploadConf getConf() {
		return conf;
	}

	public void setConf(UploadConf conf) {
		this.conf = conf;
	}

}
