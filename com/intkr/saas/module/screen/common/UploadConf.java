package com.intkr.saas.module.screen.common;

/**
 * 
 * @author Beiden
 * @date 2016-1-6 22:45:03
 * @version 1.0
 */
public class UploadConf {

	// 最大上传文件,单位 B
	private Integer fileMaxSize = 10 * 1024 * 1024;

	// 最大缓存,单位 B
	private Integer SizeThreshold = 5 * 1024 * 1024;

	// 上传位置
	private String uploadPath = "/_upload/media/other/";

	// 文件类型
	private String mediaType = null;

	public Integer getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileMaxSize(Integer fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	public Integer getSizeThreshold() {
		return SizeThreshold;
	}

	public void setSizeThreshold(Integer sizeThreshold) {
		SizeThreshold = sizeThreshold;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

}
