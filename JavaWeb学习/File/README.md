# 第二十章 文件上传与下载
## 20.1 文件上传
前台：

	1. 提交方式：post
	2. 表单中有文件上传的表单项： <input type=”file” />
	3. 指定表单类型:
		默认类型：enctype="application/x-www-form-urlencoded"
		文件上传类型：multipart/form-data

## 18.2 监听器接口
Servlet中哪些对象需要监听？

	request / session / servletContext
	分别对应的是request监听器、session相关监听器、servletContext监听器

监听器接口：

	一、监听对象创建/销毁的监听器接口
		Interface ServletRequestListener     监听request对象的创建或销毁
		Interface HttpSessionListener        监听session对象的创建或销毁
		Interface ServletContextListener     监听servletContext对象的创建或销毁
	
	二、监听对象属性的变化
		Interface ServletRequestAttributeListener 监听request对象属性变化: 添加、移除、修改
		Interface HttpSessionAttributeListener    监听session对象属性变化: 添加、移除、修改
	Interface ServletContextAttributeListener  监听servletContext对象属性变化

	三、session相关监听器
		Interface HttpSessionBindingListener   监听对象绑定到session上的事件	
	    Interface HttpSessionActivationListener(了解) 监听session序列化及反序列化的事件
	    
# 第十九章  国际化
## 19.1 国际化概述
如何做到国际化的软件，要求：
1. 软件中存储特定的字符串
2. 知道浏览器当前使用哪种语言（Locale  ）

## 19.2 静态数据国际化
网站中显示的固定文本的国际化： “用户名”“密码“

国际化的软件：

	1. 存储所有国家显示的文本的字符串
		a) 文件: properties
		b) 命名：  基础名_语言简称_国家简称.properties
		例如：msg_zh_CN.properties     存储所有中文
	      	Msg_en_US.properties    存储所有英文
	2. 程序中获取
		ResourceBundle类，可以读取国际化的资源文件!

	


