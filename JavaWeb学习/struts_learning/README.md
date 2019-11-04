# 第二十章 Struts2
## 20.1 引入jar包

	commons-fileupload-1.2.2.jar	  【文件上传相关包】
	commons-io-2.0.1.jar
	struts2-core-2.3.4.1.jar           【struts2核心功能包】
	xwork-core-2.3.4.1.jar           【Xwork核心包】
	ognl-3.0.5.jar					 【Ognl表达式功能支持表】
	commons-lang3-3.1.jar          【struts对java.lang包的扩展】
	freemarker-2.3.19.jar            【struts的标签模板库jar文件】
	javassist-3.11.0.GA.jar           【struts对字节码的处理相关jar】

## 20.2 配置web.xml

Tomcat启动- 加载自身web.xml---加载所有项目的web.xml。通过在项目的web.xml中引入过滤器。Struts的核心功能的初始化，通过过滤器完成

	<!-- 引入struts核心过滤器 -->
		<filter>
			<filter-name>struts2</filter-name>
			<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter</filter-class>
		</filter>
		<filter-mapping>
			<filter-name>struts2</filter-name>
			<url-pattern>/*</url-pattern>
		</filter-mapping>
struts2-core-2.3.4.1.jar
StrutsPrepareAndExecuteFilter  即为核心过滤器

## 20.3 开发Action

	1. action类，也叫做动作类; 一般继承ActionSupport类
		    即处理请求的类  (struts中的action类取代之前的servlet)
	2. action中的业务方法，处理具体的请求
		必须返回String
		方法不能有参数
		
	// 开发action： 处理请求
	public class HelloAction extends ActionSupport {
	
		// 处理请求
		public String execute() throws Exception {
		System.out.println("访问到了action，正在处理请求");
			System.out.println("调用service");
			return "success";
		}
	}
	
## 20.4 配置action 

	<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE struts PUBLIC
		  "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
		  "http://struts.apache.org/dtds/struts-2.0.dtd">
	<struts>
	    <package name="xxxx" extends="struts-default">
	    
	    <!-- 配置全局跳转视图 -->
		<global-results>
			<result name="success">/index.jsp</result>
		</global-results>
		
		<global-results>
			<result name="success">/data.jsp</result>
		</global-results>

		
		<!-- 通配符: http://locahost:8080/struts02/user_login -->
		<action name="user_*" class="cn.itcast.b_config2.UserAction" method="{1}">
		</action>
		
		<!-- 
		<action name="test" class="cn.itcast.b_config2.TestAction" method="execute">
			返回结果标记success对应的页面再当前action中没有配置，
			所以会去找全局配置有是否有success标记对应的页面
			method  默认为execute
		 	默认的方法execute返回值为success,对应的页面去全局视图找。
		</action>
		 -->
	    
	    	<action name="hello" class="cn.itcast.action.HelloAction" method="execute">
	    		<result name="success">/success.jsp</result>
	    		
	    		<!-- 当日期类型转换错误的时候，会跳到input视图(struts内部返回) -->
			<result name="input">/error.jsp</result>
	    	</action>
	    </package> 
	</struts>

## 20.5 Struts2执行流程

	服务器启动：
		1. 加载项目web.xml
		2. 创建Struts核心过滤器对象， 执行filter  init()
			struts-default.xml,    核心功能的初始化
			struts-plugin.xml,     struts相关插件
			struts.xml			   用户编写的配置文件

	访问：
		3. 用户访问Action, 服务器根据访问路径名称，找对应的aciton配置, 创建action对象
		4. 执行默认拦截器栈中定义的18个拦截器
		5. 执行action的业务处理方法

## 20.6 struts-default.xml, 详解

	目录：struts2-core-2.3.4.1.jar/ struts-default.xml
	 内容：
		1. bean节点指定struts在运行的时候创建的对象类型
		2.指定struts-default包  【用户写的package(struts.xml)一样要继承此包 】
			package  struts-default  包中定义了：
				a.  跳转的结果类型
					dispatcher    转发，不指定默认为转发
					redirect       重定向
					redirectAction  重定向到action资源
					stream        (文件下载的时候用)
				b. 定义了所有的拦截器
					  定义了32个拦截器！
					  为了拦截器引用方便，可以通过定义栈的方式引用拦截器，
				    此时如果引用了栈，栈中的拦截器都会被引用!
				
					defaultStack
						默认的栈，其中定义默认要执行的18个拦截器！


				c. 默认执行的拦截器栈、默认执行的action
					<default-interceptor-ref name="defaultStack"/>
		       <default-class-ref class="com.opensymphony.xwork2.ActionSupport" />


	<interceptor 
	name="prepare" class="com.opensymphony.xwork2.interceptor.PrepareInterceptor"/>
	<interceptor 
	name="params" class="com.opensymphony.xwork2.interceptor.ParametersInterceptor"/>

## 20.7 拦截器

	拦截器功能与过滤器功能类似。
			共同点： 都拦截资源！
			区别：
				过滤器，拦截器所有资源都可以；  (/index.jsp/servlet/img/css/js)
				拦截器，只拦截action请求。
			
				拦截器是struts的概念，只能在struts中用。
				过滤器是servlet的概念，可以在struts项目、servlet项目用。
				
	// 面试题： 拦截器什么时候执行？ (访问/启动)  先执行action类创建，先执行拦截器？
	// --》 1. 用户访问时候按顺序执行18个拦截器；
	//---》 2. 先执行Action类的创建，再执行拦截器； 最后拦截器执行完，再执行业务方法
	
	共性问题：
		问题1：Struts.xml配置文件没有提示
	解决a：
		找到struts-2.0.dtd文件,  拷贝到某个目录：d:/dtd /..  (不要用中文目录)
		让MyEclipse关联到上面dtd文件，
			windows preferences - 搜索xml catalog
			配置：
				Location:    上面配置的dtd目录
				Key:     -//Apache Software Foundation//DTD Struts Configuration 2.0//EN
	解决b:
		或者，
			让机器连接互联网，工具会自动下载dtd文件，缓存到MyEclipse中！
	

