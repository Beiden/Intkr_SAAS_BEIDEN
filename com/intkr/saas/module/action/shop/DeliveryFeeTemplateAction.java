package com.intkr.saas.module.action.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.shop.DeliveryFeeTemplateBO;
import com.intkr.saas.manager.BaseManager;
import com.intkr.saas.manager.shop.DeliveryFeeTemplateManager;
import com.intkr.saas.module.action.BaseAction;
import com.intkr.saas.util.PagerUtil;
import com.intkr.saas.util.RequestUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 运费模版
 * 
 * @table delivery_fee_template
 * 
 * @author Beiden
 * @date 2021-01-18 18:32:16
 * @version 1.0
 */
public class DeliveryFeeTemplateAction extends BaseAction<DeliveryFeeTemplateBO> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DeliveryFeeTemplateManager deliveryFeeTemplateManager = IOC.get(DeliveryFeeTemplateManager.class);

	public DeliveryFeeTemplateBO getBOForAddUpdate(HttpServletRequest request) {
		DeliveryFeeTemplateBO deliveryFeeTemplate = getParameter(request);
		deliveryFeeTemplate.setFeature("expressDefaultFeeStartCount", RequestUtil.getParam(request, "expressDefaultFeeStartCount", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeStartCountPrice", RequestUtil.getParam(request, "expressDefaultFeeStartCountPrice", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeCountStep", RequestUtil.getParam(request, "expressDefaultFeeCountStep", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeCountStepPrice", RequestUtil.getParam(request, "expressDefaultFeeCountStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeStartWeight", RequestUtil.getParam(request, "expressDefaultFeeStartWeight", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeStartWeightPrice", RequestUtil.getParam(request, "expressDefaultFeeStartWeightPrice", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeWeightStep", RequestUtil.getParam(request, "expressDefaultFeeWeightStep", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeWeightStepPrice", RequestUtil.getParam(request, "expressDefaultFeeWeightStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeStartVolumn", RequestUtil.getParam(request, "expressDefaultFeeStartVolumn", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeStartVolumnPrice", RequestUtil.getParam(request, "expressDefaultFeeStartVolumnPrice", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeVolumnStep", RequestUtil.getParam(request, "expressDefaultFeeVolumnStep", Double.class));
		deliveryFeeTemplate.setFeature("expressDefaultFeeVolumnStepPrice", RequestUtil.getParam(request, "expressDefaultFeeVolumnStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeStartCount", RequestUtil.getParam(request, "emsDefaultFeeStartCount", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeStartCountPrice", RequestUtil.getParam(request, "emsDefaultFeeStartCountPrice", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeCountStep", RequestUtil.getParam(request, "emsDefaultFeeCountStep", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeCountStepPrice", RequestUtil.getParam(request, "emsDefaultFeeCountStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeStartWeight", RequestUtil.getParam(request, "emsDefaultFeeStartWeight", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeStartWeightPrice", RequestUtil.getParam(request, "emsDefaultFeeStartWeightPrice", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeWeightStep", RequestUtil.getParam(request, "emsDefaultFeeWeightStep", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeWeightStepPrice", RequestUtil.getParam(request, "emsDefaultFeeWeightStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeStartVolumn", RequestUtil.getParam(request, "emsDefaultFeeStartVolumn", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeStartVolumnPrice", RequestUtil.getParam(request, "emsDefaultFeeStartVolumnPrice", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeVolumnStep", RequestUtil.getParam(request, "emsDefaultFeeVolumnStep", Double.class));
		deliveryFeeTemplate.setFeature("emsDefaultFeeVolumnStepPrice", RequestUtil.getParam(request, "emsDefaultFeeVolumnStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeStartCount", RequestUtil.getParam(request, "mailDefaultFeeStartCount", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeStartCountPrice", RequestUtil.getParam(request, "mailDefaultFeeStartCountPrice", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeCountStep", RequestUtil.getParam(request, "mailDefaultFeeCountStep", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeCountStepPrice", RequestUtil.getParam(request, "mailDefaultFeeCountStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeStartWeight", RequestUtil.getParam(request, "mailDefaultFeeStartWeight", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeStartWeightPrice", RequestUtil.getParam(request, "mailDefaultFeeStartWeightPrice", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeWeightStep", RequestUtil.getParam(request, "mailDefaultFeeWeightStep", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeWeightStepPrice", RequestUtil.getParam(request, "mailDefaultFeeWeightStepPrice", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeStartVolumn", RequestUtil.getParam(request, "mailDefaultFeeStartVolumn", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeStartVolumnPrice", RequestUtil.getParam(request, "mailDefaultFeeStartVolumnPrice", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeVolumnStep", RequestUtil.getParam(request, "mailDefaultFeeVolumnStep", Double.class));
		deliveryFeeTemplate.setFeature("mailDefaultFeeVolumnStepPrice", RequestUtil.getParam(request, "mailDefaultFeeVolumnStepPrice", Double.class));
		String province = RequestUtil.getParam(request, "province", "");
		String city = RequestUtil.getParam(request, "city", "");
		String area = RequestUtil.getParam(request, "area", "");
		deliveryFeeTemplate.setAddress(province + "," + city + "," + area);
		return deliveryFeeTemplate;
	}

	public DeliveryFeeTemplateBO getBO(HttpServletRequest request) {
		DeliveryFeeTemplateBO deliveryFeeTemplateBO = getParameter(request);
		return deliveryFeeTemplateBO;
	}

	public static DeliveryFeeTemplateBO getParameter(HttpServletRequest request) {
		DeliveryFeeTemplateBO deliveryFeeTemplateBO = new DeliveryFeeTemplateBO();
		deliveryFeeTemplateBO.setId(RequestUtil.getParam(request, "id", Long.class));
		deliveryFeeTemplateBO.setSaasId(RequestUtil.getParam(request, "saasId", Long.class));
		deliveryFeeTemplateBO.setShopId(RequestUtil.getParam(request, "shopId", Long.class));
		deliveryFeeTemplateBO.setName(RequestUtil.getParam(request, "name", String.class));
		deliveryFeeTemplateBO.setAddress(RequestUtil.getParam(request, "address", String.class));
		deliveryFeeTemplateBO.setSendTime(RequestUtil.getParam(request, "sendTime", Integer.class));
		deliveryFeeTemplateBO.setFreeShipping(RequestUtil.getParam(request, "freeShipping", Integer.class));
		deliveryFeeTemplateBO.setValuateType(RequestUtil.getParam(request, "valuateType", String.class));
		deliveryFeeTemplateBO.setDeliveryType(RequestUtil.getParam(request, "deliveryType", String.class));
		deliveryFeeTemplateBO.setFeature(RequestUtil.getParam(request, "feature", String.class));
		deliveryFeeTemplateBO.setSort(RequestUtil.getParam(request, "sort", Double.class));
		PagerUtil.initPage(request, deliveryFeeTemplateBO);
		return deliveryFeeTemplateBO;
	}

	public BaseManager<?, ?> getBaseManager() {
		return deliveryFeeTemplateManager;
	}

	public void doAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeliveryFeeTemplateBO bo = getBOForAddUpdate(request);
		long id = deliveryFeeTemplateManager.insert(bo);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", bo.getId());
		request.setAttribute("result", true);
		request.setAttribute("data", data);
		request.setAttribute("msg", "添加成功！");
	}

	public void doUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DeliveryFeeTemplateBO bo = getBOForAddUpdate(request);
		deliveryFeeTemplateManager.update(bo);
		request.setAttribute("result", true);
		request.setAttribute("msg", "更新成功！");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getParam(request, "id", Long.class);
		DeliveryFeeTemplateBO template = deliveryFeeTemplateManager.get(id);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", template.getId());
		data.put("saasId", template.getSaasId());
		data.put("shopId", template.getShopId());
		data.put("name", template.getName());
		data.put("address", template.getAddress());
		data.put("province", template.getProvince());
		data.put("city", template.getCity());
		data.put("area", template.getArea());
		data.put("sendTime", template.getSendTime());
		data.put("freeShipping", template.getFreeShipping());
		data.put("valuateType", template.getValuateType());
		data.put("deliveryType", template.getDeliveryType());
		data.put("expressDefaultFeeStartCount", template.getFeature("expressDefaultFeeStartCount"));
		data.put("expressDefaultFeeStartCountPrice", template.getFeature("expressDefaultFeeStartCountPrice"));
		data.put("expressDefaultFeeCountStep", template.getFeature("expressDefaultFeeCountStep"));
		data.put("expressDefaultFeeCountStepPrice", template.getFeature("expressDefaultFeeCountStepPrice"));
		data.put("expressDefaultFeeStartWeight", template.getFeature("expressDefaultFeeStartWeight"));
		data.put("expressDefaultFeeStartWeightPrice", template.getFeature("expressDefaultFeeStartWeightPrice"));
		data.put("expressDefaultFeeWeightStep", template.getFeature("expressDefaultFeeWeightStep"));
		data.put("expressDefaultFeeWeightStepPrice", template.getFeature("expressDefaultFeeWeightStepPrice"));
		data.put("expressDefaultFeeStartVolumn", template.getFeature("expressDefaultFeeStartVolumn"));
		data.put("expressDefaultFeeStartVolumnPrice", template.getFeature("expressDefaultFeeStartVolumnPrice"));
		data.put("expressDefaultFeeVolumnStep", template.getFeature("expressDefaultFeeVolumnStep"));
		data.put("expressDefaultFeeVolumnStepPrice", template.getFeature("expressDefaultFeeVolumnStepPrice"));
		data.put("emsDefaultFeeStartCount", template.getFeature("emsDefaultFeeStartCount"));
		data.put("emsDefaultFeeStartCountPrice", template.getFeature("emsDefaultFeeStartCountPrice"));
		data.put("emsDefaultFeeCountStep", template.getFeature("emsDefaultFeeCountStep"));
		data.put("emsDefaultFeeCountStepPrice", template.getFeature("emsDefaultFeeCountStepPrice"));
		data.put("emsDefaultFeeStartWeight", template.getFeature("emsDefaultFeeStartWeight"));
		data.put("emsDefaultFeeStartWeightPrice", template.getFeature("emsDefaultFeeStartWeightPrice"));
		data.put("emsDefaultFeeWeightStep", template.getFeature("emsDefaultFeeWeightStep"));
		data.put("emsDefaultFeeWeightStepPrice", template.getFeature("emsDefaultFeeWeightStepPrice"));
		data.put("emsDefaultFeeStartVolumn", template.getFeature("emsDefaultFeeStartVolumn"));
		data.put("emsDefaultFeeStartVolumnPrice", template.getFeature("emsDefaultFeeStartVolumnPrice"));
		data.put("emsDefaultFeeVolumnStep", template.getFeature("emsDefaultFeeVolumnStep"));
		data.put("emsDefaultFeeVolumnStepPrice", template.getFeature("emsDefaultFeeVolumnStepPrice"));
		data.put("mailDefaultFeeStartCount", template.getFeature("mailDefaultFeeStartCount"));
		data.put("mailDefaultFeeStartCountPrice", template.getFeature("mailDefaultFeeStartCountPrice"));
		data.put("mailDefaultFeeCountStep", template.getFeature("mailDefaultFeeCountStep"));
		data.put("mailDefaultFeeCountStepPrice", template.getFeature("mailDefaultFeeCountStepPrice"));
		data.put("mailDefaultFeeStartWeight", template.getFeature("mailDefaultFeeStartWeight"));
		data.put("mailDefaultFeeStartWeightPrice", template.getFeature("mailDefaultFeeStartWeightPrice"));
		data.put("mailDefaultFeeWeightStep", template.getFeature("mailDefaultFeeWeightStep"));
		data.put("mailDefaultFeeWeightStepPrice", template.getFeature("mailDefaultFeeWeightStepPrice"));
		data.put("mailDefaultFeeStartVolumn", template.getFeature("mailDefaultFeeStartVolumn"));
		data.put("mailDefaultFeeStartVolumnPrice", template.getFeature("mailDefaultFeeStartVolumnPrice"));
		data.put("mailDefaultFeeVolumnStep", template.getFeature("mailDefaultFeeVolumnStep"));
		data.put("mailDefaultFeeVolumnStepPrice", template.getFeature("mailDefaultFeeVolumnStepPrice"));
		request.setAttribute("result", true);
		request.setAttribute("msg", "查询成功！");
		request.setAttribute("data", data);
	}

}
