package com.intkr.saas.manager.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.intkr.saas.dao.BaseDAO;
import com.intkr.saas.dao.order.PaymentDAO;
import com.intkr.saas.domain.bo.order.PaymentBO;
import com.intkr.saas.domain.dbo.order.PaymentDO;
import com.intkr.saas.domain.type.user.MoneyAccount;
import com.intkr.saas.manager.BaseManagerImpl;
import com.intkr.saas.manager.order.PaymentManager;

/**
 * 
 * @author Beiden
 * @date 2011-7-28 上午10:27:29
 * @version 1.0
 */
@Repository("PaymentManager")
public class PaymentManagerImpl extends BaseManagerImpl<PaymentBO, PaymentDO> implements PaymentManager {

	@Resource
	private PaymentDAO paymentDAO;

	public BaseDAO<PaymentDO> getBaseDAO() {
		return paymentDAO;
	}

	public PaymentBO getYuePay(Long saasId) {
		return getPayment(saasId, MoneyAccount.Yuepay.getCode());
	}

	public PaymentBO getJdpay(Long saasId) {
		return getPayment(saasId, MoneyAccount.Jdpay.getCode());
	}

	public PaymentBO getAlipay(Long saasId) {
		return getPayment(saasId, MoneyAccount.Alipay.getCode());
	}

	public PaymentBO getWxpay(Long saasId) {
		return getPayment(saasId, MoneyAccount.Wxpay.getCode());
	}

	private PaymentBO getPayment(Long saasId, String type) {
		if (type == null || "".equals(type)) {
			return null;
		}
		PaymentBO query = new PaymentBO();
		query.setSaasId(saasId);
		query.set_pageSize(1);
		query.setIsOpen(1);
		query.setType(type);
		return selectOne(query);
	}

	public PaymentBO getOffLinePay(Long saasId) {
		return getPayment(saasId, MoneyAccount.OffLinePay.getCode());
	}

	public PaymentBO getBankPay(Long saasId) {
		return getPayment(saasId, MoneyAccount.BankPay.getCode());
	}

	public PaymentBO getByType(Long saasId, String type) {
		if (saasId == null) {
			return null;
		}
		if (type == null || "".equals(type)) {
			return null;
		}
		PaymentBO query = new PaymentBO();
		query.setSaasId(saasId);
		query.setType(type);
		return selectOne(query);
	}

}
