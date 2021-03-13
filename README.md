# Intkr_SAAS_BEIDEN  小贝商城 S-B2B2C模式

官网：http://shop.intkr.com

在线演示网站：http://demo.shop.intkr.com

DB数据字典：http://db.doc.intkr.com/admin/db/dbInfoMgr.html?databaseId=1012023

API接口文档：http://api.doc.intkr.com/admin/api/info/mgr/api.html?app_id=1001002

用户使用文档：http://use.doc.intkr.com

管理后台前端框架开发文档：http://fe.doc.intkr.com

二次开发文档：http://dev.doc.intkr.com

主题使用文档：http://default.theme.doc.intkr.com


# 使用须知

✅允许

个人学习使用

允许用于学习、毕设等

允许进行使用，请自觉遵守使用协议，请遵守 Apache License2.0 协议，再次开源请注明出处

推荐Watch、Star项目，获取项目第一时间更新，同时也是对项目最好的支持

希望大家多多支持原创作品


# 如何交流、反馈、参与贡献？

官方社区：http://shop.intkr.com

github仓库：https://github.com/Beiden/Intkr_SAAS_BEIDEN

gitee仓库：https://gitee.com/intkr/saas-beiden

官方QQ群：443818603

官方微信：Beiden

技术讨论、二次开发等咨询、问题和建议，请移步到官方社区，我会在第一时间进行解答和回复！


# 开发计划

1 完善单商城系统模型

2 完善多商家商城系统模型

3 完善Saas版多商家商城系统模型

4 完善主题库

5 完善系统文档

6 完善电商配套的供应链管理系统


# 技术选型

1 后端使用技术

1.1 Webx

1.2 mybatis

1.3 servlet3

1.6 slf4j

1.7 fastjson

1.8 poi

1.9 velocity

1.11 mysql


2 前端使用技术

2.1 bootstrap


# 项目结构

src
 |--platform-admin 后台管理
 |--platform-api 微信小程序商城api接口
 |--platform-common 公共模块
 |--platform-framework 系统WEB合并，请打包发布此项目
 |--platform-gen 代码生成
 |--platform-mp 微信公众号模块
 |--platform-schedule 定时任务
 |--platform-shop 商城后台管理
 |--uni-mall uniapp版商城
 |--wx-mall 微信小程序原生商城


# 实现功能

一：会员管理

​	a 会员管理
​	b 会员等级
​	c 收货地址管理
​	d 会员优惠劵
​	e 会员收藏
​	f 会员足迹
​	g 搜索历史
​	h 购物车

二：商城配置

​	a 区域配置
​	b 商品属性种类
​	c 品牌制造商
​	d 商品规格
​	e 订单管理
​	f 商品类型
​	g 渠道管理
​	h 商品问答
​	i 反馈
​	j 关键词

三：商品编辑

​	a 所有商品
​	b 用户评论
​	c 产品设置
​	d 商品规格
​	e 商品回收站

四：推广管理

​	a 广告列表
​	b 广告位置
​	c 优惠劵管理
​	d 专题管理
​	e 专题分类

五：订单管理

​	a 所有订单管理

六：系统管理

​	a 管理员列表
​	b 角色管理
​	c 菜单管理
​	d SQL监控
​	e 定时任务
​	f 参数管理
​	g 代码生成器
​	h 系统日志
​	i 文件上传
​	j 通用字典表

七：短信服务平台


# 安装教程

**教程**

​	JDK安装：http://use.doc.intkr.com/index.html?articleId=48801	

​	系统下载安装：http://use.doc.intkr.com/index.html?articleId=48803

**下载**

​	链接：http://shop.intkr.com/download.html

**Window系统** 

​		下载完后，直接双击 startup - without jdk.bat 文件启动系统 

**Linux系统** 

​	先安装JDK

​	再启动系统：startup.sh

**访问本地网站**

​	链接：http://localhost:16888/

​	管理后台：http://localhost:16888/admin/index.html

​	买家帐号：buyer

​	买家密码：12345678

​	卖家帐号：seller

​	卖家密码：12345678

​	管理员帐号：admin

​	管理员密码：beiden**** 请联系客服索要



# 页面展示

首页

![标题](http://localhost:16888/IK/templates/screen/themes/Picture/index.png)

登录页面

![](http://localhost:16888/IK/templates/screen/themes/Picture/login.png)

买家后台

![](http://localhost:16888/IK/templates/screen/themes/Picture/admgr-buyer.png)

卖家后台

![](http://localhost:16888/IK/templates/screen/themes/Picture/admgr-seller.png)

小程序首页

![](http://localhost:16888/IK/templates/screen/themes/Picture/m-index.png)

小程序用户中心

![](http://localhost:16888/IK/templates/screen/themes/Picture/m-buyer.png)

管理后台

![](http://localhost:16888/IK/templates/screen/themes/Picture/mgr-index.png)