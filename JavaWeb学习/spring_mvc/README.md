# 第二十三章 springmvc

## 23.1 springmvc介绍
springmvc属于spring框架的后续产品，用在基于MVC的表现层开发，类似于struts2框架
		
## 23.2 springmvc快速入门（XML版本）

步一：创建springmvc-day01这么一个web应用

步二：导入springioc，springweb , springmvc相关的jar包

步三：在/WEB-INF/下创建web.xml文件

	<!-- 注册springmvc框架核心控制器 -->
	<servlet>
		<servlet-name>DispatcherServlet</servlet-name>
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	</servlet>
	<servlet-mapping>
	<servlet-name>DispatcherServlet</servlet-name>
		<url-pattern>*.action</url-pattern>
	</servlet-mapping>
	
步四：创建HelloAction.java控制器类

	/**
	 * 控制器
	 * @author AdminTC
	 */
	public class HelloAction implements Controller{
		/**
		 * 业务方法
		 */
		public ModelAndView handleRequest(HttpServletRequest requqest,HttpServletResponse response) throws Exception {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message","这是我的第一个springmvc应用程序");
			modelAndView.setViewName("/jsp/success.jsp");
			return modelAndView;
		}
	}	
	
步五：在/WebRoot/下创建jsp/success.jsp

	<%@ page language="java" pageEncoding="UTF-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>这是我的第一个springmvc应用程序</title>
	  </head>
	  <body>
		success.jsp<br/>
		${message}
	  </body>
	</html>
	
步六：在/WEB-INF/创建DispatcherServlet-servlet.xml配置文件，xml头部信息与spring.xml相同
       注意：该配置文件的命名规则：web.xml文件中配置的<servlet-name>的值-servlet.xml
       
	<?xml version="1.0" encoding="UTF-8"?>
	<beans 
	      xmlns="http://www.springframework.org/schema/beans"
	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	      xmlns:context="http://www.springframework.org/schema/context"
	      xmlns:aop="http://www.springframework.org/schema/aop"
	      xmlns:tx="http://www.springframework.org/schema/tx"
		  xmlns:mvc="http://www.springframework.org/schema/mvc"
	      xsi:schemaLocation="
	      
		  http://www.springframework.org/schema/beans 
		  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
		  
		  http://www.springframework.org/schema/context
	      http://www.springframework.org/schema/context/spring-context-3.0.xsd
	      
	      http://www.springframework.org/schema/aop 
		  http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
		  
		  http://www.springframework.org/schema/tx
	      http://www.springframework.org/schema/tx/spring-tx-3.0.xsd
	    
	      http://www.springframework.org/schema/mvc
	      http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
		
	      ">
	      
	      <!-- 控制器(程序员) -->
	    <bean name="/hello.action" class="cn.itcast.javaee.springmvc.base.HelloAction"></bean>  
	    
	     <!-- 映射器(框架) -->  
	    <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"></bean>  
	    
	    <!-- 适配器(框架) -->  
	    <bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"></bean>  
	    
	    <!-- 视图解析器(框架) -->  
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"></bean>  
	          
	</beans>
	
步七：部署web应用到tomcat中，通过浏览器访问如下URL：

	http://127.0.0.1:8080/springmvc-day01/hello.action
		
## 23.3 加载自定义目录下的springmvc.xml配置文件

在默认情况下：springmvc框架的配置文件必须叫<servlet-name>-servlet.xml
且必须放在/WEB-INF/目录下，我们可以在web.xml文件中，为DispatcherServlet配置一个初始化参数，
让它去我们指定的目录下加载springmvc.xml配置文件，web.xml代码如下：

	<!-- 注册springmvc框架核心控制器 -->
		<servlet>
			<servlet-name>DispatcherServlet</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>/WEB-INF/classes/cn/itcast/javaee/springmvc/config/springmvc.xml</param-value>	
			</init-param>
		</servlet>
		<servlet-mapping>
			<servlet-name>DispatcherServlet</servlet-name>
			<url-pattern>*.action</url-pattern>
		</servlet-mapping>
		
如果springmvc.xml配置文件放在src目录下，web.xml代码如下：
	
	<!-- 注册springmvc框架核心控制器 -->
	<servlet>
			<servlet-name>DispatcherServlet</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>classpath:spring.xml</param-value>	
			</init-param>
		</servlet>
		<servlet-mapping>
			<servlet-name>DispatcherServlet</servlet-name>
			<url-pattern>*.action</url-pattern>
		</servlet-mapping>
		
## 23.4 视图解析器InternalResourceViewResolver【解析视图逻辑名对应的真实路径】

ModelAndView对象中即可以封装真实视图路径名，也可以封装视图路径的逻辑名，springmvc.xml
代码如下：

	<!-- 视图解析器(框架) -->  
	    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	    	<property name="prefix" value="/jsp/"/>
	    	<property name="suffix" value=".jsp"/>
	    </bean>

Action类代码如下：

	modelAndView.setViewName("success");
		
## 23.5 映射器Mapping【什么样的请求交给Action】

1. org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping(核心) 将程序员定义的Action所对应的<bean>标签的name属性作为请求路径

		<!-- 注册控制器(程序员) -->
	      <bean name="/add.action" class="cn.itcast.javaee.springmvc.mapping.UserAction"></bean>

	      <!-- 注册映射器(handler包)(框架) -->
	      		class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
	
2.  org.springframework.web.servlet.handler.SimpleUrlHandlerMapping/delete.action和/update.action和/find.action请求路径都交由<bean>标签为id的Action，即多个路径对应同一个Action 

	<!-- 注册控制器(程序员) -->
		  <bean id="userActionID" class="cn.itcast.javaee.springmvc.mapping.UserAction"></bean>
		
		  <!-- 注册映射器(handler包)(框架) -->
		  <bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
		  		<property name="mappings">
		  			<props>
		  				<prop key="/delete.action">userActionID</prop>
		  				<prop key="/update.action">userActionID</prop>
		  				<prop key="/find.action">userActionID</prop>
		  			</props>
		  		</property>
		  </bean>

## 23.6 适配器Adapter【Action实现什么接口】

1） Action实现Controller接口,org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter

	public class EmpAction implements Controller{
		public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception {
			System.out.println("EmpAction::handleRequest");
			request.setCharacterEncoding("UTF-8");
			String username = request.getParameter("username");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("message",username);
			modelAndView.setViewName("/jsp/success.jsp");
			return modelAndView;
		}
	}

## 23.7 控制器Controller【Action继承什么类】

1. org.springframework.web.servlet.mvc.ParameterizableViewController
如果请求是/hello.action的请求路径，则直接跳转到/jsp/success.jsp页面，不经过程序员定义的控制器Action

		 <!-- /index.action请求，直接转发到/index.jsp页面 -->
		      <bean name="/index.action" class="org.springframework.web.servlet.mvc.ParameterizableViewController">
		      		<property name="viewName" value="/index.jsp"/>
		      </bean>
		      
		      
		     <!-- 注册视图解析器(view包)(框架) 
			  <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
			  		<property name="prefix" value="/"/>
			  		<property name="suffix" value=".jsp"/>
			  </bean>
		      -->

2. org.springframework.web.servlet.mvc.AbstractCommandController
能够以实体的形式，收集客户端参数

		public class AdminAction extends AbstractCommandController{
			public AdminAction(){
				this.setCommandClass(Admin.class);
			}
			@Override
			protected ModelAndView handle(HttpServletRequest request,HttpServletResponse response, Object obj, BindException bindException)throws Exception {
				System.out.println("AdminAction::handle");
				ModelAndView modelAndView = new ModelAndView();
				Admin admin = null;
				if(obj instanceof Admin){
					admin = (Admin) obj;
				}
				modelAndView.addObject("username",admin.getUsername());
				modelAndView.addObject("gender",admin.getGender());
				modelAndView.addObject("hiredate",admin.getHiredate());
				modelAndView.setViewName("/jsp/success.jsp");
						return modelAndView;
			}
		}
		
		 <!-- 请求处理类 -->
		      <bean name="/add.action" class="cn.itcast.javaee.springmvc.controller.AdminAction">
		      </bean>
		      
		      <!-- 映射器 -->
			  <bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping">
			  </bean>
					
## 23.8 日期转换器和编码过滤器

1. 在默认情况下，springmvc不能将String类型转成Date类型，必须自定义类型转换器

		public class AdminAction extends AbstractCommandController{
			@Override
			protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
				binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
			}
			
2. spring提供的，专用于解决POST提交中文乱码问题，需要在web.xml文件中配置

		<!-- 编码过滤器 -->
			<filter>
				<filter-name>CharacterEncodingFilter</filter-name>
				<filter-class>
			org.springframework.web.filter.CharacterEncodingFilter
				</filter-class>
				<init-param>
					<param-name>encoding</param-name>
					<param-value>UTF-8</param-value>
				</init-param>
			</filter>
			<filter-mapping>
				<filter-name>CharacterEncodingFilter</filter-name>
				<url-pattern>/*</url-pattern>
			</filter-mapping>
			
## 23.9 springmvc快速入门（注解版本）

步一：创建springmvc-day02这么一个web应用

步二：导入springioc，springweb和springmvc相关的jar包

	------------------------------------------------------springWEB模块
	   org.springframework.web-3.0.5.RELEASE.jar
	org.springframework.web.servlet-3.0.5.RELEASE.jar（mvc专用）
	   ------------------------------------------------------springIOC模块
	   org.springframework.asm-3.0.5.RELEASE.jar
	   org.springframework.beans-3.0.5.RELEASE.jar
	   org.springframework.context-3.0.5.RELEASE.jar
	   org.springframework.core-3.0.5.RELEASE.jar
	   org.springframework.expression-3.0.5.RELEASE.jar

步三：在/WEB-INF/下创建web.xml文件

	<servlet>
			<servlet-name>DispatcherServlet</servlet-name>
			<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
			<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>classpath:spring.xml</param-value>
			</init-param>
		</servlet>
		<servlet-mapping>
			<servlet-name>DispatcherServlet</servlet-name>
			<url-pattern>*.action</url-pattern>
		</servlet-mapping>

 步四：创建HelloAction.java控制器类
 
	 @Controller
	public class HelloAction{
		@RequestMapping(value="/hello")
		public String helloMethod(Model model) throws Exception{
			System.out.println("HelloAction::helloMethod()");
			model.addAttribute("message","这是我的第二个springmvc应用程序");
			return "/success.jsp";
		}	
	}
	
步五：在/WebRoot/下创建success.jsp

		<%@ page language="java" pageEncoding="UTF-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>这是我的第二个springmvc应用程序</title>
	  </head>
	  <body>
		success.jsp<br/>
		${message}
	  </body>
	</html>
	
步六：在/src/目录下创建spring.xml配置文件

		<?xml version="1.0" encoding="UTF-8"?>
		<beans 
		      xmlns="http://www.springframework.org/schema/beans"
		      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		      xmlns:context="http://www.springframework.org/schema/context"
		      xmlns:aop="http://www.springframework.org/schema/aop"
		      xmlns:tx="http://www.springframework.org/schema/tx"
			  xmlns:mvc="http://www.springframework.org/schema/mvc"
		
		      xsi:schemaLocation="
	
			  http://www.springframework.org/schema/beans 
			  http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
			  
			  http://www.springframework.org/schema/context
		      http://www.springframework.org/schema/context/spring-context-3.0.xsd
		    
		      http://www.springframework.org/schema/mvc
		      http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd
		
		      ">
		

			  <!-- Action控制器 -->
			  <context:component-scan base-package="cn.itcast.javaee.springmvc.helloannotation"/> 
			  
			  <!-- 基于注解的映射器(可选) -->
		      <bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping"/>
		      
		      <!-- 基于注解的适配器(可选) -->
		      <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter"/>
		      
		      <!-- 视图解析器(可选) -->
		      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"/>
		      	
		</beans>
		
步七：部署web应用到tomcat中，通过浏览器访问如下URL：

 	http://127.0.0.1:8080/springmvc-day02/hello.action
 	
## 23.10 一个Action中，可以写多个类似的业务控制方法

1. 通过模块根路径 + 功能子路径 = 访问模块下子功能的路径

		@Controller
		@RequestMapping(value="/user")
		public class UserAction{
			@RequestMapping(value="/add")
			public String add(Model model) throws Exception{
				System.out.println("HelloAction::add()");
				model.addAttribute("message","增加用户");
				return "/success.jsp";
			}
			@RequestMapping(value="/find")
			public String find(Model model) throws Exception{
				System.out.println("HelloAction::find()");
				model.addAttribute("message","查询用户");
				return "/success.jsp";
			}	
		}

## 23.11 在业务控制方法中写入普通变量收集参数

1. 可以在业务控制方法中，以参数形式收集客户端参数，springmvc采用方法参数形式的

		@Controller
		@RequestMapping(value="/user")
		public class UserAction{
			@RequestMapping(value="/add")
			public String add(Model model,int id,String name,Double sal) throws Exception{
				System.out.println("HelloAction::add()");
				System.out.println(id + ":" + name + ":" + sal);
				model.addAttribute("message","增加用户");
				return "/success.jsp";
			}	
		}
		
http://127.0.0.1:8080/myspringmvc-day02/user/add.action?id=1&name=zhaojun&sal=5000

## 23.12 限定某个业务控制方法，只允许GET或POST请求方式访问
	
1. 可以在业务控制方法前，指明该业务控制方法只能接收GET或POST的请求

		@Controller
		@RequestMapping(value="/user")
		public class UserAction{
			@RequestMapping(value="/add",method=RequestMethod.POST)
			public String add(Model model,int id,String name,double sal) throws Exception{
				System.out.println("HelloAction::add()::POST");
				System.out.println(id + ":" + name + ":" + sal);
				model.addAttribute("message","增加用户");
				return "/success.jsp";
			}	
		}

 如果不书写method=RequestMethod.POST的话，GET和POST请求都支持
 
## 23.13 在业务控制方法中写入Request，Response等传统web参数

1. 可以在业务控制方法中书写传统web参数，这种方式我们不提倡，耦合了

		@Controller
		@RequestMapping(value="/user")
		public class UserAction{
			@RequestMapping(value="/add",method=RequestMethod.POST)
			public void add(HttpServletRequest request,HttpServletResponse response) throws Exception{
				System.out.println("HelloAction::add()::POST");
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				double sal = Double.parseDouble(request.getParameter("sal"));
				System.out.println(id + ":" + name + ":" + sal);
				request.getSession().setAttribute("id",id);
				request.getSession().setAttribute("name",name);
				request.getSession().setAttribute("sal",sal);
				response.sendRedirect(request.getContextPath()+"/register.jsp");
			}	
		}
		
## 23.14 在业务控制方法中写入模型变量收集参数，且使用@InitBind来解决字符串转日期类型

1. 在默认情况下，springmvc不能将String类型转成java.util.Date类型，所有我们只能在Action中自定义类型转换器

		<form action="${pageContext.request.contextPath}/user/add.action" method="POST">
				编号：<input type="text" name="id" value="${id}"/><br/>
				姓名：<input type="text" name="name" value="${name}"/><br/>
				薪水：<input type="text" name="sal" value="${sal}"/><br/>
				入职时间：<input type="text" name="hiredate" value='<fmt:formatDate value="${hiredate}" type="date"/>'/><br/>
				<input type="submit" value="注册"/>
			</form>


		@Controller
		@RequestMapping(value = "/user")
		public class UserAction {
			@InitBinder
			protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
				binder.registerCustomEditor(
						Date.class, 
						new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
			}
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public String add(int id, String name, double sal, Date hiredate,
					Model model) throws Exception {
				System.out.println("HelloAction::add()::POST");
				model.addAttribute("id", id);
				model.addAttribute("name", name);
				model.addAttribute("sal", sal);
				model.addAttribute("hiredate", hiredate);
				return "/register.jsp";
			}
		}
		
## 23.15 在业务控制方法中写入User，Admin多个模型收集参数

		@Controller
		@RequestMapping(value = "/user")
		public class UserAction {
			@InitBinder
			protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
				binder.registerCustomEditor(
						Date.class, 
						new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
			}
			@RequestMapping(value = "/add", method = RequestMethod.POST)
			public String add(User user,Model model) throws Exception {
				System.out.println("HelloAction::add()::POST");
				model.addAttribute("user",user);
				return "/register.jsp";
			}
		}
		
## 23.16 在业务控制方法中写入包装User的模型来收集参数

User.java

	public class User {
		private Integer id;
		private String name;
		private Double sal;
		private Date hiredate;
		public User(){}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
		return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getSal() {
			return sal;
		}
		public void setSal(Double sal) {
			this.sal = sal;
		}
		public Date getHiredate() {
			return hiredate;
		}
		public void setHiredate(Date hiredate) {
			this.hiredate = hiredate;
		}
		@Override
		public String toString() {
			return this.id + ":" + this.name + ":" + this.sal + ":" + this.hiredate;
		}
	}
	
Bean.java

	public class Bean {
		private User user;
		private Admin admin;
		public Bean(){}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public Admin getAdmin() {
			return admin;
		}
		public void setAdmin(Admin admin) {
			this.admin = admin;
		}
	}

PersonAction.java

	@Controller
	@RequestMapping(value = "/person")
	public class PersonAction {
		@InitBinder
		protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception {
			binder.registerCustomEditor(
					Date.class, 
					new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
		}
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public String add(Bean bean,Model model) throws Exception {
			System.out.println(bean.getUser());
			System.out.println(bean.getAdmin());
			System.out.println("PersonAction::add()::POST");
			model.addAttribute("bean",bean);
			return "/register.jsp";
		}
	}
	
register.jsp

	普通用户
		<form action="${pageContext.request.contextPath}/person/add.action" method="POST">
			编号：<input type="text" name="user.id" value="${bean.user.id}"/><br/>
			姓名：<input type="text" name="user.name" value="${bean.user.name}"/><br/>
			薪水：<input type="text" name="user.sal" value="${bean.user.sal}"/><br/>
			入职时间：<input type="text" name="user.hiredate" value='<fmt:formatDate value="${bean.user.hiredate}" type="both" />'/><br/>
			<input type="submit" value="注册"/>
		</form>
		
## 23.17 在业务控制方法中收集数组参数

批量删除用户

	@Controller
	@RequestMapping(value="/user")
	public class UserAction {
		@RequestMapping(value="/delete")
		public String deleteMethod(int[] ids,Model model) throws Exception{
			System.out.println("UserAction::deleteMethod()");
			System.out.println("需要删除的id为：");
			for(int id : ids){
				System.out.print(id+" ");
			}
			model.addAttribute("message","批量删除成功");
			return "/success.jsp";
		}
	}
	
## 23.18 在业务控制方法中收集List<JavaBean>参数

批量注册用户
UserAction.java

	@Controller
	@RequestMapping(value="/user")
	public class UserAction {
		@RequestMapping(value="/addAll")
		public String addAll(Bean bean,Model model) throws Exception{
			for(User user : bean.getUserList()){
				System.out.println(user.getName()+":"+user.getGender());
			}
			model.addAttribute("message","批量增加用户成功");
			return "/success.jsp";
		}
	}
	
Bean.java

	public class Bean {
		private List<User> userList = new ArrayList<User>();
		public Bean(){}
		public List<User> getUserList() {
			return userList;
		}
		public void setUserList(List<User> userList) {
			this.userList = userList;
			}
	}
	
registerAll.java

	<form action="${pageContext.request.contextPath}/user/addAll.action" method="POST"> 
			 
			姓名：<input type="text" name="userList[0].name" value="哈哈"/>
			性别：<input type="text" name="userList[0].gender" value="男"/>
			<hr/>
		
			姓名：<input type="text" name="userList[1].name" value="呵呵"/>
			性别：<input type="text" name="userList[1].gender" value="男"/>
			<hr/>

			姓名：<input type="text" name="userList[2].name" value="嘻嘻"/>
			性别：<input type="text" name="userList[2].gender" value="女"/>
			<hr/>
			
			<input type="submit" value="批量注册"/>
		
	</form>
	
## 23.19 结果的转发和重定向

1） 在转发情况下，共享request域对象，会将参数从第一个业务控制方法传入第二个业务控制方法，反之，重定向则不行 .删除id=10号的用户，再查询用户

	@Controller
	@RequestMapping(value="/user")
	public class UserAction {

		@RequestMapping(value="/delete")
		public String delete(int id) throws Exception{
			System.out.println("删除用户->" + id);
			//转发到find()
			return "forward:/user/find.action";
			//重定向到find()
			//return "redirect:/user/find.action";
		}
	
		@RequestMapping(value="/find")
		public String find(int id) throws Exception{
			System.out.println("查询用户->" + id);
			return "/success.jsp";
		}
	
	}
	
## 23.20 异步发送表单数据到JavaBean，并响应JSON文本返回

1. 提交表单后，将JavaBean信息以JSON文本形式返回到浏览器

bean2json.jsp

	<form>
			编号：<input type="text" name="id" value="1"/><br/>
			姓名：<input type="text" name="name" value="哈哈"/><br/>
			薪水：<input type="text" name="sal" value="5000"/><br/>
			<input type="button" value="异步提交注册"/>
		</form>
	
		<script type="text/javascript">
			$(":button").click(function(){
				var url = "${pageContext.request.contextPath}/user/add.action";
				var sendData = {
					"id":1,
					"name":"哈哈",
					"sal":5000
				};
				$.post(url,sendData,function(backData,textStatus,ajax){
					alert(ajax.responseText);
				});
			});
		</script>
		
User.java

	public class User {
		private Integer id;
		private String name;
		private Double sal;
		public User(){}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Double getSal() {
			return sal;
		}
		public void setSal(Double sal) {
			this.sal = sal;
		}
	}
	
UserAction.java

	@Controller
	@RequestMapping(value="/user")
	public class UserAction {

		@RequestMapping(value="/add")
		public @ResponseBody User add(User user) throws Exception{
			System.out.println(user.getId()+":"+user.getName()+":"+user.getSal());
			return user;
		}
	
	}
	
spring.xml

	 <!-- Action控制器 -->
		  <context:component-scan base-package="cn.itcast.javaee.springmvc.app25"/>  	


	  	  <!-- 配适器 -->
	      <bean class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	      		<property name="messageConverters">
	      	   		<list>
	      				<bean class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter"/>
	      	   		</list>
	      		</property>
	      </bean>
