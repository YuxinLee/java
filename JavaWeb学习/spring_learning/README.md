# 第二十二章 Spring

## 22.1 对象创建细节

		对象数量
		    action  多个   【维护成员变量】
		    service 一个   【不需要维护公共变量】
		    dao     一个   【不需要维护公共变量】
		    
		创建时间
		    action    访问时候创建
		    service   启动时候创建
		    dao       启动时候创建
		    
		对象的依赖关系
			action 依赖 service
			service依赖 dao
			
## 22.2 组件/框架设计

	侵入式设计
			引入了框架，对现有的类的结构有影响；即需要实现或继承某些特定类。
			例如：	Struts框架
	非侵入式设计
		引入了框架，对现有的类结构没有影响。
		例如：Hibernate框架 / Spring框架
		
	控制反转:
		Inversion on Control , 控制反转 IOC
		对象的创建交给外部容器完成，这个就做控制反转.
		
	依赖注入，  dependency injection 
		处理对象的依赖关系
		
		区别：
	 	控制反转， 解决对象创建的问题 【对象创建交给别人】
		依赖注入，在创建完对象后， 对象的关系的处理就是依赖注入 【通过set方法依赖注入】
		
		AOP
		面向切面编程。切面，简单来说来可以理解为一个类，由很多重复代码形成的类。
		切面举例：事务、日志、权限;
		
## 22.3 Spring框架

Spring框架，可以解决对象创建以及对象之间依赖关系的一种框架。

	Spring提供了一站式解决方案：
		1） Spring Core  spring的核心功能： IOC容器, 解决对象创建及依赖关系
		2） Spring Web  Spring对web模块的支持。
							 可以与struts整合,让struts的action创建交给spring
						    spring mvc模式
		3） Spring DAO  Spring 对jdbc操作的支持  【JdbcTemplate模板工具类】
		4） Spring ORM  spring对orm的支持： 
							既可以与hibernate整合，【session】
							也可以使用spring的对hibernate操作的封装
		5）Spring AOP  切面编程
		6）SpringEE   spring 对javaEE其他模块的支持
		
## 22.4 开发步骤

	1） 源码, jar文件：spring-framework-3.2.5.RELEASE
		commons-logging-1.1.3.jar           日志
		spring-beans-3.2.5.RELEASE.jar        bean节点
		pring-context-3.2.5.RELEASE.jar       spring上下文节点
		spring-core-3.2.5.RELEASE.jar         spring核心功能
		spring-expression-3.2.5.RELEASE.jar    spring表达式相关表
		
	2） 核心配置文件: applicationContext.xml  
		Spring配置文件：applicationContext.xml / bean.xml
		
	public class App {

	// 1. 通过工厂类得到IOC容器创建的对象
	@Test
	public void testIOC() throws Exception {
		// 创建对象
		// User user = new User();
		
		// 现在，把对象的创建交给spring的IOC容器
		Resource resource = new ClassPathResource("cn/itcast/a_hello/applicationContext.xml");
		// 创建容器对象(Bean的工厂), IOC容器 = 工厂类 + applicationContext.xml
		BeanFactory factory = new XmlBeanFactory(resource);
		// 得到容器创建的对象
		User user = (User) factory.getBean("user");
		
		System.out.println(user.getId());
		
	}
	
	//2. （方便）直接得到IOC容器对象 
	@Test
	public void testAc() throws Exception {
		// 得到IOC容器对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/a_hello/applicationContext.xml");
		// 从容器中获取bean
		User user = (User) ac.getBean("user");
		
		System.out.println(user);
		}
	}
	
## 22.5 bean对象创建的细节

	/**
		 * 1) 对象创建： 单例/多例
		 * 	scope="singleton", 默认值， 即 默认是单例	【service/dao/工具类】
		 *  scope="prototype", 多例； 				【Action对象】
		 * 
		 * 2) 什么时候创建?
		 * 	  scope="prototype"  在用到对象的时候，才创建对象。
		 *    scope="singleton"  在启动(容器初始化之前)， 就已经创建了bean，且整个应用只有一个。
		 * 3)是否延迟创建
		 * 	  lazy-init="false"  默认为false,  不延迟创建，即在启动时候就创建对象
		 * 	  lazy-init="true"   延迟初始化， 在用到对象的时候才创建对象
		 *    （只对单例有效）
		 * 4) 创建对象之后，初始化/销毁
		 * 	  init-method="init_user"       【对应对象的init_user方法，在对象创建爱之后执行 】
		 *    destroy-method="destroy_user"  【在调用容器对象的destriy方法时候执行，(容器用实现类)】
		 */
		 
	@Test
		public void testIOC() throws Exception {
			// 得到IOC容器对象  【用实现类，因为要调用销毁的方法】
			ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/a_hello/applicationContext.xml");
			System.out.println("-----容器创建-----");
		
			// 从容器中获取bean
			User user1 = (User) ac.getBean("user");
			User user2 = (User) ac.getBean("user");
		
			System.out.println(user1);
			System.out.println(user2);
		
			// 销毁容器对象 
			ac.destroy();
		}

## 22.6 SpringIOC容器

SpringIOC容器，是spring核心内容。创建对象 & 处理对象的依赖关系。

IOC容器创建对象：

	创建对象, 有几种方式：
	1） 调用无参数构造器
	2） 带参数构造器
	3） 工厂创建对象
			工厂类，静态方法创建对象
			工厂类，非静态方法创建对象
			
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:p="http://www.springframework.org/schema/p"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd">
	
		<!-- ###############对象创建############### -->
	
		<!-- 1. 默认无参数构造器 
		<bean id="user1" class="cn.itcast.b_create_obj.User"></bean>
		-->
		<!-- 2. 带参数构造器 -->
		<bean id="user2" class="cn.itcast.b_create_obj.User">
			<constructor-arg index="0" type="int" value="100"></constructor-arg>
			<constructor-arg index="1" type="java.lang.String" value="Jack"></constructor-arg>
		</bean>
	
		<!-- 定义一个字符串，值是"Jack" ;  String s = new String("jack")-->
		<bean id="str" class="java.lang.String">
			<constructor-arg value="Jacks"></constructor-arg>
		</bean>
		<bean id="user3" class="cn.itcast.b_create_obj.User">
			<constructor-arg index="0" type="int" value="100"></constructor-arg>
			<constructor-arg index="1" type="java.lang.String" ref="str"></constructor-arg>
		</bean>
	
		<!-- 3. 工厂类创建对象 -->
		<!-- # 3.1 工厂类，实例方法 -->
		<!-- 先创建工厂 -->
		<bean id="factory" class="cn.itcast.b_create_obj.ObjectFactory"></bean>
		<!-- 在创建user对象，用factory方的实例方法 -->
		<bean id="user4" factory-bean="factory" factory-method="getInstance"></bean>
	
		<!-- # 3.2 工厂类： 静态方法 -->
		<!-- 
			class 指定的就是工厂类型
			factory-method  一定是工厂里面的“静态方法”
		 -->
		<bean id="user" class="cn.itcast.b_create_obj.ObjectFactory" factory-method="getStaticInstance"></bean>
	
	</beans>     
	
对象依赖关系

	Spring中，如何给对象的属性赋值?  【DI, 依赖注入】
		1) 通过构造函数
		2) 通过set方法给属性注入值
		3) p名称空间
		4)自动装配(了解)
		5) 注解

(常用)Set方法注入值

	<!-- dao instance -->
		<bean id="userDao" class="cn.itcast.c_property.UserDao"></bean>

		<!-- service instance -->
		<bean id="userService" class="cn.itcast.c_property.UserService">
			<property name="userDao" ref="userDao"></property>
		</bean>
	
		<!-- action instance -->
		<bean id="userAction" class="cn.itcast.c_property.UserAction">
			<property name="userService" ref="userService"></property>
		</bean>
		
内部bean

	<!-- ##############内部bean############## -->
		<bean id="userAction" class="cn.itcast.c_property.UserAction">
			<property name="userService">
				<bean class="cn.itcast.c_property.UserService">
					<property name="userDao">
						<bean class="cn.itcast.c_property.UserDao"></bean>
					</property>
				</bean>
			</property>
		</bean>
		
p 名称空间注入属性值 (优化)

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:p="http://www.springframework.org/schema/p"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd">
	
		<!-- ###############对象属性赋值############### -->
		<!-- 
			给对象属性注入值：
				# p 名称空间给对象的属性注入值
				 (spring3.0以上版本才支持)
		 -->
		 <bean id="userDao" class="cn.itcast.c_property.UserDao"></bean>
		 
		 <bean id="userService" class="cn.itcast.c_property.UserService" p:userDao-ref="userDao"></bean>
		 
		 <bean id="userAction" class="cn.itcast.c_property.UserAction" p:userService-ref="userService"></bean>
		 
		 <!-- 传统的注入： 
		 <bean id="user" class="cn.itcast.c_property.User" >
		 	<property name="name" value="xxx"></property>
		 </bean>
		-->
		<!-- p名称空间优化后 -->
		<bean id="user" class="cn.itcast.c_property.User" p:name="Jack0001"></bean>
		 
	</beans>   
	
自动装配(了解)

	根据名称自动装配：autowire="byName"
	
	自动去IOC容器中找与属性名同名的引用的对象，并自动注入
		<!-- ###############自动装配############### -->  
		<bean id="userDao" class="cn.itcast.d_auto.UserDao"></bean>	
		<bean id="userService" class="cn.itcast.d_auto.UserService" autowire="byName"></bean>
		<!-- 根据“名称”自动装配： userAction注入的属性，会去ioc容器中自动查找与属性同名的对象 -->
		<bean id="userAction" 
	class="cn.itcast.d_auto.UserAction" autowire="byName"></bean>
	
	也可以定义到全局， 这样就不用每个bean节点都去写autowire=”byName”
		<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:p="http://www.springframework.org/schema/p"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd" default-autowire="byName">   根据名称自动装配（全局）
	
		<!-- ###############自动装配############### -->  
		<bean id="userDao" class="cn.itcast.d_auto.UserDao"></bean>	
		<bean id="userService" class="cn.itcast.d_auto.UserService"></bean>
		<bean id="userAction" class="cn.itcast.d_auto.UserAction"></bean>
	</beans>   
	
	根据类型自动装配：autowire="byType" 必须确保改类型在IOC容器中只有一个对象；否则报错。
		<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:p="http://www.springframework.org/schema/p"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd" default-autowire="byType">
		
			<!-- ###############自动装配############### -->  
		<bean id="userDao" class="cn.itcast.d_auto.UserDao"></bean>	
		<bean id="userService" class="cn.itcast.d_auto.UserService"></bean>
	
		<!-- 如果根据类型自动装配： 必须确保IOC容器中只有一个该类型的对象 -->
		<bean id="userAction" class="cn.itcast.d_auto.UserAction"></bean>
	
	
		<!--   报错： 因为上面已经有一个该类型的对象，且使用了根据类型自动装配
		<bean id="userService_test" class="cn.itcast.d_auto.UserService" autowire="byType"></bean>
		 -->
	</beans>  
	
注解方式可以简化spring的IOC容器的配置!

	使用注解步骤：
		1）先引入context名称空间
			xmlns:context="http://www.springframework.org/schema/context"
		2）开启注解扫描
			<context:component-scan base-package="cn.itcast.e_anno2"></context:component-scan>
		3）使用注解
			通过注解的方式，把对象加入ioc容器。

		   创建对象以及处理对象依赖关系，相关的注解：
			@Component   指定把一个对象加入IOC容器

	@Repository   作用同@Component； 在持久层使用
	@Service      作用同@Component； 在业务逻辑层使用
	@Controller    作用同@Component； 在控制层使用 

	@Resource     属性注入
	
	
## 22.7 代理模式

代理（Proxy）是一种设计模式， 提供了对目标对象另外的访问方式；即通过代理访问目标对象。 这样好处： 可以在目标对象实现的基础上，增强额外的功能操作。(扩展目标对象的功能)。
代理模式的关键点： 代理对象与目标对象。

静态代理:

	静态代理，
		1） 代理对象，要实现与目标对象一样的接口；
		2） 举例:
				保存用户(模拟)
					Dao  ,  直接保存
					DaoProxy, 给保存方法添加事务处理
					
	总结静态代理：
		1）可以做到在不修改目标对象的功能前提下，对目标对象功能扩展。
		2）缺点：
			--》  因为代理对象，需要与目标对象实现一样的接口。所以会有很多代理类，类太多。
			--》  一旦接口增加方法，目标对象与代理对象都要维护。

	解决：
		代理工厂？  可以使用动态代理。
		
 动态代理:
 
	 动态代理，
		1）代理对象，不需要实现接口；
		2）代理对象的生成，是利用JDKAPI， 动态的在内存中构建代理对象(需要我们指定创建 代理对象/目标对象 实现的接口的类型；);
		3)  动态代理， JDK代理， 接口代理；

	JDK中生成代理对象的API：
	|-- Proxy
		static Object newProxyInstance(
	ClassLoader loader,       指定当前目标对象使用类加载器
	 Class<?>[] interfaces,     目标对象实现的接口的类型
	InvocationHandler h       事件处理器
	)  
	
	动态代理总结：
		代理对象不需要实现接口，但是目标对象一定要实现接口；否则不能用动态代理！
		(class  $Proxy0  implements IuserDao)
		
Cglib代理:

		Cglib代理，也叫做子类代理。在内存中构建一个子类对象从而实现对目标对象功能的扩展。
		JDK的动态代理有一个限制，就是使用动态代理的对象必须实现一个或多个接口。如果想代理没有实现接口的类，就可以使用CGLIB实现。 
	  	CGLIB是一个强大的高性能的代码生成包，它可以在运行期扩展Java类与实现Java接口。它广泛的被许多AOP的框架使用，例如Spring AOP和dynaop，为他们提供方法的interception（拦截）。 
	 	CGLIB包的底层是通过使用一个小而快的字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
	 	
	 	Cglib子类代理：
	1) 需要引入cglib – jar文件， 但是spring的核心包中已经包括了cglib功能，所以直接引入spring-core-3.2.5.jar即可。
	2）引入功能包后，就可以在内存中动态构建子类
	3）代理的类不能为final， 否则报错。
	4） 目标对象的方法如果为final/static, 那么就不会被拦截，即不会执行目标对象额外的业务方法。
	
	在Spring的AOP编程中，
	如果加入容器的目标对象有实现接口，用JDK代理；
	如果目标对象没有实现接口，用Cglib代理；
	
## 22.8 手动实现AOP编程

AOP 面向切面的编程，AOP可以实现“业务代码”与“关注点代码”分离

	// 保存一个用户
	public void add(User user) { 
			Session session = null; 
			Transaction trans = null; 
			try { 
				session = HibernateSessionFactoryUtils.getSession();   // 【关注点代码】
				trans = session.beginTransaction();    // 【关注点代码】
				 
				session.save(user);     // 核心业务代码
			
				trans.commit();     //…【关注点代码】

			} catch (Exception e) {     
				e.printStackTrace(); 
				if(trans != null){ 
					trans.rollback();   //..【关注点代码】

				} 
			} finally{ 
				HibernateSessionFactoryUtils.closeSession(session);   ////..【关注点代码】

			} 
	   } 

	分析总结：
		关注点代码，就是指重复执行的代码。
		业务代码与关注点代码分离，好处？
		   --关注点代码写一次即可；
			-开发者只需要关注核心业务；
			-运行时期，执行核心业务代码时候动态植入关注点代码； 【代理】
			
## 22.9 AOP编程

	Aop，  aspect object programming  面向切面编程
		功能： 让关注点代码与业务代码分离！
		
	关注点,
		重复代码就叫做关注点；
		
	切面，
		 关注点形成的类，就叫切面(类)！
		 面向切面编程，就是指 对很多功能都有的重复的代码抽取，再在运行的时候网业务方法上动态植入“切面类代码”。
		 
	切入点，
		执行目标对象方法，动态植入切面代码。
		可以通过切入点表达式，指定拦截哪些类的哪些方法； 给指定的类在运行的时候植入切面类代码。
		
## 22.10 注解方式实现AOP编程

	步骤：
	1） 先引入aop相关jar文件    	（aspectj  aop优秀组件）					
		spring-aop-3.2.5.RELEASE.jar   【spring3.2源码】
	aopalliance.jar				  【spring2.5源码/lib/aopalliance】
	aspectjweaver.jar			  【spring2.5源码/lib/aspectj】或【aspectj-1.8.2\lib】
	aspectjrt.jar				  【spring2.5源码/lib/aspectj】或【aspectj-1.8.2\lib】

	注意： 用到spring2.5版本的jar文件，如果用jdk1.7可能会有问题。
			需要升级aspectj组件，即使用aspectj-1.8.2版本中提供jar文件提供。

	2） bean.xml中引入aop名称空间

	3） 开启aop注解

	4) 使用注解
	@Aspect							指定一个类为切面类		
	@Pointcut("execution(* cn.itcast.e_aop_anno.*.*(..))")  指定切入点表达式

	@Before("pointCut_()")				前置通知: 目标方法之前执行
	@After("pointCut_()")					后置通知：目标方法之后执行（始终执行）
	@AfterReturning("pointCut_()")		    返回后通知： 执行方法结束前执行(异常不执行)
	@AfterThrowing("pointCut_()")			异常通知:  出现异常时候执行
	@Around("pointCut_()")				环绕通知： 环绕目标方法执行
	

1. IUserDao.java

		// 接口
		public interface IUserDao {
			void save();
		}
	
2. UserDao.java

		/**
		 * 目标对象
		 * @author Jie.Yuan
		 *
		 */
		@Component   // 加入容器
		public class UserDao implements IUserDao{

			@Override
			public void save() {
				System.out.println("-----核心业务：保存！！！------"); 
			}
		}

3. Aop.java  切面类

		@Component
		@Aspect  // 指定当前类为切面类
		public class Aop {

		// 指定切入点表单式： 拦截哪些方法； 即为哪些类生成代理对象
	
		@Pointcut("execution(* cn.itcast.e_aop_anno.*.*(..))")
		public void pointCut_(){
		}
	
		// 前置通知 : 在执行目标方法之前执行
		@Before("pointCut_()")
		public void begin(){
			System.out.println("开始事务/异常");
		}
	
		// 后置/最终通知：在执行目标方法之后执行  【无论是否出现异常最终都会执行】
		@After("pointCut_()")
		public void after(){
			System.out.println("提交事务/关闭");
		}
	
		// 返回后通知： 在调用目标方法结束后执行 【出现异常不执行】
		@AfterReturning("pointCut_()")
		public void afterReturning() {
			System.out.println("afterReturning()");
		}
	
		// 异常通知： 当目标方法执行异常时候执行此关注点代码
		@AfterThrowing("pointCut_()")
		public void afterThrowing(){
			System.out.println("afterThrowing()");
		}
	
		// 环绕通知：环绕目标方式执行
		@Around("pointCut_()")
		public void around(ProceedingJoinPoint pjp) throws Throwable{
			System.out.println("环绕前....");
			pjp.proceed();  // 执行目标方法
			System.out.println("环绕后....");
		}
	
		}	
	
4. bean.xml

		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
		    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		    xmlns:p="http://www.springframework.org/schema/p"
		    xmlns:context="http://www.springframework.org/schema/context"
		    xmlns:aop="http://www.springframework.org/schema/aop"
		    xsi:schemaLocation="
			http://www.springframework.org/schema/beans
			http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/context
			http://www.springframework.org/schema/context/spring-context.xsd
			http://www.springframework.org/schema/aop
			http://www.springframework.org/schema/aop/spring-aop.xsd">
	
		<!-- 开启注解扫描 -->
		<context:component-scan base-package="cn.itcast.e_aop_anno"></context:component-scan>
		<!-- 开启aop注解方式 -->
		<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
	</beans>   
	
App.java

	public class App {
	
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext("cn/itcast/e_aop_anno/bean.xml");

		// 目标对象有实现接口，spring会自动选择“JDK代理”
		@Test
		public void testApp() {
			IUserDao userDao = (IUserDao) ac.getBean("userDao");
			System.out.println(userDao.getClass());
			userDao.save();
		}
	
		// 目标对象没有实现接口， spring会用“cglib代理”
		@Test
		public void testCglib() {
			OrderDao orderDao = (OrderDao) ac.getBean("orderDao");
			System.out.println(orderDao.getClass());
			orderDao.save();
		}
	}
	
## 22.11 XML方式实现AOP编程

	Xml实现aop编程：
		1） 引入jar文件  【aop 相关jar， 4个】
		2） 引入aop名称空间
		3）aop 配置
			* 配置切面类 （重复执行代码形成的类）
			* aop配置
				拦截哪些方法 / 拦截到方法后应用通知代码
				
		<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:p="http://www.springframework.org/schema/p"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xmlns:aop="http://www.springframework.org/schema/aop"
	    xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop.xsd">
		
		<!-- dao 实例 -->
		<bean id="userDao" class="cn.itcast.f_aop_xml.UserDao"></bean>
		<bean id="orderDao" class="cn.itcast.f_aop_xml.OrderDao"></bean>
	
		<!-- 切面类 -->
		<bean id="aop" class="cn.itcast.f_aop_xml.Aop"></bean>
	
		<!-- Aop配置 -->
		<aop:config>
			<!-- 定义一个切入点表达式： 拦截哪些方法 -->
			<aop:pointcut expression="execution(* cn.itcast.f_aop_xml.*.*(..))" id="pt"/>
			
			<!-- 切面 -->
		<aop:aspect ref="aop">
			<!-- 环绕通知 -->
			<aop:around method="around" pointcut-ref="pt"/>
			<!-- 前置通知： 在目标方法调用前执行 -->
			<aop:before method="begin" pointcut-ref="pt"/>
			<!-- 后置通知： -->
			<aop:after method="after" pointcut-ref="pt"/>
			<!-- 返回后通知 -->
			<aop:after-returning method="afterReturning" pointcut-ref="pt"/>
			<!-- 异常通知 -->
			<aop:after-throwing method="afterThrowing" pointcut-ref="pt"/>
			
		</aop:aspect>
		</aop:config>
</beans>  

## 22.12 切入点表达式

切入点表达式:可以对指定的“方法”进行拦截；  从而给指定的方法所在的类生层代理对象。

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	    xmlns:p="http://www.springframework.org/schema/p"
	    xmlns:context="http://www.springframework.org/schema/context"
	    xmlns:aop="http://www.springframework.org/schema/aop"
	    xsi:schemaLocation="
		http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context
		http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/aop
		http://www.springframework.org/schema/aop/spring-aop.xsd">
		<!-- dao 实例 -->
	<bean id="userDao" class="cn.itcast.g_pointcut.UserDao"></bean>
	<bean id="orderDao" class="cn.itcast.g_pointcut.OrderDao"></bean>
	
	<!-- 切面类 -->
	<bean id="aop" class="cn.itcast.g_pointcut.Aop"></bean>
	
	<!-- Aop配置 -->
	<aop:config>
	<!-- 定义一个切入点表达式： 拦截哪些方法 -->
		<!--<aop:pointcut expression="execution(* cn.itcast.g_pointcut.*.*(..))" id="pt"/>-->
		
		<!-- 【拦截所有public方法】 -->
		<!--<aop:pointcut expression="execution(public * *(..))" id="pt"/>-->
		<!-- 【拦截所有save开头的方法 】 -->
		<!--<aop:pointcut expression="execution(* save*(..))" id="pt"/>-->
		
		<!-- 【拦截指定类的指定方法, 拦截时候一定要定位到方法】 -->
		<!--<aop:pointcut expression="execution(public * cn.itcast.g_pointcut.OrderDao.save(..))" id="pt"/>-->
		
		<!-- 【拦截指定类的所有方法】 -->
		<!--<aop:pointcut expression="execution(* cn.itcast.g_pointcut.UserDao.*(..))" id="pt"/>-->
		
		<!-- 【拦截指定包，以及其自包下所有类的所有方法】 -->
		<!--<aop:pointcut expression="execution(* cn..*.*(..))" id="pt"/>-->
		
		<!-- 【多个表达式】 -->
		<!--<aop:pointcut expression="execution(* cn.itcast.g_pointcut.UserDao.save()) || execution(* cn.itcast.g_pointcut.OrderDao.save())" id="pt"/>-->
		<!--<aop:pointcut expression="execution(* cn.itcast.g_pointcut.UserDao.save()) or execution(* cn.itcast.g_pointcut.OrderDao.save())" id="pt"/>-->
		<!-- 下面2个且关系的，没有意义 -->
		<!--<aop:pointcut expression="execution(* cn.itcast.g_pointcut.UserDao.save()) &amp;&amp; execution(* cn.itcast.g_pointcut.OrderDao.save())" id="pt"/>-->
		<!--<aop:pointcut expression="execution(* cn.itcast.g_pointcut.UserDao.save()) and execution(* cn.itcast.g_pointcut.OrderDao.save())" id="pt"/>-->
		
		<!-- 【取非值】 -->
		<!--<aop:pointcut expression="!execution(* cn.itcast.g_pointcut.OrderDao.save())" id="pt"/>-->
		<aop:pointcut expression=" not execution(* cn.itcast.g_pointcut.OrderDao.save())" id="pt"/>
		
			<!-- 切面 -->
			<aop:aspect ref="aop">
				<!-- 环绕通知 -->
				<aop:around method="around" pointcut-ref="pt"/>
			</aop:aspect>
		</aop:config>
	</beans>    
	
## 22.13 Spring对jdbc支持

	使用步骤：
		1）引入jar文件
			spring-jdbc-3.2.5.RELEASE.jar
			spring-tx-3.2.5.RELEASE.jar
		2） 优化
		
## 22.14 Spring对事务控制

事务应该在Service层统一控制。

	编程式事务控制
		自己手动控制事务，就叫做编程式事务控制。
		Jdbc代码：
			Conn.setAutoCommite(false);  // 设置手动控制事务
		Hibernate代码：
			Session.beginTransaction();    // 开启一个事务
		【细粒度的事务控制： 可以对指定的方法、指定的方法的某几行添加事务控制】
		(比较灵活，但开发起来比较繁琐： 每次都要开启、提交、回滚.)
		
	声明式事务控制
		Spring提供了对事务的管理, 这个就叫声明式事务管理。
		Spring提供了对事务控制的实现。用户如果想用Spring的声明式事务管理，只需要在配置文件中配置即可； 不想使用时直接移除配置。这个实现了对事务控制的最大程度的解耦。
		Spring声明式事务管理，核心实现就是基于Aop。
		【粗粒度的事务控制： 只能给整个方法应用事务，不可以对方法的某几行应用事务。】
		(因为aop拦截的是方法。)

		Spring声明式事务管理器类：
			Jdbc技术：DataSourceTransactionManager
			Hibernate技术：HibernateTransactionManager
			
## 22.15 声明式事务管理

	步骤：
		1） 引入spring-aop相关的4个jar文件
		2） 引入aop名称空间  【XML配置方式需要引入】
		3） 引入tx名称空间    【事务方式必须引入】
		
XML方式实现

1. DeptDao.java

		public class DeptDao {
	
		// 容器注入JdbcTemplate对象
		private JdbcTemplate jdbcTemplate;
		public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
			this.jdbcTemplate = jdbcTemplate;
		}

		public void save(Dept dept){
			String sql = "insert into t_dept (deptName) values(?)";
			jdbcTemplate.update(sql,dept.getDeptName());
		}
	}
	
2. DeptService

		public class DeptService {
	
		// 容器注入dao对象
		private DeptDao deptDao;
		public void setDeptDao(DeptDao deptDao) {
			this.deptDao = deptDao;
		}

		/*
		 * 事务控制？
		 */
		 public void save(Dept dept){
			// 第一次调用
			deptDao.save(dept);
		
			int i = 1/0; // 异常： 整个Service.save()执行成功的要回滚
		
			// 第二次调用
			deptDao.save(dept);
		}
		}
		
3. App 测试类

		@Test
		public void testApp() throws Exception {
				//容器对象
				ApplicationContext ac = new ClassPathXmlApplicationContext("cn/itcast/a_tx/bean.xml");
		
				// 模拟数据
				Dept dept = new Dept();
				dept.setDeptName("测试： 开发部");
		
				DeptService deptService = (DeptService) ac.getBean("deptService");
				deptService.save(dept);
		
			} 
			
4. bean.xml  (Spring声明式事务管理配置)

		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
			xmlns:p="http://www.springframework.org/schema/p"
			xmlns:context="http://www.springframework.org/schema/context"
			xmlns:aop="http://www.springframework.org/schema/aop"
			xmlns:tx="http://www.springframework.org/schema/tx"
			xsi:schemaLocation="http://www.springframework.org/schema/beans
		    	 http://www.springframework.org/schema/beans/spring-beans.xsd
		     	 http://www.springframework.org/schema/context
			 http://www.springframework.org/schema/context/spring-context.xsd
			 http://www.springframework.org/schema/aop
			 http://www.springframework.org/schema/aop/spring-aop.xsd
			 http://www.springframework.org/schema/tx
		     	 http://www.springframework.org/schema/tx/spring-tx.xsd">
		     	 
		     	 <!-- 1. 数据源对象: C3P0连接池 -->
			<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
				<property name="driverClass" value="com.mysql.jdbc.Driver"></property>
				<property name="jdbcUrl" value="jdbc:mysql:///hib_demo"></property>
				<property name="user" value="root"></property>
				<property name="password" value="root"></property>
				<property name="initialPoolSize" value="3"></property>
				<property name="maxPoolSize" value="10"></property>
				<property name="maxStatements" value="100"></property>
				<property name="acquireIncrement" value="2"></property>
			</bean>
			
			
			<!-- 2. JdbcTemplate工具类实例 -->
			<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
				<property name="dataSource" ref="dataSource"></property>
			</bean>
	
			<!-- 3. dao实例 -->
			<bean id="deptDao" class="cn.itcast.a_tx.DeptDao">
				<property name="jdbcTemplate" ref="jdbcTemplate"></property>
			</bean>
		 
			<!-- 4. service实例 -->
			<bean id="deptService" class="cn.itcast.a_tx.DeptService">
				<property name="deptDao" ref="deptDao"></property>
			</bean>
			
			<!-- #############5. Spring声明式事务管理配置############### -->
			<!-- 5.1 配置事务管理器类 -->
			<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
				<property name="dataSource" ref="dataSource"></property>
			</bean>
	
			<!-- 5.2 配置事务增强(如果管理事务?) -->
			<tx:advice id="txAdvice" transaction-manager="txManager">
				<tx:attributes>
					<tx:method name="get*" read-only="true"/>
					<tx:method name="find*" read-only="true"/>
					<tx:method name="*" read-only="false"/>
				</tx:attributes>
			</tx:advice>
			
			<!-- 5.3 Aop配置： 拦截哪些方法(切入点表表达式) + 应用上面的事务增强配置 -->
			<aop:config>
				<aop:pointcut expression="execution(* cn.itcast.a_tx.DeptService.*())" id="pt"/>
				<aop:advisor advice-ref="txAdvice" pointcut-ref="pt"/>
			</aop:config>
	
		</beans>    


注解方式实现

	使用注解实现Spring的声明式事务管理，更加简单！
	步骤：
		1） 必须引入Aop相关的jar文件
		2） bean.xml中指定注解方式实现声明式事务管理以及应用的事务管理器类
		3）在需要添加事务控制的地方，写上: @Transactional
		
	@Transactional注解：
	1）应用事务的注解
	2）定义到方法上： 当前方法应用spring的声明式事务
	3）定义到类上：   当前类的所有的方法都应用Spring声明式事务管理;
	4）定义到父类上： 当执行父类的方法时候应用事务。

Bean.xml

	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns:p="http://www.springframework.org/schema/p"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:aop="http://www.springframework.org/schema/aop"
		xmlns:tx="http://www.springframework.org/schema/tx"
		xsi:schemaLocation="http://www.springframework.org/schema/beans
	    	 http://www.springframework.org/schema/beans/spring-beans.xsd
	     	 http://www.springframework.org/schema/context
		 http://www.springframework.org/schema/context/spring-context.xsd
		 http://www.springframework.org/schema/aop
		 http://www.springframework.org/schema/aop/spring-aop.xsd
		 http://www.springframework.org/schema/tx
	     	 http://www.springframework.org/schema/tx/spring-tx.xsd">
	     	 
	     	 <!-- 1. 数据源对象: C3P0连接池 -->
		<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
			<property name="driverClass" value="com.mysql.jdbc.Driver"></property>
			<property name="jdbcUrl" value="jdbc:mysql:///hib_demo"></property>
			<property name="user" value="root"></property>
			<property name="password" value="root"></property>
			<property name="initialPoolSize" value="3"></property>
			<property name="maxPoolSize" value="10"></property>
			<property name="maxStatements" value="100"></property>
			<property name="acquireIncrement" value="2"></property>
		</bean>
	
		<!-- 2. JdbcTemplate工具类实例 -->
		<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
			<property name="dataSource" ref="dataSource"></property>
		</bean>
	
		<!-- 事务管理器类 -->
		<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
			<property name="dataSource" ref="dataSource"></property>
		</bean>
	
		<!-- 开启注解扫描 -->
		<context:component-scan base-package="cn.itcast.b_anno"></context:component-scan>
	
		<!-- 注解方式实现事务： 指定注解方式实现事务 -->
		<tx:annotation-driven transaction-manager="txManager"/>
	</beans>     

DeptService

	@Service
	public class DeptService {
	
		@Resource
		private DeptDao deptDao;

		/*
		 * 事务控制？
		 */
		@Transactional
		public void save(Dept dept){
			deptDao.save(dept);
			int i = 1/0;
			deptDao.save(dept);
		}
	}
	
	
## 22.16 事务属性

	@Transactional(
				readOnly = false,  // 读写事务
				timeout = -1,       // 事务的超时时间不限制
				noRollbackFor = ArithmeticException.class,  // 遇到数学异常不回滚
				isolation = Isolation.DEFAULT,              // 事务的隔离级别，数据库的默认
				propagation = Propagation.REQUIRED			// 事务的传播行为
		)
		public void save(Dept dept){
			deptDao.save(dept);
			int i = 1/0;
			deptDao.save(dept);
		}
		
	事务传播行为:
		Propagation.REQUIRED
			指定当前的方法必须在事务的环境下执行；
			如果当前运行的方法，已经存在事务， 就会加入当前的事务；
		Propagation.REQUIRED_NEW
			指定当前的方法必须在事务的环境下执行；
			如果当前运行的方法，已经存在事务：  事务会挂起； 会始终开启一个新的事务，执行完后；  刚才挂起的事务才继续运行。
			
			
举例：

	Class Log{
			Propagation.REQUIRED  
			insertLog();  
	}

		Propagation.REQUIRED
		Void  saveDept(){
			insertLog();    // 加入当前事务
			.. 异常, 会回滚
			saveDept();
		}
		
	Class Log{
			Propagation.REQUIRED_NEW  
			insertLog();  
	}

		Propagation.REQUIRED
		Void  saveDept(){
			insertLog();    // 始终开启事务
			.. 异常, 日志不会回滚
			saveDept();
		}
		

## 22.17 Spring与Hibernate整合

	Spring与Hibernate整合关键点：
		1） Hibernate的SessionFactory对象交给Spring创建；
		2） hibernate事务交给spring的声明式事务管理。
		
步骤实现

1. DeptDao.java

		// 数据访问层
		public class DeptDao {

			// Spring与Hibernate整合： IOC容器注入
			private SessionFactory sessionFactory;
			public void setSessionFactory(SessionFactory sessionFactory) {
				this.sessionFactory = sessionFactory;
			}

			// 保存一个记录
			// Spring与Hibernate整合：事务管理交给Spring
			public void save(Dept dept) {
				sessionFactory.getCurrentSession().save(dept);
			}
		}
	
2. DeptService

		public class DeptService {

			private DeptDao deptDao;
			public void setDeptDao(DeptDao deptDao) {
				this.deptDao = deptDao;
			}
	
			public void save(Dept dept){
				deptDao.save(dept);
			}
		}

3. App.java  测试

		public class App {
	
			// 容器
			private ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

			@Test
			public void testApp() throws Exception {
				DeptService deptServie = (DeptService) ac.getBean("deptService");
				System.out.println(deptServie.getClass());
		
				deptServie.save(new Dept());
			}
		}
		
4. bean.xml 配置  【Spring管理SessionFactory的3中方式】

		<?xml version="1.0" encoding="UTF-8"?>
		<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
			xmlns:p="http://www.springframework.org/schema/p"
			xmlns:context="http://www.springframework.org/schema/context"
			xmlns:aop="http://www.springframework.org/schema/aop"
			xmlns:tx="http://www.springframework.org/schema/tx"
			xsi:schemaLocation="http://www.springframework.org/schema/beans
		    	 http://www.springframework.org/schema/beans/spring-beans.xsd
		     	 http://www.springframework.org/schema/context
			 http://www.springframework.org/schema/context/spring-context.xsd
			 http://www.springframework.org/schema/aop
			 http://www.springframework.org/schema/aop/spring-aop.xsd
			 http://www.springframework.org/schema/tx
		     	 http://www.springframework.org/schema/tx/spring-tx.xsd">
		     	 
		     	 <!-- dao 实例 -->
			<bean id="deptDao" class="cn.itcast.dao.DeptDao">
				<property name="sessionFactory" ref="sessionFactory"></property>
			</bean>
	
			<!-- service 实例 -->
			<bean id="deptService" class="cn.itcast.service.DeptService">
				<property name="deptDao" ref="deptDao"></property>
			</bean>
	
			<!-- 数据源配置 -->
			<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
				<property name="driverClass" value="com.mysql.jdbc.Driver"></property>
				<property name="jdbcUrl" value="jdbc:mysql:///hib_demo"></property>
				<property name="user" value="root"></property>
				<property name="password" value="root"></property>
				<property name="initialPoolSize" value="3"></property>
				<property name="maxPoolSize" value="10"></property>
				<property name="maxStatements" value="100"></property>
				<property name="acquireIncrement" value="2"></property>
			</bean>
	
			<!-- ###########Spring与Hibernate整合  start########### -->
	
			<!-- 方式（1）直接加载hibernate.cfg.xml文件的方式整合
			<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
				<property name="configLocation" value="classpath:hibernate.cfg.xml"></property>
			</bean>    -->
	
			<!-- 方式（2）连接池交给spring管理  【一部分配置写到hibernate中，一份分在spring中完成】 
			<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
				<property name="configLocation" value="classpath:hibernate.cfg.xml"></property>
				<property name="dataSource" ref="dataSource"></property>
			</bean> -->
	
			<!-- 【推荐】方式（3）所有的配置全部都在Spring配置文件中完成 -->
			<bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
				<!-- 注入连接池对象 -->
				<property name="dataSource" ref="dataSource"></property>
		
				<!-- hibernate常用配置 -->
				<property name="hibernateProperties">
					<props>
						<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
						<prop key="hibernate.show_sql">true</prop>
						<prop key="hibernate.hbm2ddl.auto">update</prop>
				
				</props>
				</property>
		
				<!-- hibernate映射配置 
				<property name="mappingLocations">
					<list>
						<value>classpath:cn/itcast/entity/*.hbm.xml</value>
					</list>
				</property>
				-->
				<property name="mappingDirectoryLocations">
					<list>
						<value>classpath:cn/itcast/entity/</value>
					</list>
				</property>
			</bean>
	
			<!-- ###########Spring与Hibernate整合  end########### -->
	
			<!-- 事务配置 -->
			<!-- a. 配置事务管理器类 -->
			<bean id="txManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager">
				<property name="sessionFactory" ref="sessionFactory"></property>
			</bean>
			<!-- b. 配置事务增强(拦截到方法后如果管理事务?) -->
			<tx:advice id="txAdvice" transaction-manager="txManager">
				<tx:attributes>
					<tx:method name="*" read-only="false"/>
				</tx:attributes>
			</tx:advice>
	
			<!-- c. Aop配置 -->
			<aop:config>
				 <aop:pointcut expression="execution(* cn.itcast.service.*.*(..))" id="pt"/>
				 <aop:advisor advice-ref="txAdvice" pointcut-ref="pt"/>
			</aop:config>
	
		</beans>     

