package com.intkr.saas.facade.wx;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.WxMpTemplateMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.item.DemandBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.user.WxAccountBO;
import com.intkr.saas.manager.conf.OptionManager;
import com.intkr.saas.manager.item.DemandManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.manager.saas.SaasClientManager;
import com.intkr.saas.manager.user.WxAccountManager;
import com.intkr.saas.util.DateUtil;
import com.intkr.saas.util.claz.IOC;

/**
 * 
 * @author Beiden
 * @date 2016-5-23 下午8:06:46
 * @version 1.0
 */
public class TemplateMsgFacade {

	private static final Logger logger = LoggerFactory.getLogger(TemplateMsgFacade.class);

	private static WxAccountManager wxAccountManager = IOC.get(WxAccountManager.class);

	private static ItemManager itemManager = IOC.get(ItemManager.class);

	private static OptionManager optionManager = IOC.get(OptionManager.class);

	private static DemandManager demandManager = IOC.get(DemandManager.class);
	
	private static SaasClientManager saasClientManager = IOC.get(SaasClientManager.class);

	public static void sendDemandAuditFail(Long demandId) {
		if (demandId == null || "".equals(demandId)) {
			return;
		}
		DemandBO demand = demandManager.get(demandId);
		if (demand == null) {
			return;
		}
		WxAccountBO wxaccount = wxAccountManager.getByUserId(demand.getUserId());
		if (wxaccount == null) {
			return;
		}
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(wxaccount.getOpenId());
		templateMessage.setTemplateId("T02RShJnIS_2fQ_kPAVgBg7oVX0KQdwy4wDv8cD4NMI");
		templateMessage.setUrl("http://" + saasClientManager.getSaasDomain(demand.getSaasId()));
		List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
		WxMpTemplateData wxMpTemplateData = null;
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue("老板，抱歉地通知你，你发布的需求[" + demand.getName() + "]未能通过审核，请联系客户进行处理。");
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword1");
			wxMpTemplateData.setValue(demand.getName());
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword2");
			wxMpTemplateData.setValue(DateUtil.formatDateTime(new Date()));
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("remark");
			wxMpTemplateData.setValue("如有任何疑问请随时联系您的专属客服。");
			datas.add(wxMpTemplateData);
		}
		templateMessage.setDatas(datas);
		try {
			Config.wxService.templateSend(templateMessage);
		} catch (WxErrorException e) {
			logger.error("", e);
		}
	}

	public static void sendDemandAuditSuccss(Long demandId) {
		if (demandId == null || "".equals(demandId)) {
			return;
		}
		DemandBO demand = demandManager.get(demandId);
		if (demand == null) {
			return;
		}
		WxAccountBO wxaccount = wxAccountManager.getByUserId(demand.getUserId());
		if (wxaccount == null) {
			return;
		}
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(wxaccount.getOpenId());
		templateMessage.setTemplateId("jhVR0Dyq3rLCKdVpvUStWi14SxtQ4TRwc9L69kDjAZM");
		templateMessage.setUrl("http://" + saasClientManager.getSaasDomain(demand.getSaasId()));
		List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
		WxMpTemplateData wxMpTemplateData = null;
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue("老板，恭喜你，你发布的需求[" + demand.getName() + "]已通过审核，已可上线进行交易。");
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword1");
			wxMpTemplateData.setValue(demand.getName());
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword2");
			wxMpTemplateData.setValue(DateUtil.formatDateTime(new Date()));
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("remark");
			wxMpTemplateData.setValue("如有任何疑问请随时联系您的专属客服。");
			datas.add(wxMpTemplateData);
		}
		templateMessage.setDatas(datas);
		try {
			Config.wxService.templateSend(templateMessage);
		} catch (WxErrorException e) {
			logger.error("", e);
		}
	}

	public static void sendItemAuditFail(Long itemId) {
		if (itemId == null || "".equals(itemId)) {
			return;
		}
		ItemBO item = itemManager.get(itemId);
		if (item == null) {
			return;
		}
		WxAccountBO wxaccount = wxAccountManager.getByUserId(item.getShopId());
		if (wxaccount == null) {
			return;
		}
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(wxaccount.getOpenId());
		templateMessage.setTemplateId("T02RShJnIS_2fQ_kPAVgBg7oVX0KQdwy4wDv8cD4NMI");
		templateMessage.setUrl("http://" + saasClientManager.getSaasDomain(item.getSaasId()));
		List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
		WxMpTemplateData wxMpTemplateData = null;
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue("老板，抱歉地通知你，你发布的商品[" + item.getName() + "]未能通过审核，请联系客户进行处理。");
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword1");
			wxMpTemplateData.setValue(item.getName());
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword2");
			wxMpTemplateData.setValue(DateUtil.formatDateTime(new Date()));
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("remark");
			wxMpTemplateData.setValue("如有任何疑问请随时联系您的专属客服。");
			datas.add(wxMpTemplateData);
		}
		templateMessage.setDatas(datas);
		try {
			Config.wxService.templateSend(templateMessage);
		} catch (WxErrorException e) {
			logger.error("", e);
		}
	}

	/**
	 * 商品审核成功通知
	 * 
	 * @param userId
	 *            @
	 */
	public static void sendItemAuditSuccss(Long itemId) {
		if (itemId == null || "".equals(itemId)) {
			return;
		}
		ItemBO item = itemManager.get(itemId);
		if (item == null) {
			return;
		}
		WxAccountBO wxaccount = wxAccountManager.getByUserId(item.getShopId());
		if (wxaccount == null) {
			return;
		}
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(wxaccount.getOpenId());
		templateMessage.setTemplateId("jhVR0Dyq3rLCKdVpvUStWi14SxtQ4TRwc9L69kDjAZM");
		templateMessage.setUrl("http://" + saasClientManager.getSaasDomain(item.getSaasId()));
		List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
		WxMpTemplateData wxMpTemplateData = null;
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue("老板，恭喜你，你发布的商品[" + item.getName() + "]已通过审核，已可上线进行交易。");
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword1");
			wxMpTemplateData.setValue(item.getName());
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword2");
			wxMpTemplateData.setValue(DateUtil.formatDateTime(new Date()));
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("remark");
			wxMpTemplateData.setValue("如有任何疑问请随时联系您的专属客服。");
			datas.add(wxMpTemplateData);
		}
		templateMessage.setDatas(datas);
		try {
			Config.wxService.templateSend(templateMessage);
		} catch (WxErrorException e) {
			logger.error("", e);
		}
	}

	public static void auditMsg(String openId, String first, String wxNickName, String title, Date publicTime, String result, String remark) {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(openId);
		templateMessage.setTemplateId("zHpJA-yf4FuG1-VfofUfOD_XX4KxgbIsq6mVmzOjD5E");
//		templateMessage.setUrl(optionManager.getWebsiteUrl());
		List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
		WxMpTemplateData wxMpTemplateData = null;
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue(first);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword1");
			wxMpTemplateData.setValue(wxNickName);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword2");
			wxMpTemplateData.setValue(title);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword3");
			wxMpTemplateData.setValue(DateUtil.formatDateTime(publicTime));
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword4");
			wxMpTemplateData.setValue(result);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("remark");
			wxMpTemplateData.setValue(remark);
			datas.add(wxMpTemplateData);
		}
		templateMessage.setDatas(datas);
		try {
			Config.wxService.templateSend(templateMessage);
		} catch (WxErrorException e) {
			logger.error("", e);
		}
	}

	/**
	 * 业务信息提醒
	 */
	public static void busMsg(String openId, String first, String msgType, String msgName, String msgSummary, String remark) {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser(openId);
		templateMessage.setTemplateId("nyzlmbEO61eMISWZpwCmUU8d9o9WLs5sB3WEJaZIfhE");
//		templateMessage.setUrl(optionManager.getWebsiteUrl());
		List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
		WxMpTemplateData wxMpTemplateData = null;
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue(first);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword1");
			wxMpTemplateData.setValue(msgType);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword2");
			wxMpTemplateData.setValue(msgName);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("keyword3");
			wxMpTemplateData.setValue(msgSummary);
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("remark");
			wxMpTemplateData.setValue(remark);
			datas.add(wxMpTemplateData);
		}
		templateMessage.setDatas(datas);
		try {
			Config.wxService.templateSend(templateMessage);
		} catch (WxErrorException e) {
			logger.error("", e);
		}
	}

	private static void test() {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		templateMessage.setToUser("oTDqiwjYAiKIZApaAyxL2zrBeh-I");
		templateMessage.setTemplateId("6FxrADOZ8f5Wp-f-4CkQHDwVWdQlHog9zeLA0VyCO4M");
//		templateMessage.setUrl(optionManager.getWebsiteUrl());
		List<WxMpTemplateData> datas = new ArrayList<WxMpTemplateData>();
		WxMpTemplateData wxMpTemplateData = null;
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("first");
			wxMpTemplateData.setValue("我们已收到您的货款，开始为您打包商品，请耐心等待: )");
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("OrderSn");
			wxMpTemplateData.setValue("DD1234");
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("OrderStatus");
			wxMpTemplateData.setValue("待支付");
			datas.add(wxMpTemplateData);
		}
		{
			wxMpTemplateData = new WxMpTemplateData();
			wxMpTemplateData.setColor("#173177");
			wxMpTemplateData.setName("Remark");
			wxMpTemplateData.setValue("如有问题请致电400-828-1878或直接在微信留言，小易将第一时间为您服务！");
			datas.add(wxMpTemplateData);
		}
		templateMessage.setDatas(datas);
		try {
			Config.wxService.templateSend(templateMessage);
		} catch (WxErrorException e) {
			logger.error("", e);
		}
	}

	public static void main(String[] args) throws Exception {
		new TemplateMsgFacade().test();
	}

}
