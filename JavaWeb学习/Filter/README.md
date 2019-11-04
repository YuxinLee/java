# 第十七章 Filter
## 17.1 过滤器的设计执行流程
1. 用户访问服务器
2. 过滤器： 对Servlet请求进行拦截
3. 先进入过滤器， 过滤器处理
4. 过滤器处理完后， 在放行， 此时，请求到达Servlet/JSP
5. Servlet处理
6. Servlet处理完后，再回到过滤器, 最后在由tomcat服务器相应用户；

## 17.2 过滤器相关Api

	|-- interface  Filter				过滤器核心接口
		void  init(filterConfig);    初始化方法，在服务器启动时候执行
		void  doFilter(request,response,filterChain);   过滤器拦截的业务处理方法
		void destroy();   			销毁过滤器实例时候调用
		
	|-- interface  FilterConfig   获取初始化参数信息
		String getInitParameter(java.lang.String name) 
		Enumeration getInitParameterNames() 
		
	|-- interface  FilterChain     过滤器链参数；一个个过滤器形成一个执行链；
		void doFilter(ServletRequest request, ServletResponse response)  ;  执行下一个过滤器或放行

## 17.3 对指定的请求拦截

	/*   表示拦截所有的请求
	
	默认拦截的类型：(直接访问或者重定向)
			<dispatcher>REQUEST</dispatcher>
	拦截转发：
			  <dispatcher>FORWARD</dispatcher>
	拦截包含的页面(RequestDispatcher.include(/page.jsp);    对page.jsp也执行拦截)
			  <dispatcher>INCLUDE</dispatcher>
	拦截声明式异常信息：
			<dispatcher>ERROR</dispatcher>
			
		<!-- 4. 指定拦截指定的类型 -->
	  <url-pattern>/*</url-pattern>
	  <dispatcher>REQUEST</dispatcher>
	  <dispatcher>FORWARD</dispatcher>



