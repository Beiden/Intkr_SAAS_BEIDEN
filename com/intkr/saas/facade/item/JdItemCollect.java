package com.intkr.saas.facade.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intkr.saas.domain.bo.fs.ImgBO;
import com.intkr.saas.domain.bo.item.ItemBO;
import com.intkr.saas.domain.bo.shop.ShopBO;
import com.intkr.saas.manager.fs.ImgManager;
import com.intkr.saas.manager.item.ItemManager;
import com.intkr.saas.util.claz.IOC;

public class JdItemCollect {

	protected static Logger logger = LoggerFactory.getLogger(JdItemCollect.class);

	static boolean running = false;

	static ImgManager imgManager = IOC.get("ImgManager");

	static ItemManager itemManager = IOC.get("ItemManager");

	static Executor executor = Executors.newFixedThreadPool(30);

	private static void addItem(String itemName, List<String> pictureUrls, String content, String price) {
		if (pictureUrls == null || pictureUrls.isEmpty()) {
			return;
		}
		ShopBO shop = ItemDispather.getRandomShop();

		ItemBO query = new ItemBO();
		query.setName(itemName);
		if (itemManager.count(query) > 0) {
			return;
		}

		String imgIds = "";
		for (String pic : pictureUrls) {
			ImgBO media = new ImgBO();
			media.setId(imgManager.getId());
			media.setSaasId(1L);
			media.setUserId(shop.getUserId());
			media.setShopId(shop.getId());
			media.setName(itemName);
			media.setRealname(itemName);
			media.setUri(pic);
			imgManager.insert(media);
			imgIds += media.getId() + ";";
		}
		imgIds = imgIds.substring(0, imgIds.length() - 1);

		Map<String, String> param = new HashMap<String, String>();
		param.put("action", "shop/item/ShopItemAction");
		param.put("event_submit_doAdd", "true");
		param.put("type", "normal");
		param.put("id", "" + itemManager.getId());
		param.put("name", itemName);
		param.put("slogan", "热价来袭！可别错过！数量有限，随时涨价");
		param.put("imgIds", imgIds);
		param.put("imgIdsUpdate", imgIds);
		param.put("content", "<p><br></p>");
		param.put("skuType", "1");
		param.put("skuIds", "-1");
		// param.put("-1-skuPropertyValueIds", "-1");
		param.put("-1-skuStatus", "On");
		param.put("-1-skuIsDefault", "1");
		param.put("-1-skuImgId", "-1");
		param.put("-1-skuColor", "-1");
		param.put("-1-skuName", "name");
		param.put("-1-skuInventory", "1");
		param.put("-1-skuPrice", "".equals(price) ? "1.00" : price);
		param.put("setStatus", "approve");
		param.put("status", "approve");
		param.put("shopId", shop.getId() + "");
		param.put("shopName", "shopName");
		// param.put("content", content);
		String result = post("http://localhost/common/jsonResult.json", param, shop.getUserId());
		// System.out.println(result);
	}

	private static void collectReal(String keyword) {
		String url = "https://search.jd.com/Search?keyword={keyword}&enc=utf-8&wq={keyword}&page={page}";
		try {
			String pageUrl = url.replace("{keyword}", keyword);
			int page = 1;
			pageUrl = url.replace("{page}", page + "");
			Document doc = Jsoup.connect(pageUrl).get();
			String note = doc.select(".notice-search .ns-content").text();
			while (processPage(url.replace("{keyword}", keyword), page, keyword)) {
				page++;
				if (page > 10) {
					break;
				}
				System.out.println("keyword=" + keyword + ";page=" + page);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static class Runner implements Runnable {

		String keyword;

		public Runner(String keyword) {
			this.keyword = keyword;
		}

		public void run() {
			collectReal(keyword);
		}
	}

	private static void collect(String keyword) {
		Runner runner = new Runner(keyword);
		executor.execute(runner);
		// runner.run();
	}

	public static void main(String[] args) throws Exception {
		start();
	}

	public static void start() {
		if (running) {
			return;
		}
		running = true;
		while (true) {
			collectAll();
			try {
				Thread.sleep(1000 * 60 * 60 * 100);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	private static void collectAll() {
		// 一级类目

		collect("男装");
		collect("女装");
		collect("童装");
		collect("内衣");
		collect("美妆个护");
		collect("宠物");
		collect("女鞋");
		collect("箱包");
		collect("钟表");
		collect("珠宝");
		collect("男鞋");
		collect("运动");
		collect("户外");
		collect("汽车");
		collect("汽车用品");
		collect("母婴");
		collect("玩具乐器");
		collect("食品");
		collect("酒类");
		collect("生鲜");
		collect("特产");
		collect("医药保健");
		collect("图书");
		collect("音像");
		collect("电子书");
		collect("机票");
		collect("酒店");
		collect("旅游");
		collect("生活");
		collect("理财");
		collect("众筹");
		collect("白条");
		collect("保险");
		collect("运营商");
		collect("办公");
		collect("家居");
		collect("家具");
		collect("家装");
		collect("厨具");
		collect("家用电器");
		collect("手机");
		collect("数码");
		collect("电脑");

		// collect("计生情趣");
		// 二级类目
		collect("电视");
		collect("空调");
		collect("壁挂式空调");
		collect("柜式空调");
		collect("中央空调");
		collect("空调配件");
		collect("洗衣机");
		collect("滚筒洗衣机");
		collect("洗烘一体机");
		collect("波轮洗衣机");
		collect("迷你洗衣机");
		collect("洗衣机配件");
		collect("冰箱");
		collect("多门");
		collect("对开门");
		collect("三门");
		collect("双门");
		collect("冷柜/冰吧");
		collect("酒柜");
		collect("冰箱配件");
		collect("厨卫大电");
		collect("油烟机");
		collect("燃气灶");
		collect("烟灶套装");
		collect("消毒柜");
		collect("洗碗机");
		collect("电热水器");
		collect("燃气热水器");
		collect("嵌入式厨电");
		collect("厨房小电");
		collect("电饭煲");
		collect("微波炉");
		collect("电烤箱");
		collect("电磁炉");
		collect("电压力锅");
		collect("豆浆机");
		collect("咖啡机");
		collect("面包机");
		collect("榨汁机");
		collect("料理机");
		collect("电饼铛");
		collect("养生壶/煎药壶");
		collect("酸奶机");
		collect("煮蛋器");
		collect("电水壶/热水瓶");
		collect("电炖锅");
		collect("多用途锅");
		collect("电烧烤炉");
		collect("电热饭盒");
		collect("其它厨房电器");
		collect("生活电器");
		collect("电风扇");
		collect("冷风扇");
		collect("吸尘器");
		collect("净化器");
		collect("扫地机器人");
		collect("加湿器");
		collect("挂烫机/熨斗");
		collect("取暖电器");
		collect("插座");
		collect("电话机");
		collect("饮水机");
		collect("净水器");
		collect("除湿机");
		collect("干衣机");
		collect("清洁机");
		collect("收录/音机");
		collect("其它生活电器");
		collect("生活电器配件");
		collect("个护健康");
		collect("剃须刀");
		collect("口腔护理");
		collect("电吹风");
		collect("美容器卷/直发器");
		collect("理发器");
		collect("剃/脱毛器");
		collect("足浴盆");
		collect("健康秤/厨房秤");
		collect("按摩器");
		collect("按摩椅");
		collect("其它健康电器");
		collect("家庭影音");
		collect("家庭影院");
		collect("迷你音响");
		collect("DVD电视");
		collect("影音配件");
		collect("进口电器");

		collect("手机通讯");
		collect("手机");
		collect("对讲机");
		collect("以旧换新");
		collect("手机维修");
		collect("运营商");
		collect("合约机");
		collect("选号码");
		collect("固话");
		collect("宽带办套餐");
		collect("充话费/流量");
		collect("中国电信");
		collect("中国移动");
		collect("中国联通");
		collect("京东通信");
		collect("170选号");
		collect("手机配件");
		collect("手机壳");
		collect("贴膜");
		collect("手机存储卡");
		collect("数据线");
		collect("充电器");
		collect("手机耳机");
		collect("创意配件");
		collect("手机饰品");
		collect("手机电池");
		collect("苹果周边");
		collect("移动电源");
		collect("蓝牙耳机");
		collect("手机支架");
		collect("车载配件");
		collect("拍照配件");
		collect("摄影摄像");
		collect("数码相机");
		collect("单电/微单相机");
		collect("单反相机");
		collect("拍立得");
		collect("运动相机");
		collect("摄像机");
		collect("镜头");
		collect("户外器材");
		collect("影棚器材");
		collect("冲印服务");
		collect("数码相框");
		collect("数码配件");
		collect("存储卡");
		collect("三脚架/云台");
		collect("相机包");
		collect("滤镜");
		collect("闪光灯/手柄");
		collect("相机清洁/贴膜");
		collect("机身附件");
		collect("镜头附件");
		collect("读卡器");
		collect("支架");
		collect("电池/充电器");
		collect("影音娱乐");
		collect("耳机/耳麦");
		collect("音箱/音响");
		collect("便携/无线音箱");
		collect("收音机");
		collect("麦克风");
		collect("MP3/MP4");
		collect("专业音频");
		collect("智能设备");
		collect("智能手环");
		collect("智能手表");
		collect("智能眼镜");
		collect("智能机器人");
		collect("运动跟踪器");
		collect("健康监测");
		collect("智能配饰");
		collect("智能家居");
		collect("体感车");
		collect("无人机");
		collect("其他配件");
		collect("电子教育");
		collect("学生平板");
		collect("点读机/笔");
		collect("早教益智");
		collect("录音笔");
		collect("电纸书");
		collect("电子词典");
		collect("复读机");

		collect("电脑整机");
		collect("笔记本");
		collect("游戏本");
		collect("平板电脑");
		collect("平板电脑配件");
		collect("台式机");
		collect("一体机");
		collect("服务器/工作站");
		collect("笔记本配件");
		collect("电脑配件");
		collect("显示器");
		collect("CPU");
		collect("主板");
		collect("显卡");
		collect("硬盘");
		collect("内存");
		collect("机箱");
		collect("电源");
		collect("散热器");
		collect("刻录机/光驱");
		collect("声卡/扩展卡");
		collect("装机配件");
		collect("SSD固态硬盘");
		collect("组装电脑");
		collect("外设产品");
		collect("鼠标");
		collect("键盘");
		collect("键鼠套装");
		collect("网络仪表仪器");
		collect("U盘");
		collect("移动硬盘");
		collect("鼠标垫");
		collect("摄像头");
		collect("线缆");
		collect("手写板");
		collect("硬盘盒");
		collect("电脑工具");
		collect("电脑清洁");
		collect("UPS");
		collect("电源插座");
		collect("游戏设备");
		collect("游戏机");
		collect("游戏耳机");
		collect("手柄/方向盘");
		collect("游戏软件");
		collect("游戏周边");
		collect("网络产品");
		collect("路由器");
		collect("网络机顶盒");
		collect("交换机");
		collect("网络存储");
		collect("网卡");
		collect("4G/3G上网");
		collect("网络配件");
		collect("办公设备");
		collect("投影机");
		collect("投影配件");
		collect("多功能一体机");
		collect("打印机");
		collect("传真设备");
		collect("验钞/点钞机");
		collect("扫描设备");
		collect("复合机");
		collect("碎纸机");
		collect("考勤机");
		collect("收银机");
		collect("会议音频视频");
		collect("保险柜");
		collect("装订/封装机");
		collect("安防监控");
		collect("办公家具");
		collect("白板");
		collect("文具耗材");
		collect("硒鼓/墨粉墨盒");
		collect("色带");
		collect("纸类");
		collect("办公文具");
		collect("学生文具");
		collect("文件管理本册/便签");
		collect("计算器");
		collect("笔类画具");
		collect("画材");
		collect("财会用品");
		collect("刻录碟片/附件");
		collect("服务产品");
		collect("延保服务");
		collect("上门安装");
		collect("维修保养");
		collect("电脑软件");

		collect("厨具");
		collect("自营会场");
		collect("烹饪锅具");
		collect("刀剪菜板");
		collect("厨房配件");
		collect("水具酒具");
		collect("餐具茶具/咖啡具");
		collect("保温杯");
		collect("家纺");
		collect("床品套件");
		collect("被子枕芯");
		collect("蚊帐凉席");
		collect("毛巾浴巾");
		collect("地毯地垫");
		collect("床垫/床褥毯子");
		collect("抱枕靠垫");
		collect("窗帘/窗纱");
		collect("床单/床笠");
		collect("被套枕巾");
		collect("枕套沙发");
		collect("垫套/椅垫");
		collect("桌布/罩件");
		collect("电热毯");
		collect("布艺软饰");
		collect("生活日用");
		collect("收纳用品");
		collect("雨伞雨具");
		collect("净化除味");
		collect("浴室用品");
		collect("洗晒/熨烫");
		collect("缝纫/针织用品");
		collect("清洁工具");
		collect("家装软饰");
		collect("家居软饰");
		collect("装饰字画");
		collect("装饰摆件");
		collect("手工/十字绣");
		collect("相框/照片墙");
		collect("墙贴/装饰贴");
		collect("花瓶花艺");
		collect("香薰蜡烛");
		collect("节庆饰品");
		collect("钟饰帘艺隔断");
		collect("创意家居");
		collect("保暖防护");
		collect("灯具");
		collect("吸顶灯");
		collect("吊灯");
		collect("台灯");
		collect("筒灯");
		collect("射灯");
		collect("装饰灯");
		collect("LED灯");
		collect("氛围照明");
		collect("落地灯");
		collect("庭院灯");
		collect("应急灯/手电节能灯");
		collect("家具");
		collect("家具建材");
		collect("卧室家具");
		collect("客厅家具");
		collect("餐厅家具");
		collect("书房家具");
		collect("儿童家具");
		collect("储物家具");
		collect("阳台/户外");
		collect("办公家具");
		collect("床垫");
		collect("沙发");
		collect("电脑椅");
		collect("衣柜");
		collect("茶几");
		collect("电视柜");
		collect("餐桌");
		collect("电脑桌");
		collect("鞋架/衣帽架");
		collect("儿童桌");
		collect("椅儿童床");
		collect("晾衣架");
		collect("家装主材");
		collect("瓷砖");
		collect("地板");
		collect("油漆");
		collect("涂料");
		collect("壁纸");
		collect("涂刷辅料");
		collect("厨房卫浴");
		collect("水槽");
		collect("龙头");
		collect("淋浴");
		collect("花洒");
		collect("马桶");
		collect("智能马桶盖");
		collect("厨卫挂件");
		collect("浴室柜");
		collect("浴霸垃圾处理器");
		collect("五金电工");
		collect("锁具");
		collect("电动工具");
		collect("手动工具");
		collect("测量工具");
		collect("劳防用品");
		collect("开关插座");
		collect("门铃");
		collect("监控安防");
		collect("配电箱/断路器");
		collect("装修定制");
		collect("装修设计");
		collect("装修施工");
		collect("淋浴房");
		collect("橱柜集成");
		collect("吊顶");
		collect("门窗");
		collect("壁挂炉");
		collect("散热器");
		collect("安装服务");

		collect("女装");
		collect("商场同款");
		collect("当季热卖");
		collect("2017新品");
		collect("连衣裙");
		collect("T恤");
		collect("雪纺衫");
		collect("休闲裤");
		collect("牛仔裤");
		collect("衬衫");
		collect("针织衫");
		collect("半身裙");
		collect("短裤");
		collect("时尚套装");
		collect("卫衣");
		collect("短外套");
		collect("风衣");
		collect("打底衫");
		collect("小西装");
		collect("吊带/背心");
		collect("正装裤");
		collect("马甲");
		collect("打底裤");
		collect("真皮皮衣");
		collect("大码女装");
		collect("中老年女装");
		collect("毛呢大衣");
		collect("羽绒服");
		collect("棉服");
		collect("皮草");
		collect("羊绒衫");
		collect("毛衣");
		collect("加绒裤");
		collect("仿皮皮衣");
		collect("婚纱礼");
		collect("服旗袍/唐装");
		collect("设计师/潮牌");
		collect("男装");
		collect("商场同款");
		collect("当季热卖");
		collect("2017新品");
		collect("T恤");
		collect("牛仔裤");
		collect("休闲裤");
		collect("衬衫");
		collect("短裤");
		collect("POLO衫羽绒服");
		collect("棉服");
		collect("真皮皮衣");
		collect("夹克");
		collect("卫衣");
		collect("毛呢大衣");
		collect("大码男装");
		collect("西服套装");
		collect("仿皮皮衣");
		collect("风衣");
		collect("针织衫");
		collect("马甲/背心");
		collect("羊毛衫");
		collect("羊绒衫");
		collect("西服西裤");
		collect("卫裤/运动裤");
		collect("工装设计师/潮牌");
		collect("唐装/中山装");
		collect("中老年男装");
		collect("加绒裤");
		collect("内衣");
		collect("文胸");
		collect("睡衣/家居服");
		collect("男士内裤");
		collect("女士内裤");
		collect("吊带/背心");
		collect("文胸套装");
		collect("情侣睡衣");
		collect("塑身美体");
		collect("少女文胸");
		collect("休闲棉袜");
		collect("商务男袜");
		collect("连裤袜/丝袜");
		collect("美腿袜");
		collect("打底裤");
		collect("袜抹胸内衣");
		collect("配件大码");
		collect("内衣");
		collect("打底衫");
		collect("泳衣");
		collect("秋衣");
		collect("秋裤");
		collect("保暖内衣");
		collect("情趣内衣");
		collect("配饰");
		collect("太阳镜");
		collect("男士腰带/礼盒");
		collect("光学镜架/镜片");
		collect("女士丝巾/围巾/披肩");
		collect("防辐射眼镜");
		collect("棒球帽");
		collect("遮阳伞/雨伞");
		collect("遮阳帽");
		collect("老花镜");
		collect("领带/领结/领带夹");
		collect("女士腰带/礼盒");
		collect("毛线帽");
		collect("真皮手套");
		collect("贝雷帽");
		collect("鸭舌帽");
		collect("男士丝巾/围巾");
		collect("毛线手套");
		collect("游泳镜");
		collect("礼帽");
		collect("钥匙扣");
		collect("毛线/布面料口");
		collect("罩袖扣围巾/手套/帽子套装");
		collect("耳罩/耳包");
		collect("童装童鞋");
		collect("宝宝出游套装");
		collect("上衣");
		collect("裤子");
		collect("裙子");
		collect("运动服");
		collect("运动鞋");
		collect("内衣配饰");
		collect("亲子装");
		collect("凉鞋皮鞋/帆布鞋");
		collect("功能鞋");
		collect("演出服");
		collect("羽绒服/棉服靴子");

		collect("面部护肤");
		collect("补水");
		collect("保湿");
		collect("卸妆");
		collect("洁面");
		collect("爽肤");
		collect("水乳液");
		collect("面霜精华");
		collect("眼霜防晒");
		collect("面膜");
		collect("剃须套装");
		collect("洗发护发");
		collect("洗发");
		collect("护发");
		collect("染发");
		collect("造型");
		collect("假发");
		collect("美发工具套装");
		collect("身体护理");
		collect("补水");
		collect("保湿");
		collect("沐浴");
		collect("润肤精油");
		collect("颈部手足");
		collect("纤体塑形");
		collect("美胸套装");
		collect("口腔护理");
		collect("牙膏/牙粉");
		collect("牙刷/牙线");
		collect("漱口水套装");
		collect("女性护理");
		collect("卫生巾");
		collect("卫生护垫");
		collect("私密护理");
		collect("脱毛膏");
		collect("香水彩妆");
		collect("BB霜");
		collect("化妆棉");
		collect("女士香水");
		collect("男士香水");
		collect("底妆眉笔");
		collect("睫毛膏");
		collect("眼线眼影");
		collect("唇膏/彩腮红");
		collect("美甲美妆工具套装");
		collect("清洁用品");
		collect("纸品");
		collect("湿巾");
		collect("衣物清洁");
		collect("清洁工具");
		collect("家庭清洁一次性用品");
		collect("驱虫用品");
		collect("皮具护理");
		collect("宠物生活");
		collect("水族世界");
		collect("狗粮猫粮");
		collect("猫狗罐头");
		collect("宠物零食");
		collect("医疗保健");
		collect("宠物玩具");
		collect("宠物服饰");
		collect("猫狗窝");
		collect("洗护美容");
		collect("猫砂/猫砂盆");
		collect("狗厕所/尿垫");
		collect("宠物牵引食具水具");
		collect("小宠用品");

		collect("时尚女鞋");
		collect("2017新品");
		collect("单鞋");
		collect("休闲鞋");
		collect("帆布鞋");
		collect("妈妈鞋");
		collect("布鞋/绣花鞋");
		collect("女靴踝靴");
		collect("马丁靴");
		collect("雪地靴");
		collect("雨鞋/雨靴");
		collect("高跟鞋");
		collect("凉鞋");
		collect("拖鞋/人字拖");
		collect("鱼嘴鞋");
		collect("内增高");
		collect("坡跟鞋");
		collect("防水台");
		collect("松糕鞋");
		collect("鞋配件");
		collect("潮流女包");
		collect("真皮包");
		collect("水桶包");
		collect("单肩包");
		collect("手提包");
		collect("斜挎包");
		collect("双肩包");
		collect("钱包");
		collect("手拿包");
		collect("卡包/零钱包");
		collect("帆布包");
		collect("小方包");
		collect("钥匙包");
		collect("链条包");
		collect("贝壳包");
		collect("精品男包");
		collect("男士钱包");
		collect("双肩包");
		collect("单肩/斜挎包");
		collect("商务公文包");
		collect("男士手包");
		collect("卡包名片夹");
		collect("钥匙包");
		collect("功能箱包");
		collect("拉杆箱");
		collect("拉杆包");
		collect("旅行包");
		collect("电脑包");
		collect("休闲运动包");
		collect("书包");
		collect("登山包");
		collect("腰包/胸包");
		collect("旅行配件");
		collect("相机包");
		collect("妈咪包");
		collect("奢侈品");
		collect("箱包");
		collect("钱包");
		collect("服饰");
		collect("腰带");
		collect("鞋靴");
		collect("太阳镜/眼镜框");
		collect("饰品配件");
		collect("精选大牌");
		collect("GUCCI");
		collect("COACH");
		collect("雷朋");
		collect("施华洛世奇");
		collect("MK");
		collect("阿玛尼");
		collect("菲拉格慕");
		collect("VERSACE");
		collect("普拉达");
		collect("巴宝莉");
		collect("万宝龙");
		collect("礼品");
		collect("电子烟");
		collect("火机");
		collect("烟具");
		collect("军刀军具");
		collect("美妆礼品");
		collect("工艺礼品");
		collect("礼盒礼券");
		collect("礼品文具");
		collect("收藏品");
		collect("古董文玩");
		collect("礼品定制");
		collect("创意礼品");
		collect("电子礼品");
		collect("婚庆节庆");
		collect("鲜花绿植");
		collect("熏香京东卡");
		collect("钟表");
		collect("瑞表");
		collect("国表");
		collect("日韩表");
		collect("欧美表");
		collect("德表");
		collect("儿童手表");
		collect("智能手表");
		collect("闹钟");
		collect("座钟");
		collect("挂钟");
		collect("钟表配件");
		collect("珠宝首饰");
		collect("黄金");
		collect("K金");
		collect("时尚饰品");
		collect("钻石");
		collect("翡翠");
		collect("玉石银饰");
		collect("水晶玛瑙");
		collect("彩宝铂金");
		collect("木手串/把件珍珠");
		collect("金银投资");
		collect("投资金");
		collect("投资银");
		collect("投资收藏");
		collect("黄金托管");

		collect("流行男鞋");
		collect("2017新品");
		collect("休闲鞋");
		collect("商务休闲鞋");
		collect("正装鞋");
		collect("帆布鞋");
		collect("凉鞋");
		collect("拖鞋");
		collect("功能鞋");
		collect("增高鞋");
		collect("工装鞋");
		collect("雨鞋");
		collect("传统布鞋");
		collect("男靴");
		collect("鞋配件");
		collect("运动鞋包");
		collect("出游踏青");
		collect("跑步鞋");
		collect("休闲鞋");
		collect("篮球鞋");
		collect("帆布鞋");
		collect("板鞋");
		collect("拖鞋");
		collect("运动包");
		collect("足球鞋");
		collect("乒羽网鞋");
		collect("训练鞋");
		collect("运动服饰");
		collect("T恤");
		collect("运动裤");
		collect("紧身衣");
		collect("运动套装");
		collect("运动背心");
		collect("羽绒服");
		collect("卫衣/套头衫");
		collect("棉服");
		collect("夹克/风衣");
		collect("运动配饰");
		collect("乒羽网服");
		collect("健身训练");
		collect("跑步机");
		collect("健身车/动感单车");
		collect("椭圆机");
		collect("综合训练器");
		collect("划船机");
		collect("甩脂机");
		collect("倒立机");
		collect("武术搏击");
		collect("踏步机");
		collect("哑铃");
		collect("仰卧板/收腹机");
		collect("瑜伽用品");
		collect("舞蹈用品");
		collect("运动护具");
		collect("健腹轮");
		collect("拉力器/臂力器");
		collect("跳绳");
		collect("骑行运动");
		collect("山地车");
		collect("公路车");
		collect("折叠车");
		collect("骑行服");
		collect("电动车");
		collect("电动滑板车");
		collect("城市自行车");
		collect("平衡车");
		collect("穿戴装备");
		collect("自行车配件");
		collect("体育用品");
		collect("乒乓球");
		collect("羽毛球");
		collect("篮球");
		collect("足球");
		collect("轮滑");
		collect("滑板");
		collect("网球");
		collect("高尔夫");
		collect("台球");
		collect("排球");
		collect("棋牌");
		collect("麻将");
		collect("民俗运动");
		collect("户外鞋服");
		collect("户外出游");
		collect("户外风衣");
		collect("徒步鞋");
		collect("T恤");
		collect("冲锋衣裤");
		collect("速干衣裤");
		collect("越野跑鞋");
		collect("滑雪服");
		collect("羽绒服/棉服");
		collect("休闲衣裤");
		collect("抓绒衣裤");
		collect("溯溪鞋");
		collect("沙滩/凉拖");
		collect("休闲鞋");
		collect("软壳衣裤");
		collect("功能内衣");
		collect("军迷服饰");
		collect("登山鞋");
		collect("工装鞋");
		collect("户外袜");
		collect("户外装备");
		collect("露营烧烤");
		collect("背包帐篷/垫子");
		collect("望远镜");
		collect("烧烤用具");
		collect("便携桌椅床");
		collect("户外配饰");
		collect("军迷用品");
		collect("野餐用品");
		collect("睡袋/吊床");
		collect("救援装备");
		collect("户外照明");
		collect("旅行装备");
		collect("户外工具");
		collect("户外仪表");
		collect("登山攀岩");
		collect("极限户外");
		collect("冲浪潜水");
		collect("滑雪装备");
		collect("垂钓用品");
		collect("垂钓总动员");
		collect("钓竿");
		collect("鱼线");
		collect("浮漂");
		collect("鱼饵");
		collect("钓鱼配件");
		collect("渔具包");
		collect("钓箱");
		collect("钓椅");
		collect("鱼线轮");
		collect("钓鱼灯");
		collect("辅助装备");
		collect("游泳用品");
		collect("女士泳衣");
		collect("比基尼");
		collect("男士泳衣");
		collect("泳镜");
		collect("游泳圈");
		collect("游泳包");
		collect("防水包");
		collect("泳帽游泳配件");

		collect("汽车车型");
		collect("微型车");
		collect("小型车");
		collect("紧凑型车");
		collect("中型车");
		collect("中大型车");
		collect("豪华车");
		collect("MPV");
		collect("SUV跑车");
		collect("汽车品牌");
		collect("宝马");
		collect("宝骏");
		collect("上汽大众");
		collect("一汽奔腾");
		collect("陆风");
		collect("比亚迪");
		collect("长安");
		collect("维修保养");
		collect("出游必备");
		collect("机油");
		collect("轮胎");
		collect("添加剂");
		collect("防冻液");
		collect("滤清器");
		collect("蓄电池");
		collect("雨刷");
		collect("刹车片/盘");
		collect("火花塞");
		collect("车灯");
		collect("轮毂");
		collect("维修配件");
		collect("汽车玻璃");
		collect("减震器");
		collect("正时皮带");
		collect("底盘装甲/护板");
		collect("汽车喇叭");
		collect("汽修工具");
		collect("贴膜改装配件");
		collect("汽车装饰");
		collect("座垫");
		collect("脚垫");
		collect("座套");
		collect("头枕腰靠");
		collect("方向盘套");
		collect("后备箱垫");
		collect("香水挂件摆件");
		collect("空气净化");
		collect("车衣功能");
		collect("小件车身装饰件");
		collect("车载电器");
		collect("行车记录仪");
		collect("净化器");
		collect("蓝牙设备");
		collect("电源");
		collect("安全预警仪");
		collect("导航仪");
		collect("冰箱车载影音");
		collect("车载电台");
		collect("智能车机");
		collect("倒车雷达");
		collect("电器配件");
		collect("车载生活");
		collect("电器");
		collect("吸尘器");
		collect("智能驾驶");
		collect("汽车音响");
		collect("美容清洗");
		collect("洗车机");
		collect("洗车水枪");
		collect("车蜡镀晶镀膜玻璃水");
		collect("清洁剂");
		collect("补漆笔");
		collect("毛巾掸子");
		collect("洗车配件");
		collect("安全自驾");
		collect("安全座椅");
		collect("胎压监测");
		collect("充气泵");
		collect("摩托车");
		collect("摩托车装备");
		collect("应急救援");
		collect("防盗设备");
		collect("保温箱储物箱");
		collect("自驾野营");
		collect("赛事改装");
		collect("赛事服装");
		collect("赛事用品");
		collect("制动系统");
		collect("悬挂系统");
		collect("进气系统");
		collect("排气系统");
		collect("电子管理");
		collect("车身强化");
		collect("汽车服务");
		collect("油卡充值");
		collect("加油卡");
		collect("保养维修");
		collect("清洗美容");
		collect("功能升级");
		collect("ETC驾驶培训");

		collect("奶粉");
		collect("孕妈奶粉");
		collect("特殊配方奶粉");
		collect("有机奶粉");
		collect("营养辅食");
		collect("米粉/菜粉");
		collect("面条/粥");
		collect("果泥/果汁");
		collect("益生菌/初乳");
		collect("DHA钙铁锌/维生素");
		collect("清火/开胃");
		collect("宝宝零食");
		collect("尿裤湿巾");
		collect("拉拉裤");
		collect("成人尿裤");
		collect("婴儿湿巾");
		collect("喂养用品");
		collect("奶瓶");
		collect("奶嘴");
		collect("吸奶器");
		collect("暖奶消毒");
		collect("辅食料理");
		collect("机牙胶");
		collect("安抚食物存储");
		collect("儿童餐具");
		collect("水壶/水杯");
		collect("围兜/防溅衣");
		collect("洗护用品");
		collect("宝宝护肤");
		collect("日常护理");
		collect("洗发沐浴");
		collect("洗澡用具");
		collect("洗衣液/皂");
		collect("理发器");
		collect("婴儿口腔清洁");
		collect("座便器");
		collect("驱蚊防晒");
		collect("寝居服饰");
		collect("睡袋/抱被");
		collect("家居床品");
		collect("安全防护");
		collect("爬行垫");
		collect("婴儿内衣");
		collect("婴儿礼盒");
		collect("婴儿鞋帽");
		collect("袜婴儿外出服");
		collect("妈妈专区");
		collect("防辐射服");
		collect("孕妈装孕");
		collect("妇护肤");
		collect("妈咪包/背婴带");
		collect("待产护理");
		collect("产后塑身");
		collect("文胸/内裤");
		collect("防溢乳垫");
		collect("孕期营养");
		collect("童车童床");
		collect("安全座椅");
		collect("婴儿推车");
		collect("婴儿床");
		collect("婴儿床垫");
		collect("餐椅");
		collect("学步车");
		collect("三轮车");
		collect("自行车");
		collect("扭扭车");
		collect("滑板车");
		collect("电动车");
		collect("玩具");
		collect("遥控/电动益智玩具");
		collect("积木拼插");
		collect("动漫玩具");
		collect("毛绒布艺");
		collect("模型玩具");
		collect("健身玩具");
		collect("娃娃玩具");
		collect("绘画/DIY");
		collect("创意减压");
		collect("乐器");
		collect("钢琴");
		collect("电子琴/电钢琴");
		collect("吉他/尤克里里");
		collect("打击乐器");
		collect("西洋管弦");
		collect("民族乐器");
		collect("乐器配件");

		collect("新鲜水果");
		collect("苹果");
		collect("香蕉");
		collect("梨");
		collect("橙子");
		collect("奇异果/猕猴桃");
		collect("火龙果");
		collect("瓜类");
		collect("芒果");
		collect("百香果");
		collect("榴莲");
		collect("国产水果");
		collect("进口水果");
		collect("蔬菜蛋品");
		collect("蛋品");
		collect("叶菜类");
		collect("根茎类");
		collect("葱姜蒜椒");
		collect("鲜菌菇茄");
		collect("果瓜类");
		collect("半加工蔬菜");
		collect("半加工豆制品");
		collect("玉米山药萝卜");
		collect("精选肉类");
		collect("猪肉");
		collect("牛肉");
		collect("羊肉");
		collect("鸡肉");
		collect("鸭肉");
		collect("冷鲜肉");
		collect("特色肉类");
		collect("内脏类");
		collect("冷藏熟食");
		collect("牛排");
		collect("牛腩");
		collect("鸡翅");
		collect("海鲜水产");
		collect("鱼类");
		collect("虾类");
		collect("蟹类");
		collect("贝类");
		collect("海参活鲜");
		collect("特色水产");
		collect("海产干货");
		collect("三文鱼");
		collect("扇贝");
		collect("帝王蟹");
		collect("海产礼盒");
		collect("冷饮冻食");
		collect("水饺/馄饨");
		collect("汤圆/元宵");
		collect("面点/面食");
		collect("奶酪/黄油");
		collect("速冻半成品");
		collect("火锅丸串");
		collect("冰淇淋冷藏");
		collect("果蔬汁");
		collect("低温乳品");
		collect("中外名酒");
		collect("白酒");
		collect("葡萄酒");
		collect("洋酒");
		collect("啤酒");
		collect("黄酒/养生酒");
		collect("收藏酒/陈年老酒");
		collect("进口食品");
		collect("牛奶");
		collect("饼干");
		collect("蛋糕");
		collect("糖果/巧克力");
		collect("休闲零食");
		collect("冲调饮品");
		collect("粮油调味");
		collect("休闲食品");
		collect("休闲零食");
		collect("坚果炒货");
		collect("肉干肉脯");
		collect("熟食腊味");
		collect("蜜饯果干");
		collect("糖果/巧克力");
		collect("饼干蛋糕");
		collect("地方特产");
		collect("茗茶");
		collect("铁观音");
		collect("普洱");
		collect("龙井");
		collect("绿茶");
		collect("红茶");
		collect("乌龙茶");
		collect("花草茶");
		collect("花果茶");
		collect("黑茶");
		collect("白茶");
		collect("养生茶");
		collect("其他茶");
		collect("饮料冲调");
		collect("牛奶乳品");
		collect("饮料饮用水");
		collect("咖啡/奶茶");
		collect("蜂蜜/柚子茶");
		collect("冲饮谷物成人奶粉");
		collect("粮油调味");
		collect("米面杂粮");
		collect("食用油调味品");
		collect("南北干货");
		collect("方便食品");
		collect("烘焙原料");
		collect("有机食品");

		collect("中西药品");
		collect("感冒咳嗽");
		collect("补肾壮阳");
		collect("补气养血");
		collect("止痛镇痛");
		collect("耳鼻喉用药");
		collect("眼科用药");
		collect("口腔用药");
		collect("皮肤用药");
		collect("肠胃消化");
		collect("风湿骨外伤");
		collect("男科/泌尿");
		collect("妇科用药");
		collect("儿科用药");
		collect("心脑血管");
		collect("肝胆用药");
		collect("营养健康");
		collect("调节免疫");
		collect("调节三高");
		collect("缓解疲劳");
		collect("美体塑身");
		collect("美容养颜");
		collect("肝肾养护");
		collect("肠胃养护");
		collect("明目益智");
		collect("骨骼健康");
		collect("改善睡眠");
		collect("抗氧化耐缺氧");
		collect("营养成分");
		collect("维生素/矿物质");
		collect("蛋白质");
		collect("鱼油/磷脂");
		collect("螺旋藻");
		collect("番茄红素");
		collect("叶酸葡萄籽左旋肉碱辅酶Q10");
		collect("益生菌");
		collect("玛咖");
		collect("膳食纤维");
		collect("牛初乳胶原蛋白");
		collect("大豆异黄酮芦荟提取酵素");
		collect("滋补养生");
		collect("阿胶");
		collect("蜂蜜/蜂产品");
		collect("枸杞");
		collect("燕窝");
		collect("海参");
		collect("冬虫夏草");
		collect("人参/西洋参");
		collect("三七");
		collect("鹿茸");
		collect("雪蛤");
		collect("青钱柳");
		collect("石斛/枫斗");
		collect("灵芝/孢子粉");
		collect("当归");
		collect("养生茶");
		collect("饮药食同源");
		collect("计生情趣");
		collect("安全避孕");
		collect("验孕测孕");
		collect("人体润滑");
		collect("男用延时");
		collect("男用器具");
		collect("女用器具");
		collect("情趣内衣");
		collect("保健器械");
		collect("血压计");
		collect("血糖仪");
		collect("血氧仪");
		collect("体温计");
		collect("体重秤");
		collect("胎心仪");
		collect("呼吸制氧");
		collect("雾化器");
		collect("助听器");
		collect("轮椅拐杖");
		collect("中医保健");
		collect("养生器械");
		collect("理疗仪");
		collect("家庭护理");
		collect("智能健康");
		collect("护理护具");
		collect("隐形眼镜");
		collect("护理液口");
		collect("罩眼罩/耳塞");
		collect("跌打损伤暖贴");
		collect("鼻喉护理");
		collect("眼部保健");
		collect("美体护理");

		collect("邮币");
		collect("邮票");
		collect("钱币");
		collect("邮资");
		collect("封片");
		collect("音像");
		collect("音乐影视");
		collect("教育音像");
		collect("游戏影视/动漫周边");
		collect("少儿");
		collect("儿童文学");
		collect("绘本科普");
		collect("幼儿启蒙");
		collect("手工游戏");
		collect("智力开发");
		collect("教育");
		collect("教材");
		collect("中小学教辅考试");
		collect("外语学习");
		collect("字典词典");
		collect("文艺");
		collect("小说文学");
		collect("青春文学");
		collect("传记动漫");
		collect("艺术摄影");
		collect("书法音乐绘画");
		collect("经管励志");
		collect("管理");
		collect("金融与投资");
		collect("经济");
		collect("励志与成功");
		collect("人文社科");
		collect("历史");
		collect("心理学");
		collect("政治/军事");
		collect("国学/古籍");
		collect("哲学/宗教");
		collect("社会科学法律文化");
		collect("生活");
		collect("育儿/家教");
		collect("孕产/胎教");
		collect("健身保健");
		collect("旅游/地图");
		collect("美食时尚");
		collect("美妆家居");
		collect("手工DIY");
		collect("两性体育");
		collect("科技");
		collect("计算机与互联网科普");
		collect("建筑工业");
		collect("技术电子/通信");
		collect("医学科学与自然农林");
		collect("刊/原版");
		collect("杂志/期刊");
		collect("英文原版书");
		collect("港台图书");
		collect("电子书");
		collect("畅读VIP");
		collect("小说励志与成功");
		collect("经济金融文学社科");
		collect("婚恋两性");
		collect("外文原版");

		collect("交通出行");
		collect("国内机票");
		collect("国际机票");
		collect("火车票");
		collect("机场服务");
		collect("酒店预订");
		collect("国内酒店");
		collect("国际酒店");
		collect("超值精选酒店");
		collect("旅游度假");
		collect("国内旅游");
		collect("出境旅游");
		collect("全球签证");
		collect("景点门票");
		collect("邮轮旅行");
		collect("保险");
		collect("商旅服务");
		collect("企业差旅");
		collect("团队建设");
		collect("奖励旅游");
		collect("会议周边");
		collect("会议展览");
		collect("演出票务");
		collect("电影选座");
		collect("演唱会");
		collect("音乐会");
		collect("话剧歌剧");
		collect("体育赛事");
		collect("舞蹈芭蕾");
		collect("戏曲综艺");
		collect("生活缴费");
		collect("水费电费煤气费");
		collect("生活服务");
		collect("外卖订座");
		collect("家政保洁");
		collect("休闲娱乐");
		collect("医疗美容");
		collect("教育培训");
		collect("早教幼教");
		collect("中小学教育");
		collect("语言/留学");
		collect("学历教育");
		collect("职业技能");
		collect("兴趣爱好");
		collect("彩票");
		collect("购彩中心");
		collect("开奖结果");
		collect("数据图表");
		collect("彩民服务");
		collect("游戏");
		collect("QQ充值");
		collect("游戏点卡");
		collect("网页游戏");
		collect("游戏周边");
		collect("手机游戏");
		collect("单机游戏");
		collect("海外生活");
		collect("留学留学后");
		collect("服务游学");
		collect("海外房产");

		collect("理财");
		collect("京东小金库");
		collect("票据理财");
		collect("基金理财");
		collect("定期理财");
		collect("固收理财");
		collect("妈妈理财");
		collect("慧投理财");
		collect("众筹");
		collect("智能硬件");
		collect("流行文化");
		collect("生活美学");
		collect("公益其他");
		collect("权益众筹");
		collect("东家");
		collect("私募股权");
		collect("白条");
		collect("京东白条");
		collect("白条联名卡");
		collect("京东钢镚");
		collect("旅游白条");
		collect("安居白条");
		collect("校园金融");
		collect("京东金采");
		collect("钱包");
		collect("京东钱包");
		collect("保险");
		collect("车险健康险");
		collect("意外险旅行险");
		collect("股票");
		collect("实时资讯");
		collect("市场行情");
		collect("投资达人");
		collect("量化平台");
	}

	private static boolean processPage(String url, int page, String keyword) throws IOException {
		try {
			String pageUrl = url.replace("{page}", page + "");
			Document doc = Jsoup.connect(pageUrl).get();
			String note = doc.select(".notice-search .ns-content").text();
			if (note.contains("抱歉，没有找到")) {
				return false;
			}
			Elements itemInfoList = doc.select(".gl-warp .gl-item");
			for (Element itemInfo : itemInfoList) {
				if (itemInfo.select(".p-name a").size() == 0) {
					continue;
				}
				String title = itemInfo.select(".p-name a em").get(0).text();
				String href = itemInfo.select(".p-name a").get(0).attr("href");
				if (!href.startsWith("http")) {
					href = "https:" + href;
				}
				String price = itemInfo.select(".p-price strong").get(0).attr("data-price");
				Document itemDetailDoc = Jsoup.connect(href).get();
				Elements es = itemDetailDoc.select(".spec-list ul li img");
				List<String> pictureUrls = new ArrayList<String>();
				for (Element e : es) {
					pictureUrls.add("http://img13.360buyimg.com/n1/" + e.attr("data-url"));
				}
				String content = itemDetailDoc.select(".detail-content-wrap").html();

				addItem(title, pictureUrls, content, price);
			}
			return true;
		} catch (Throwable e) {
			logger.error(e.getMessage());
			// e.printStackTrace();
			logger.error("报错！！！");
			return true;
		}
	}

	public static String post(String url, Map<String, String> param, Long userId) {
		HttpClient client = new HttpClient();
		PostMethod httpPost = new PostMethod(url);
		try {
			String chartSet = "UTF-8";
			HttpMethodParams params = httpPost.getParams();
			params.setContentCharset(chartSet);
			if (param != null) {
				for (String key : param.keySet()) {
					httpPost.setParameter(key, param.get(key));
				}
			}
			httpPost.setRequestHeader("Cookie", "_l_u_i=" + userId + "; _u_i=" + userId);
			client.executeMethod(httpPost);
			return httpPost.getResponseBodyAsString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			httpPost.releaseConnection();
		}
	}

}
