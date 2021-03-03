package com.intkr.saas.manager.shop.impl;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.manager.BaseManagerImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.shop.DeliveryFeeTemplateDAO;
import com.intkr.saas.domain.bo.shop.DeliveryFeeTemplateBO;
import com.intkr.saas.domain.dbo.shop.DeliveryFeeTemplateDO;
import com.intkr.saas.manager.shop.DeliveryFeeTemplateManager;

/**
 * 运费模版
 * 
 * @table delivery_fee_template
 * 
 * @author Beiden
 * @date 2021-01-18 18:32:16
 * @version 1.0
 */
@Repository("DeliveryFeeTemplateManager")
public class DeliveryFeeTemplateManagerImpl extends BaseManagerImpl<DeliveryFeeTemplateBO, DeliveryFeeTemplateDO> implements DeliveryFeeTemplateManager {

	@Resource
	private DeliveryFeeTemplateDAO deliveryFeeTemplateDAO;

	public BaseDAO<DeliveryFeeTemplateDO> getBaseDAO() {
		return deliveryFeeTemplateDAO;
	}

}
