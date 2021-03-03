package com.intkr.saas.distributed.search.client;

import com.intkr.saas.domain.bo.fs.ImgBO;

/**
 * 
 * @author Beiden
 */
public interface ImgSearchClient {

	public void fullDump(Long saasId);

	public ImgBO search(ImgBO imgQuery);

	public void dump(Long imgId);

	public void dump(ImgBO img);

	public void delete(Long imgId);

}
