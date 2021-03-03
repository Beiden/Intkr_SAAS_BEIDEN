package com.intkr.saas.module.action.buyer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.aop.StartTransaction;
import com.intkr.saas.client.user.SessionClient;
import com.intkr.saas.domain.bo.order.DeliveryAddressBO;
import com.intkr.saas.domain.bo.user.UserBO;
import com.intkr.saas.manager.order.DeliveryAddressManager;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2011-4-27 下午3:09:44
 * @version 1.0
 */
public class DeliveryAddressAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryAddressManager deliveryAddressManager = IOC.get("DeliveryAddressManager");

	public static DeliveryAddressBO getParameter(HttpServletRequest request) {
		DeliveryAddressBO bo = new DeliveryAddressBO();
		bo.setId(RequestUtil.getParam(request, "id", Long.class));
		bo.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		bo.setUserId(RequestUtil.getParam(request, "userId", Long.class));
		bo.setProvince(RequestUtil.getParam(request, "province"));
		bo.setCity(RequestUtil.getParam(request, "city"));
		bo.setArea(RequestUtil.getParam(request, "area"));
		bo.setDetail(RequestUtil.getParam(request, "detail"));
		bo.setPostalCode(RequestUtil.getParam(request, "postalCode"));
		bo.setConsignee(RequestUtil.getParam(request, "consignee"));
		bo.setTel(RequestUtil.getParam(request, "tel"));
		bo.setIsDefault(RequestUtil.getParam(request, "isDefault", Integer.class));
		PagerUtil.initPage(request, bo);
		return bo;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeliveryAddressBO deliveryAddressBO = getParameter(request);
		deliveryAddressBO.setSaasId(SessionClient.getSaasId(request));
		deliveryAddressBO.setIsDefault(RequestUtil.getParam(request, "isDefault", Integer.class, 2));
		UserBO userBO = SessionClient.getLoginUser(request);
		deliveryAddressBO.setId(deliveryAddressManager.getId());
		deliveryAddressBO.setUserId(userBO.getId());
		deliveryAddressManager.insert(deliveryAddressBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "添加收货地址成功");
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeliveryAddressBO deliveryAddressBO = getParameter(request);
		deliveryAddressBO.setSaasId(SessionClient.getSaasId(request));
		deliveryAddressBO.setIsDefault(RequestUtil.getParam(request, "isDefault", Integer.class, 2));
		UserBO userBO = SessionClient.getLoginUser(request);
		DeliveryAddressBO oldDeliveryAddressBO = deliveryAddressManager.get(deliveryAddressBO.getId());
		if (userBO.getId().equals(oldDeliveryAddressBO.getUserId())) {
			deliveryAddressManager.update(deliveryAddressBO);
			request.setAttribute("result", true);
			request.setAttribute("msg", "更新收货地址成功");
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "收货地址不存在");
		}
	}

	public void doDeleteOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = RequestUtil.getParam(request, "deleteId");
		DeliveryAddressBO deliveryAddressBO = deliveryAddressManager.get(id);
		if (deliveryAddressBO == null) {
			request.setAttribute("result", false);
			request.setAttribute("msg", "收货地址不存在");
			return;
		}
		UserBO userBO = SessionClient.getLoginUser(request);
		if (userBO.getId().equals(deliveryAddressBO.getUserId())) {
			deliveryAddressManager.delete(id);
			request.setAttribute("result", true);
			request.setAttribute("msg", "操作成功");
			return;
		} else {
			request.setAttribute("result", false);
			request.setAttribute("msg", "收货地址不存在");
		}
	}

	@StartTransaction
	public void doSetDefault(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (RequestUtil.existParam(request, "setDefaultId")) {
			String id = RequestUtil.getParam(request, "setDefaultId");
			Long userId = SessionClient.getLoginUserId(request);
			DeliveryAddressBO oldDa = deliveryAddressManager.getDefault(userId);
			if (oldDa != null) {
				oldDa.setIsDefault(-1);
				deliveryAddressManager.update(oldDa);
			}
			DeliveryAddressBO newDa = deliveryAddressManager.get(id);
			newDa.setIsDefault(1);
			deliveryAddressManager.update(newDa);
			request.setAttribute("result", true);
			request.setAttribute("msg", "设置默认地址成功！");
		}
	}

	public void doGetDefault(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long userId = SessionClient.getLoginUserId(request);
		DeliveryAddressBO deliveryAddressBO = deliveryAddressManager.getDefault(userId);
		request.setAttribute("data", deliveryAddressBO);
		request.setAttribute("result", true);
		request.setAttribute("msg", "操作成功");
	}

	public void doSelectAndCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeliveryAddressBO query = getParameter(request);
		query.setSaasId(SessionClient.getSaasId(request));
		query.setUserId(SessionClient.getLoginUserId(request));
		query = deliveryAddressManager.selectAndCount(query);
		request.setAttribute("data", query);
		request.setAttribute("result", true);
		request.setAttribute("msg", "查询成功！");
	}

}
