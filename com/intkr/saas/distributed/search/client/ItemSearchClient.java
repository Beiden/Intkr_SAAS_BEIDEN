package com.intkr.saas.distributed.search.client;

import java.util.List;

import com.intkr.saas.domain.bo.item.ItemBO;

/**
 * 
 * @author Beiden
 * 
 */
public interface ItemSearchClient {

	public ItemBO search(ItemBO itemQuery);

	public void dump(Long itemId);

	public void dumpAllById(List<Long> itemIds);

	public void fullDump(Long saasId);

	public void delete(Long itemId);

}
