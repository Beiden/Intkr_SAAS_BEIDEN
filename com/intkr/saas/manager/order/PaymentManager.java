package com.intkr.saas.manager.order;

import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.domain.dbo.order.PaymentDO;
import com.intkr.saas.manager.BaseManager;

/**
 * 
 * @author Beiden
 * @date 2011-4-29 下午9:18:11
 * @version 1.0
 */
public interface PaymentManager extends BaseManager<PaymentBO, PaymentDO> {

	public PaymentBO getYuePay(Long saasId);

	public PaymentBO getAlipay(Long saasId);

	public PaymentBO getJdpay(Long saasId);

	public PaymentBO getWxpay(Long saasId);

	public PaymentBO getOffLinePay(Long saasId);

	public PaymentBO getBankPay(Long saasId);

	public PaymentBO getByType(Long saasId, String type);

}
