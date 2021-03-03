package com.intkr.saas.manager.item.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.client.conf.IdEngine;
import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.item.ItemPropertyDAO;
import com.intkr.saas.domain.bo.item.ItemPropertyBO;
import com.intkr.saas.domain.bo.item.ItemSpuTemplateBO;
import com.intkr.saas.domain.dbo.item.ItemPropertyDO;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.item.ItemPropertyManager;

/**
 * 
 * @author Beiden
 * @date 2017-07-21 15:55:37
 * @version 1.0
 */
@Repository("ItemPropertyManager")
public class ItemPropertyManagerImpl extends BaseManagerImpl<ItemPropertyBO, ItemPropertyDO> implements ItemPropertyManager {

	@Resource
	private ItemPropertyDAO shopPropertyDAO;

	public BaseDAO<ItemPropertyDO> getBaseDAO() {
		return shopPropertyDAO;
	}

	public List<?> fill(List<?> list) {
		if (list == null) {
			return new ArrayList<Object>();
		}
		for (Object obj : list) {
			if (obj instanceof ItemSpuTemplateBO) {
				fill((ItemSpuTemplateBO) obj);
			}
		}
		return list;
	}

	public ItemSpuTemplateBO fill(ItemSpuTemplateBO spuTemplate) {
		if (spuTemplate == null || !IdEngine.isValidate(spuTemplate.getPropertyId())) {
			return null;
		}
		spuTemplate.setProperty(get(spuTemplate.getPropertyId()));
		return spuTemplate;
	}
}
