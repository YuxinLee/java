#  SpringBoot

## 1 创建项目工程

### 1.1 通过压缩包创建

网址为：http://start.spring.io。Dependencies选择：Web, AOP, 模板引擎(Freemarker)(Velocity)。选择web：会自动添加Tomcat和Spring MVC的依赖。
然后在Intellij idea中设置import project(导入java项目)	。File -> new -> module from existing sources。然后点击pom.xml 点击next和finish即可 
			

### 1.2 使用Intellij idea直接创建Spring Boot项目

新建Spring Initializr项目
填写信息和需要的模块

### 1.3 使用Maven新建项目

新建项目，选择Maven
输入GroupId和AtrifaceId
选择存储项目路径，创建新项目，此时会生成pom.xml，即Maven的项目对象模型，并生成了源代码目录java，资源目录resources和测试目录test。

## 2 springboot 启动原理

```
@SpringBootApplication
public class Application {
   public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 2.1  @SpringBootApplication注解 

 @SpringBootApplication注解是Spring Boot的核心注解，它其实是一个组合注解： 

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
         @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
         @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
 ...
}
```

 @SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan。

#### 2.1.1 @Configuration

  任何一个标注了@Configuration的Java类定义都是一个JavaConfig配置类。  提到@Configuration就要提到他的搭档@Bean。使用这两个注解就可以创建一个简单的spring配置类，可以用来替代相应的xml配置文件。 @Configuration的注解类标识这个类可以使用Spring IoC容器作为bean定义的来源。  @Bean注解告诉Spring，一个带有@Bean的注解方法将返回一个对象，该对象应该被注册为在Spring应用程序上下文中的bean。  

xml形式：

```
<beans> 
	 <bean id = "car" class="com.test.Car"> 
         <property name="wheel" ref = "wheel"></property> 
     </bean> 
     <bean id = "wheel" class="com.test.Wheel"></bean> 
</beans>
```

注解形式：

```
@Configuration 
public class Conf { 
    @Bean 
    public Car car() { 
        Car car = new Car(); 
        car.setWheel(wheel()); 
        return car; 
    }
    
    @Bean 
    public Wheel wheel() { 
        return new Wheel(); 
    } 
}
```

#### 2.1.2 @ComponentScan

 @ComponentScan的功能其实就是自动扫描并加载符合条件的组件（比如@Component和@Repository等）或者bean定义，最终将这些bean定义加载到IoC容器中。  通过basePackages等属性来细粒度的定制@ComponentScan自动扫描的范围，如果不指定，则默认Spring框架实现会从声明@ComponentScan所在类的package进行扫描。 

 注：所以SpringBoot的启动类最好是放在root package下，因为默认不指定basePackages。 

#### 2.1.3 **@EnableAutoConfiguration** 

```
 @SuppressWarnings("deprecation")
 @Target(ElementType.TYPE)
 @Retention(RetentionPolicy.RUNTIME)
 @Documented
 @Inherited
 @AutoConfigurationPackage
 @Import(EnableAutoConfigurationImportSelector.class)
 public @interface EnableAutoConfiguration {
     ...
 }
```

 其中，最关键的要属@Import(EnableAutoConfigurationImportSelector.class)，借助EnableAutoConfigurationImportSelector，@EnableAutoConfiguration可以帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器。 

## 2.2  SpringApplication执行流程

SpringApplication的run方法的实现流程：

1.