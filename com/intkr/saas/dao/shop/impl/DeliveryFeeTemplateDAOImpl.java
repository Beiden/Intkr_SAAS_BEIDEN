package com.intkr.saas.dao.shop.impl;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAOImpl;
import com.intkr.saas.dao.shop.DeliveryFeeTemplateDAO;
import com.intkr.saas.domain.dbo.shop.DeliveryFeeTemplateDO;

/**
 * 运费模版
 * 
 * @table delivery_fee_template
 * 
 * @author Beiden
 * @date 2021-01-18 18:32:16
 * @version 1.0
 */
@Repository("DeliveryFeeTemplateDAO")
public class DeliveryFeeTemplateDAOImpl extends BaseDAOImpl<DeliveryFeeTemplateDO> implements DeliveryFeeTemplateDAO {

	public String getNameSpace() {
		return "deliveryFeeTemplate";
	}

	private String dataSourceName = "cms";

	public String getDataSourceName() {
		return dataSourceName;
	}

}
